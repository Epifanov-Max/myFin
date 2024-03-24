package com.maximus.expensesms.services;

import com.maximus.expensesms.models.Subject;
import com.maximus.expensesms.repositories.SubjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Класс-сервис обработки пользователей
 */
@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepo subjectRepo;


    /**
     * Получение списка пользователей
     *
     * @return список всех пользователей
     */
    public List<Subject>  getAllSubjects() {
        return subjectRepo.findAll();
    }

    /**
     * Получение пользователя по ID
     *
     * @param id идентификатор пользователя
     * @return запрошенный пользователь
     */
//    @TrackSubjectAction
    public Subject getSubjectById(Long id) {
        Optional<Subject> optSubject = subjectRepo.findById(id);
        return optSubject.orElse(null);
    }

    /**
     * Добавление нового пользователя
     *
     * @param subject новый пользователь
     * @return добавленный пользователь
     */
//    @TrackSubjectAction
    public Subject addSubject(Subject subject) {
        return subjectRepo.save(subject);
    }

    /**
     * Изменение данных пользователя
     *
     * @param id          идентификатор пользователя
     * @param subjectDetails пользователь с обновленными данными, (полученными из контроллера)
     * @return измененный пользователь
     */
//    @TrackSubjectAction
    public Subject updateSubject(Long id, Subject subjectDetails) {
        Optional<Subject> optionalSubject = subjectRepo.findById(id);
        if (optionalSubject.isPresent()) {
            Subject subject = optionalSubject.get();
            subject.setName(subjectDetails.getName());
            subject.setAddress(subjectDetails.getAddress());
            subject.setNote(subjectDetails.getNote());

//            subject.setIdExp(expenseTypeDetails.getIdExp());
            return subjectRepo.save(subject);
        } else {
            throw new IllegalArgumentException("Тип объекта с id" + id + "не найден");
        }
    }

    /**
     * Удаление объекта по ID
     *
     * @param id идентификатор объекта
     */
    public void deleteSubject(Long id) {
        subjectRepo.deleteById(id);
    }

    public List<Subject> getSubjectsBySubjectTypeId(Long subjectTypeId){
        return subjectRepo.getSubjectsBySubjectTypeId(subjectTypeId);
    }


}
