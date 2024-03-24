package com.maximus.expensesms.repositories;

import com.maximus.expensesms.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepo extends JpaRepository<Subject, Long> {

    List<Subject> getSubjectsBySubjectTypeId(Long subjectTypeId);
}
