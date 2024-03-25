package com.maximus.expensesms.services;


import com.maximus.expensesms.models.ExpenseType;
import com.maximus.expensesms.models.PaymentRecord;
import com.maximus.expensesms.models.Subject;
import com.maximus.expensesms.models.SubjectType;
import com.maximus.expensesms.repositories.PaymentRecordsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentRecordsService {

    private final PaymentRecordsRepo paymentsRepo;
    private final ExpenseTypeService expenseTypeService;
    private final SubjectTypeService subjectTypeService;
    private final SubjectService subjectService;
    private final InteractionService interactionService;


    public List<PaymentRecord> getAllPaymentRecords() {
        List<PaymentRecord> list = paymentsRepo.findAll();
        if (list.isEmpty()) {
            generateInitialDataExamples();
        }
        return list;
    }

    public void generateInitialDataExamples() {
        expenseTypeService.generateExpenseCategories();
        expenseTypeService.generateExpenseTypes();
        subjectTypeService.generateSubjectTypes();
        subjectService.generateSubjects();

        interactionService.addExpenseTypeToSubjectTypeByNames("Неопределено", "Прочее");
        interactionService.addExpenseTypeToSubjectTypeByNames("Оплата ЖКХ", "Объект недвижимости");
        interactionService.addExpenseTypeToSubjectTypeByNames("Страхование", "Объект недвижимости");
        interactionService.addExpenseTypeToSubjectTypeByNames("Страхование", "Автомобиль");
        interactionService.addExpenseTypeToSubjectTypeByNames("Страхование", "Физ.лицо");
        interactionService.addExpenseTypeToSubjectTypeByNames("Покупка продуктов", "Физ.лицо");
        interactionService.addExpenseTypeToSubjectTypeByNames("Покупка мебели", "Объект недвижимости");
        interactionService.addExpenseTypeToSubjectTypeByNames("Покупка мебели", "Физ.лицо");
        interactionService.addExpenseTypeToSubjectTypeByNames("Выплаты по кредитам", "Объект недвижимости");
        interactionService.addExpenseTypeToSubjectTypeByNames("Выплаты по кредитам", "Автомобиль");
    }


    public PaymentRecord getRecordById(Long id) {
        Optional<PaymentRecord> optPaymentRecord = paymentsRepo.findById(id);
        return optPaymentRecord.orElse(null);
    }

    /**
     * Добавление новой записи расходов
     */
    public PaymentRecord addPaymentRecord(PaymentRecord paymentRecord) {
        if (paymentRecord.getInputTime() == null) {
            paymentRecord.setInputTime(new Date(System.currentTimeMillis()));
        }
        return paymentsRepo.save(paymentRecord);
    }

    //region Вспомогательные методы по сбору данных в строку и сумма

    /**
     * Получение списка названий сущностей по их id:
     * - категория расхода, тип Расхода, тип объекта, объект
     *
     * @param paymentRecord запись расходов
     * @return список строковых значений
     */
    public List<String> dataToStringMapping(PaymentRecord paymentRecord) {


        ExpenseType expType = expenseTypeService.getExpenseTypeById(paymentRecord.getIdExpenseType());
        String expCategory = expType.getExpenseCategory().getName();
        String expTypeString = expType.getName();

        SubjectType subjectType = subjectTypeService.getSubjectTypeById(paymentRecord.getIdSubjectType());
        String subjTypeString = subjectType.getName();

        Subject subject = subjectService.getSubjectById(paymentRecord.getIdSubject());
        String subjectString = subject.getName();

        List<String> dataStringList = new ArrayList<>(List.of(expCategory, expTypeString, subjTypeString, subjectString));

        return dataStringList;
    }

    public Map<Long, List<String>> recordsStringProcessing(List<PaymentRecord> paymentRecordList) {
        Map<Long, List<String>> map = new HashMap<>();
        for (PaymentRecord pr : paymentRecordList) {
            map.put(pr.getId(), dataToStringMapping(pr));
        }
        return map;
    }
    //endregion

    /**
     * Получение записи расходов по id
     */
    public PaymentRecord getPaymentRecordById(Long id) {
        Optional<PaymentRecord> optPaymentRecord = paymentsRepo.findById(id);
        return optPaymentRecord.orElse(null);
    }

    /**
     * Обновление записи расходов по id и получаемой записи расходов
     */
    public PaymentRecord updatePaymentRecord(Long id, PaymentRecord paymentRecordDetails) {
        Optional<PaymentRecord> optionalRecord = paymentsRepo.findById(id);
        if (optionalRecord.isPresent()) {
            PaymentRecord paymentRecord = optionalRecord.get();
            paymentRecord.setIdExpenseCategory(paymentRecordDetails.getIdExpenseCategory());
            paymentRecord.setIdExpenseType(paymentRecordDetails.getIdExpenseType());
            paymentRecord.setIdSubjectType(paymentRecordDetails.getIdSubjectType());
            paymentRecord.setIdSubject(paymentRecordDetails.getIdSubject());
            paymentRecord.setPeriod(paymentRecordDetails.getPeriod());
            paymentRecord.setAmount(paymentRecordDetails.getAmount());
            paymentRecord.setNote(paymentRecordDetails.getNote());
            paymentRecord.setPaymentDate(paymentRecordDetails.getPaymentDate());

            return paymentsRepo.save(paymentRecord);
        } else {
            throw new IllegalArgumentException("Запись с id" + id + "не найдена");
        }
    }

    public void deletePaymentRecord(Long id) {
        paymentsRepo.deleteById(id);
    }

    /**
     * Получение суммы расходов между датами
     *
     * @param dateFrom дата начала периода
     * @param dateTo   дата конца периода
     * @return сумма расходов
     */
    public Double summarizeAllPaymentRecords(LocalDate dateFrom, LocalDate dateTo) {
        return paymentsRepo.summarizeAmountsBetweenDates(dateFrom, dateTo);
    }
}
