package com.maximus.webms.feignclients;

import com.maximus.webms.models.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@FeignClient(name= "exp-records-service",url="http://127.0.0.1:8002" )
public interface WebExpenseFeignClient {

    @GetMapping("/payments")
    List<ExpenseRecord> getAllExpenseRecords();

    @GetMapping("/payments/string-mapping")
    Map<Long, List<String>> mapPaymentStringRecords();

    @GetMapping("/expense-types/categories")
    List<ExpenseCategory> listOfExpenseCategories();

    @GetMapping("/payments/toggle/exp-category/{id}")
    List<ExpenseType> getExpenseTypesByExpCatId(@PathVariable("id") Long expenseCategoryId);

    @GetMapping("/payments/toggle/exp-type/{id}")
    List<SubjectType> getSubjectTypesByExpTypeId(@PathVariable("id") Long expenseTypeId);

    @GetMapping("/payments/toggle/object-type/{id}")
    List<Subject> getSubjectBySubjectTypeId(@PathVariable("id") Long subjectTypeId);

    @PostMapping("/payments/save-payment-record")
    void addExpenseRecord(ExpenseRecord expenseRecord);

    @GetMapping("/subjects")
    List<Subject> getAllSubjects();

    @GetMapping("/expense-types")
    List<ExpenseType> getAllExpenseTypes();

    @DeleteMapping("/payments/{id}")
    void deleteExpenseRecord(@PathVariable("id") Long id);

    @GetMapping("/payments/{id}")
    ExpenseRecord getExpenseRecordById(@PathVariable("id") Long paymentRecordId);
    @GetMapping("/payments/summarize")
    Double summarizePaymentRecords(@RequestParam("fromDate") LocalDate fromDate, @RequestParam("toDate") LocalDate toDate);

    @GetMapping("/subjects/type")
    List<SubjectType> getAllSubjectTypes();
}
