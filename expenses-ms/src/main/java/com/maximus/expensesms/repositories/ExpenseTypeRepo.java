package com.maximus.expensesms.repositories;

import com.maximus.expensesms.models.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseTypeRepo extends JpaRepository<ExpenseType,Long> {

    List<ExpenseType> getExpenseTypesBySubjectTypesId (Long subjectTypeId);

    List<ExpenseType> getExpenseTypesByExpenseCategoryId (Long expenseCategoryId);

}
