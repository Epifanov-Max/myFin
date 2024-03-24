package com.maximus.expensesms.repositories;

import com.maximus.expensesms.models.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectTypeRepo extends JpaRepository<SubjectType, Long> {

    List<SubjectType> getSubjectTypeByExpenseTypesId (Long expenseTypeId);


}
