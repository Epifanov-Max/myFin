package com.maximus.expensesms.repositories;

import com.maximus.expensesms.models.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseCategoryRepo extends JpaRepository<ExpenseCategory,Long> {

}
