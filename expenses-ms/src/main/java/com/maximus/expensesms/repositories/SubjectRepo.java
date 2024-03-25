package com.maximus.expensesms.repositories;

import com.maximus.expensesms.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {

    /**
     * Получение списка объектов по id типа объектов
     */
    List<Subject> getSubjectsBySubjectTypeId(Long subjectTypeId);
}
