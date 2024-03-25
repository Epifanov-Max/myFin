package com.maximus.expensesms.repositories;

import com.maximus.expensesms.models.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectTypeRepo extends JpaRepository<SubjectType, Long> {

    /**
     * Получение типа объекта по id типа расходов
     */
    List<SubjectType> getSubjectTypeByExpenseTypesId (Long expenseTypeId);


}
