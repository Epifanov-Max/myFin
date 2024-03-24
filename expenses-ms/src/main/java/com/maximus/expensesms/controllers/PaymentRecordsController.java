package com.maximus.expensesms.controllers;

import com.maximus.expensesms.models.*;
import com.maximus.expensesms.services.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
     * Удаление записи по DELETE-запросу
     *      * с эндпоинта "/expenseTypes/{id}
     * @param id идентификатор пользователя
     */
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id){
        paymentRecordsService.deletePaymentRecord(id);
    }


    @GetMapping("/string-mapping")
    public Map<Long, List<String>> mapPaymentStringRecords(){
        return paymentRecordsService.recordsStringProcessing(paymentRecordsService.getAllPaymentRecords());

    }

    @GetMapping("/toggle/exp-category/{id}")
    public List<ExpenseType> getExpenseTypesByExpCatId(@PathVariable("id") Long expenseCategoryId) {
        return expenseTypeService.getExpenseTypesByCategoryId(expenseCategoryId);
    }

    @GetMapping("/toggle/exp-type/{id}")
    public List<SubjectType> getSubjectTypesByExpTypeId(@PathVariable("id") Long expenseTypeId) {
        return interactionService.getSubjectTypesByExpenseTypeId(expenseTypeId);
    }

    @GetMapping("/toggle/object-type/{id}")
    public List<Subject> getSubjectBySubjectTypeId(@PathVariable("id") Long subjectTypeId) {
        return interactionService.getSubjectBySubjectTypeId(subjectTypeId);
    }

}
