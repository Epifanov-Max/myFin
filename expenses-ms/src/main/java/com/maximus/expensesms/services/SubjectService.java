package com.maximus.expensesms.services;

import com.maximus.expensesms.models.Subject;
import com.maximus.expensesms.repositories.SubjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Класс-сервис обработки объектов
 */
@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepo subjectRepo;


    /**
     * Получение списка всех объектов
      */
    public List<Subject>  getAllSubjects() {
        return subjectRepo.findAll();
    }

    /**
     * Получение объекта по ID
     */
    public Subject getSubjectById(Long id) {
        Optional<Subject> optSubject = subjectRepo.findById(id);
        return optSubject.orElse(null);
    }

    /**
     * Добавление нового объекта
     */
    public Subject addSubject(Subject subject) {
        return subjectRepo.save(subject);
    }

    /**
     * Изменение данных объекта
     * @param id          идентификатор объекта
     * @param subjectDetails объект с обновленными данными, (полученными из контроллера)
     * @return измененный объект
     */
    public Subject updateSubject(Long id, Subject subjectDetails) {
        Optional<Subject> optionalSubject = subjectRepo.findById(id);
        if (optionalSubject.isPresent()) {
            Subject subject = optionalSubject.get();
            subject.setName(subjectDetails.getName());
            subject.setAddress(subjectDetails.getAddress());
            subject.setNote(subjectDetails.getNote());
            return subjectRepo.save(subject);
        } else {
            throw new IllegalArgumentException("Тип объекта с id" + id + "не найден");
        }
    }

    /**
     * Удаление объекта по ID
     */
    public void deleteSubject(Long id) {
        subjectRepo.deleteById(id);
    }

    /**
     *  Получение списка объектов по id типа расходов
     * @param subjectTypeId
     * @return
     */
    public List<Subject> getSubjectsBySubjectTypeId(Long subjectTypeId){
        return subjectRepo.getSubjectsBySubjectTypeId(subjectTypeId);
    }
}
