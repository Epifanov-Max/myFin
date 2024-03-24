package com.maximus.webms.controllers;

import com.maximus.webms.models.*;
import com.maximus.webms.services.WebExpenseService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
@Controller
@RequestMapping
public class WebExpenseControllerMain {

    private final WebExpenseService webExpenseService;

    @GetMapping("/main")
    public String showMain() {
        return "main.html";
    }

    @GetMapping("/payments-list")
    public ModelAndView showData() {
        ModelAndView mav = new ModelAndView("payments-list");
        List<ExpenseRecord> listPayments = webExpenseService.getAllExpenseRecords();
        mav.addObject("payments", listPayments);
        Map<Long, List<String>> map = new HashMap<>(webExpenseService.mapPaymentStringRecords());
        mav.addObject("stringValues", map);

//        String resultSum = sumOfPayments(getListOfPaymentRecordsIds(paymentRecordsService.getAllPaymentRecords()));
//        String resultSum = "1000";
//        mav.addObject("sum", resultSum);
        return mav;
    }

    @GetMapping("/payments-list/objects")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return new ResponseEntity<>(webExpenseService.getAllSubjects(), HttpStatus.OK);
    }

    @GetMapping("/payments-list/expense-types")
    public ResponseEntity<List<ExpenseType>> getAllExpenseTypes() {
        return new ResponseEntity<>(webExpenseService.getAllExpenseTypes(), HttpStatus.OK);
    }

    @GetMapping("/add-payment-record")
    public ModelAndView addPaymentRecordForm() {
        ModelAndView mav = new ModelAndView("add-payment-form");
        ExpenseRecord newPaymentRecord = new ExpenseRecord(0L, 0L, 0L, 0L, 0L,
                "", 0D, "", null, null);
        List<ExpenseCategory> listExpTypeByCat = webExpenseService.listOfExpenseCategories();
        String page_name = "Добавление оплаты";
        mav.addObject("page_name", page_name);
        mav.addObject("paymentRecord", newPaymentRecord);
        mav.addObject("categories", listExpTypeByCat);
        return mav;
    }

    @PostMapping(path = "/payments-list/save-payment-record")
    public String addPaymentRecord(ExpenseRecord expenseRecord) {
        System.out.println("expenseRecord CONTROLLER = " + expenseRecord);
        webExpenseService.addPaymentRecord(expenseRecord);
        return "redirect:/payments-list";
    }

    @GetMapping("/add-payment-record/toggle/exp-category/{id}")
    public ResponseEntity<List<ExpenseType>> getExpenseTypesByExpCatId(@PathVariable("id") Long expenseCategoryId) {
        return new ResponseEntity<>(webExpenseService.getExpenseTypesByCategoryId(expenseCategoryId), HttpStatus.OK);
    }

    @GetMapping("/add-payment-record/toggle/exp-type/{id}")
    public ResponseEntity<List<SubjectType>> getSubjectTypesByExpTypeId(@PathVariable("id") Long expenseTypeId) {
        return new ResponseEntity<>(webExpenseService.getSubjectTypesByExpenseTypeId(expenseTypeId), HttpStatus.OK);
    }

    @GetMapping("/add-payment-record/toggle/object-type/{id}")
    public ResponseEntity<List<Subject>> getSubjectBySubjectTypeId(@PathVariable("id") Long subjectTypeId) {
        return new ResponseEntity<>(webExpenseService.getSubjectBySubjectTypeId(subjectTypeId), HttpStatus.OK);
    }

    @GetMapping("/payments-list/deleteData")
    public String deletePaymentRecord(@RequestParam("id") Long id){
        webExpenseService.deleteExpenseRecord(id);
        return "redirect:/payments-list";
    }

    //TODO UPDATES
    @GetMapping("/payments-list/updateData")
    public ModelAndView updateDataForm(@RequestParam("id") Long paymentRecordId) {
        ModelAndView mav = new ModelAndView("add-payment-form");
        ExpenseRecord updRecord = webExpenseService.getExpenseRecordById(paymentRecordId);
        List<ExpenseCategory> listExpTypeByCat = webExpenseService.listOfExpenseCategories();
        String page_name = "Обновить данные по записи";
        mav.addObject("page_name", page_name);

        mav.addObject("paymentRecord", updRecord);
        mav.addObject("categories", listExpTypeByCat);

//        fileGateway.writeToFile("project_table_actions.txt", "Данные проекта с id='" + projectId + "' обновлены в " +
//                LocalDateTime.now());
        return mav;
    }

    @GetMapping("/expense-types/settings")
    public ModelAndView showSettingsExpenseTypes() {
        ModelAndView mav = new ModelAndView("settings-expenses");
        List<ExpenseCategory> listCategories = webExpenseService.listOfExpenseCategories();
        List<ExpenseType> listTypes = webExpenseService.getAllExpenseTypes();
        String page_name = "Типы и категории расходов";
        mav.addObject("page_name", page_name);
        mav.addObject("categories", listCategories);
        mav.addObject("expenseTypes", listTypes);
        return mav;
    }
    @GetMapping("/subjects/settings")
    public ModelAndView showSettingsSubjects() {
        ModelAndView mav = new ModelAndView("settings-subject-expenses");
        List<SubjectType> listSubjectTypes = webExpenseService.getAllSubjectTypes();
        List<Subject> listSubjects = webExpenseService.getAllSubjects();
        String page_name = "Типы объектов и объекты";
        mav.addObject("page_name", page_name);
        mav.addObject("subjectTypes", listSubjectTypes);
        mav.addObject("subjects", listSubjects);
        return mav;
    }


}
