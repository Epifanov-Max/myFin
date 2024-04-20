package com.maximus.expensesms.controllers;

import com.maximus.expensesms.models.*;
import com.maximus.expensesms.models.records.PaymentRecord;
import com.maximus.expensesms.models.records.Regularity;
import com.maximus.expensesms.services.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/** Класс контроллер записей расходов */
@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentRecordsController {

    private final PaymentRecordsService paymentRecordsService;
    private final ExpenseTypeService expenseTypeService;
    private final SubjectTypeService subjectTypeService;
    private final SubjectService subjectService;
    private final InteractionService interactionService;

    @GetMapping
    public List<PaymentRecord> getAllExpenseRecords() {
        return paymentRecordsService.getAllPaymentRecords();
    }

    /**
     * Получение суммы расходов между датами по Get-запросу на эндпоинт "/payments/summarize"
     * @param fromDate дата начала периода
     * @param toDate дата конца периода
     * @return сумма расходов
     */
    @GetMapping("/summarize")
    public Double summarizeAllPaymentRecords(@RequestParam("fromDate") LocalDate fromDate, @RequestParam("toDate") LocalDate toDate){
        return paymentRecordsService.summarizeAllPaymentRecords(fromDate, toDate);
    }

    @GetMapping("/{id}")
    public PaymentRecord getExpenseRecordById(@PathVariable("id") Long id){
        return paymentRecordsService.getRecordById(id);
    }

    @PostMapping("/save-payment-record")
    public void addExpenseRecord(@RequestBody PaymentRecord expenseRecord) {
        paymentRecordsService.addPaymentRecord(expenseRecord);
    }

    @GetMapping("/update/{id}")
    public PaymentRecord updateExpenseRecord(@PathVariable Long id, @RequestBody PaymentRecord expenseRecord){
        return paymentRecordsService.updatePaymentRecord(id, expenseRecord);
    }

    /**
     * Удаление записи по DELETE-запросу на эндпоинт "/payments/{id}
     * @param id идентификатор пользователя
     */
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id){
        paymentRecordsService.deletePaymentRecord(id);
    }


    /**
     * Получение словаря с ключами в виде id поля записи расходов и значениями в виде названий этих полей
     * @return словарь
     */
//    @GetMapping("/string-mapping")
//    public Map<Long, List<String>> mapPaymentStringRecords(){
//        return paymentRecordsService.recordsStringProcessing(paymentRecordsService.getAllPaymentRecords());
//
//    }

    /**
     * Получение списка типов расходов по id категории расходов
     */
    @GetMapping("/toggle/exp-category/{id}")
    public List<ExpenseType> getExpenseTypesByExpCatId(@PathVariable("id") Long expenseCategoryId) {
        return expenseTypeService.getExpenseTypesByCategoryId(expenseCategoryId);
    }

    /**
     * Получение списка типов объектов по id типа расходов
     */
    @GetMapping("/toggle/exp-type/{id}")
    public List<SubjectType> getSubjectTypesByExpTypeId(@PathVariable("id") Long expenseTypeId) {
        return interactionService.getSubjectTypesByExpenseTypeId(expenseTypeId);
    }

    /**
     * Получение списка объектов по id типа объектов
     */
    @GetMapping("/toggle/object-type/{id}")
    public List<Subject> getSubjectBySubjectTypeId(@PathVariable("id") Long subjectTypeId) {
        return interactionService.getSubjectBySubjectTypeId(subjectTypeId);
    }

    @GetMapping("/toggle/regularity")
    public List<Regularity> getStringRegularity() {
        return paymentRecordsService.getAllRegularities();
    }


}
