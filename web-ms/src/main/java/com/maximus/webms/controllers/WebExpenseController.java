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

/** Веб-контроллер записей расходов */
@Data
@RequiredArgsConstructor
@Controller
@RequestMapping
public class WebExpenseController {

    private final WebExpenseService webExpenseService;

    /** Передача формы представления списка расходов */
    @GetMapping("/payments-list")
    public ModelAndView showData() {
        ModelAndView mav = new ModelAndView("payments-list");
        List<ExpenseRecord> listPayments = webExpenseService.getAllExpenseRecords();
        mav.addObject("payments", listPayments);
        Map<Long, List<String>> map = new HashMap<>(webExpenseService.mapPaymentStringRecords());
        mav.addObject("stringValues", map);
        return mav;
    }

    /** Получение списка объектов расходов */
    @GetMapping("/payments-list/objects")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return new ResponseEntity<>(webExpenseService.getAllSubjects(), HttpStatus.OK);
    }

    /** Получение списка типов расходов */
    @GetMapping("/payments-list/expense-types")
    public ResponseEntity<List<ExpenseType>> getAllExpenseTypes() {
        return new ResponseEntity<>(webExpenseService.getAllExpenseTypes(), HttpStatus.OK);
    }

    /** Передача формы представления добавления записи расходов */
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

    /** Передача данных для сохранение новой записи расходов и возврат на страницу списка расходов*/
    @PostMapping(path = "/payments-list/save-payment-record")
    public String addPaymentRecord(ExpenseRecord expenseRecord) {
        System.out.println("expenseRecord CONTROLLER = " + expenseRecord);
        webExpenseService.addPaymentRecord(expenseRecord);
        return "redirect:/payments-list";
    }

    /** Получение списка типов расходов по id категории затрат */
    @GetMapping("/add-payment-record/toggle/exp-category/{id}")
    public ResponseEntity<List<ExpenseType>> getExpenseTypesByExpCatId(@PathVariable("id") Long expenseCategoryId) {
        return new ResponseEntity<>(webExpenseService.getExpenseTypesByCategoryId(expenseCategoryId), HttpStatus.OK);
    }

    /** Получение списка типов объектов по id типа затрат */
    @GetMapping("/add-payment-record/toggle/exp-type/{id}")
    public ResponseEntity<List<SubjectType>> getSubjectTypesByExpTypeId(@PathVariable("id") Long expenseTypeId) {
        return new ResponseEntity<>(webExpenseService.getSubjectTypesByExpenseTypeId(expenseTypeId), HttpStatus.OK);
    }

    /** Получение списка объектов по id типов объектов*/
    @GetMapping("/add-payment-record/toggle/object-type/{id}")
    public ResponseEntity<List<Subject>> getSubjectBySubjectTypeId(@PathVariable("id") Long subjectTypeId) {
        return new ResponseEntity<>(webExpenseService.getSubjectBySubjectTypeId(subjectTypeId), HttpStatus.OK);
    }

    /** Удаление записи расходов по id */
    @GetMapping("/payments-list/deleteData")
    public String deletePaymentRecord(@RequestParam("id") Long id){
        webExpenseService.deleteExpenseRecord(id);
        return "redirect:/payments-list";
    }

    /** Передача формы представления обновления записи расходов на базе формы добавления записи расходов */
    @GetMapping("/payments-list/updateData")
    public ModelAndView updateDataForm(@RequestParam("id") Long paymentRecordId) {
        ModelAndView mav = new ModelAndView("add-payment-form");
        ExpenseRecord updRecord = webExpenseService.getExpenseRecordById(paymentRecordId);
        List<ExpenseCategory> listExpTypeByCat = webExpenseService.listOfExpenseCategories();
        String page_name = "Обновить данные по записи";
        mav.addObject("page_name", page_name);
        mav.addObject("paymentRecord", updRecord);
        mav.addObject("categories", listExpTypeByCat);

        return mav;
    }

    /** Передача формы представления списка типов расходов и категорий расходов */
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

    /** Передача формы представления списка типов объектов и объектов */
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
