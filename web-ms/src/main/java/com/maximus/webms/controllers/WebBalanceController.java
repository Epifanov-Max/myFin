package com.maximus.webms.controllers;

import com.maximus.webms.models.BalanceRecord;
import com.maximus.webms.services.WebBalanceService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

/** Веб-контроллер записей остатков */
@Slf4j
@Data
@RequiredArgsConstructor
@Controller
@RequestMapping()
public class WebBalanceController {

    @Autowired
    private final WebBalanceService webBalanceService;

    /** Передача формы представления главной страницы */
    @GetMapping("/main")
    public String showMain() {
        return "main.html";
    }

    /** Передача формы представления списка остатков */
    @GetMapping("/balance-list")
    public String showData(Model model) {
        List<BalanceRecord> listBalanceRecords = webBalanceService.getAllBalanceRecords();
        model.addAttribute("balanceRecords", listBalanceRecords);
        log.info("Показана форма списка остатков");
        return "balance-list";
    }

    /** Получение суммы сальдо операций между датами */
    @GetMapping("/balance-list/betweenDates")
    public ResponseEntity<Double> balanceByTransactionsBetweenDates(@RequestParam("fromDate") LocalDate fromDate,
                                                                    @RequestParam("checkDate") LocalDate checkDate) {
        log.info("Запрос по сальдо операций между датами");
        Double result = webBalanceService.getBalanceOfOperationsBetweenDates(fromDate, checkDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /** Передача формы представления добавления нового остатка */
    @GetMapping("/balance-list/add-balance-record")
    public ModelAndView addBalanceRecordForm() {
        ModelAndView mav = new ModelAndView("add-balance-form");
        BalanceRecord newBalanceRecord = new BalanceRecord(0L, 0D,
                "", null, null);
        String page_name = "Добавление остатка на дату";
        mav.addObject("page_name", page_name);
        mav.addObject("balanceRecord", newBalanceRecord);
        log.info("Показана форма добавления остатка");
        return mav;
    }

    /** Передача данных для сохранение нового остатка и возврат на страницу списка остатков*/
    @PostMapping(path = "/balance-list/save-balance-record")
    public String addBalanceRecord(BalanceRecord balanceRecord) {
        webBalanceService.addBalanceRecord(balanceRecord);
        log.info("Добавлен остаток на дату {} сумма = {}",
                balanceRecord.balanceDate(), balanceRecord.amount());
        return "redirect:/balance-list";
    }

    /** Передача формы представления обновления остатка на базе формы добавления остатка  */
    @GetMapping("/balance-list/updateData")
    public ModelAndView updateDataForm(@RequestParam("id") Long balanceRecordId) {
        ModelAndView mav = new ModelAndView("add-balance-form");
        BalanceRecord updRecord = webBalanceService.getBalanceRecordById(balanceRecordId);
        String page_name = "Обновить данные по записи остатка";
        mav.addObject("page_name", page_name);
        mav.addObject("balanceRecord", updRecord);
//        fileGateway.writeToFile("project_table_actions.txt", "Данные проекта с id='" + projectId + "' обновлены в " +
//                LocalDateTime.now());
        log.info("Показана форма обновления записи остатка");
        return mav;
    }

    /** Передача данных для удаления записи остатк и возврат на страницу списка остатков*/
    @GetMapping("/balance-list/deleteData")
    public String deleteBalanceRecord(@RequestParam("id") Long id){
        webBalanceService.deleteBalanceRecord(id);
        log.info("Запись остатка с Id {} удалена", id);
        return "redirect:/balance-list";
    }

}
