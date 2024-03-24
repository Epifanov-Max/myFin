package com.maximus.webms.services;

import com.maximus.webms.feignclients.WebExpenseFeignClient;
import com.maximus.webms.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service("ExpenseService")
public class WebExpenseService implements SumRecords{

    private final WebExpenseFeignClient webExpenseFeignClient;

    public List<ExpenseRecord> getAllExpenseRecords(){
        return webExpenseFeignClient.getAllExpenseRecords();
    }

    public Map<Long, List<String>> mapPaymentStringRecords(){
        return webExpenseFeignClient.mapPaymentStringRecords();
    }

    public List<ExpenseCategory> listOfExpenseCategories(){
        return webExpenseFeignClient.listOfExpenseCategories();
    }

    public List<ExpenseType> getExpenseTypesByCategoryId(Long expenseCategoryId) {
        return webExpenseFeignClient.getExpenseTypesByExpCatId(expenseCategoryId);
    }

    public List<SubjectType> getSubjectTypesByExpenseTypeId(Long expenseTypeId) {
        return webExpenseFeignClient.getSubjectTypesByExpTypeId(expenseTypeId);
    }

    public List<Subject> getSubjectBySubjectTypeId(Long subjectTypeId) {
        return webExpenseFeignClient.getSubjectBySubjectTypeId(subjectTypeId);
    }

    public void addPaymentRecord(ExpenseRecord expenseRecord) {
        webExpenseFeignClient.addExpenseRecord(expenseRecord);
    }

    public List<Subject> getAllSubjects(){
        return webExpenseFeignClient.getAllSubjects();
    }

    public List<SubjectType> getAllSubjectTypes(){
        return webExpenseFeignClient.getAllSubjectTypes();
    }


    public List<ExpenseType> getAllExpenseTypes(){
        return webExpenseFeignClient.getAllExpenseTypes();
    }

    public void deleteExpenseRecord(Long id) {
        webExpenseFeignClient.deleteExpenseRecord(id);
    }

    public ExpenseRecord getExpenseRecordById(Long paymentRecordId) {
        return webExpenseFeignClient.getExpenseRecordById(paymentRecordId);
    }
    public Double getSumOfRecordsBetweenDates(LocalDate fromDate, LocalDate toDate){
//        if (fromDate ==null) {
//            fromDate = getLastBalanceRecord(toDate).balanceDate();
//        }
        return webExpenseFeignClient.summarizePaymentRecords(fromDate, toDate);
    }

}
