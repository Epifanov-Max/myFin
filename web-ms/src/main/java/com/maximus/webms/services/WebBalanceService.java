package com.maximus.webms.services;

import com.maximus.webms.feignclients.WebBalanceFeignClient;
import com.maximus.webms.models.*;
import com.maximus.webms.models.BalanceRecord;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** Сервисный класс записей остатков */
@Slf4j
@Data
@Service
public class WebBalanceService {

    private final WebBalanceFeignClient webBalanceFeignClient;
    private final WebExpenseService webExpenseService;
    private final WebRevenueService webRevenueService;

    /** Внедрение интерфейса суммы операций для доходов */
    @Autowired
    @Qualifier("RevenueService")
    private SumRecords sumRecordsRevenue;

    /** Внедрение интерфейса суммы операций для расходов */
    @Autowired
    @Qualifier("ExpenseService")
    private SumRecords sumRecordsExpense;

    /** Получить последнюю запись остатка, т.е. ближайшую к запрашиваемой дате */
    public BalanceRecord getLastBalanceRecord(LocalDate checkDate) {
        return webBalanceFeignClient.getLastBalanceRecord(checkDate);
    }

    /**
     * Получение суммы сальдо операций между заданными датами
     * @param fromDate дата начала периода
     * @param checkDate дата конца периода
     * @return сумма сальдо операций
     */
    public Double getBalanceOfOperationsBetweenDates(LocalDate fromDate, LocalDate checkDate) {
        if (checkDate.isBefore(fromDate)) {
            return 0D;
        }
        Optional<BalanceRecord> balanceRecord = Optional.ofNullable(getLastBalanceRecord(checkDate));
        if (balanceRecord.isPresent()) {
            //Если существует хотя бы одна запись баланса, то:
            if (balanceRecord.get().balanceDate().isBefore(fromDate)) {
                //Если дата внесенного остатка раньше даты начала периода:
                return getRevenueSum(fromDate, checkDate) - getExpenseSum(fromDate, checkDate);
            } else {
                //если дата внесенного остатка позже или равна дате начала периода:
                fromDate = balanceRecord.get().balanceDate();
                return balanceRecord.get().amount() + getRevenueSum(fromDate, checkDate)
                        - getExpenseSum(fromDate, checkDate);
            }
        } else {
            //Если нет записей баланса в базе
            //Добавить в базу первоначальное нулевое значение баланса
            addBalanceRecord(new BalanceRecord(1L, 0D,
                    "Автоматическое внесение остатка на начало",
                    LocalDate.parse("1900-01-01"), null));
            log.info("Автоматическое внесение записи остатка на начало");
        }
        return  getBalanceOfOperationsBetweenDates(fromDate, checkDate);
    }

    /**
     * Получение суммы доходов за период
     * @param fromDate дата начала периода
     * @param checkDate дата конца периода
     * @return сумма доходов
     */
    public Double getRevenueSum(LocalDate fromDate, LocalDate checkDate) {
        Optional<Double> sumRevenues = Optional.ofNullable(sumRecordsRevenue.getSumOfRecordsBetweenDates(fromDate, checkDate));
        return sumRevenues.orElse(0D);

    }

    /**
     * Получение суммы расходов за период
     * @param fromDate дата начала периода
     * @param checkDate дата конца периода
     * @return сумма доходов
     */
    public Double getExpenseSum(LocalDate fromDate, LocalDate checkDate) {
        Optional<Double> sumExpenses = Optional.ofNullable(sumRecordsExpense.getSumOfRecordsBetweenDates(fromDate, checkDate));
        return sumExpenses.orElse(0D);
    }

    /**
     * Получить все записи остатков
     * @return список записей остатков
     */
    public List<BalanceRecord> getAllBalanceRecords() {
        return webBalanceFeignClient.getAllBalanceRecords();
    }

    /**
     * Добавить запись остатка
     * @param balanceRecord запись остатка
     */
    public void addBalanceRecord(BalanceRecord balanceRecord) {

        if (balanceRecord.id() == 1L) {
            webBalanceFeignClient.addBalanceRecord(balanceRecord);
        } else {
            addAutoCorrectionRecord(balanceRecord);
            webBalanceFeignClient.addBalanceRecord(balanceRecord);
        }
    }

    /**
     * Добавление корректировочной записи в расходы или доходы
     * после нахождения расхождений в вводимых данных и расчетных.
     *
     * @param balanceRecord внесенный пользователем остаток
     */
    public void addAutoCorrectionRecord(BalanceRecord balanceRecord) {
        // Запись предыдущего остатка
        BalanceRecord previousBalanceRecord = getLastBalanceRecord(balanceRecord.balanceDate().minusDays(1));
        // Разница между вносимым остатком и предыдущим
        double balancesDifference = balanceRecord.amount() - previousBalanceRecord.amount();
        //дельта операций между вносимой датой и датой предыдущей записи остатка
        double sumDeltaOperationsBetweenBalanceRecordsDates = getBalanceOfOperationsBetweenDates(
                previousBalanceRecord.balanceDate(), balanceRecord.balanceDate()) - previousBalanceRecord.amount();
        Double correctionAmount;
        if (balancesDifference < 0) {
            if (sumDeltaOperationsBetweenBalanceRecordsDates >= 0) {
                correctionAmount = -balancesDifference - sumDeltaOperationsBetweenBalanceRecordsDates;
            } else {
                correctionAmount = +balancesDifference + sumDeltaOperationsBetweenBalanceRecordsDates;
            }
            webExpenseService.addPaymentRecord(
                    new ExpenseRecord(0L, 1L, 1L,
                            1L, 1L, " ",
                            correctionAmount, "автокорректировка исходя из внесенного остатка ",
                            balanceRecord.balanceDate().minusDays(1), null));
            log.info("Внесение корректирующей записи в расходы на сумму {}  датой {} ", correctionAmount,
                    balanceRecord.balanceDate().minusDays(1));
        } else {
            if (sumDeltaOperationsBetweenBalanceRecordsDates >= 0) {
                correctionAmount = balancesDifference - sumDeltaOperationsBetweenBalanceRecordsDates;
            } else {
                correctionAmount = balancesDifference + sumDeltaOperationsBetweenBalanceRecordsDates;
            }
            webRevenueService.addRevenueRecord(
                    new RevenueRecord(0L, 1L, correctionAmount,
                            "автокорректировка исходя из внесенного остатка",
                            balanceRecord.balanceDate().minusDays(1), null));
            log.info("Внесение корректирующей записи в доходы на сумму {}  датой {}", correctionAmount,
                    balanceRecord.balanceDate().minusDays(1));
        }
    }

    /**
     * Удалить запись остатка по id
     * @param id id записи остатка
     */
    public void deleteBalanceRecord(Long id) {
        webBalanceFeignClient.deleteBalanceRecord(id);
    }

    /**
     * Получить запись остатка по id
     * @param balanceRecordId id записи остатка
     * @return  запись остатка
     */
    public BalanceRecord getBalanceRecordById(Long balanceRecordId) {
        return webBalanceFeignClient.getBalanceRecordById(balanceRecordId);
    }


}
