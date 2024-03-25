package com.maximus.expensesms.repositories;

import com.maximus.expensesms.models.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseTypeRepo extends JpaRepository<ExpenseType,Long> {

    /**
     * Получение списка типа расходов по id типа объекта
     */
    List<ExpenseType> getExpenseTypesBySubjectTypesId (Long subjectTypeId);

    /**
     * Получение списка типа расходов по id категории расходов
     */
    List<ExpenseType> getExpenseTypesByExpenseCategoryId (Long expenseCategoryId);

}
