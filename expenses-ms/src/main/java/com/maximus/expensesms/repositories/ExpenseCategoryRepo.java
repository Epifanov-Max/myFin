package com.maximus.expensesms.repositories;

import com.maximus.expensesms.models.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseCategoryRepo extends JpaRepository<ExpenseCategory,Long> {

}
