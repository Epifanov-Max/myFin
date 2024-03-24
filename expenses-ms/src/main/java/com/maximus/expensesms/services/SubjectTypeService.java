package com.maximus.expensesms.services;

import com.maximus.expensesms.models.SubjectType;
import com.maximus.expensesms.repositories.SubjectTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Класс-сервис обработки пользователей
 */
@Service
@RequiredArgsConstructor
public class SubjectTypeService {

    private final SubjectTypeRepo subjectTypeRepo;



    /**
     * Получение списка пользователей
     *
     * @return список всех пользователей
     */
    public List<SubjectType>  getAllSubjectTypes() {
        return subjectTypeRepo.findAll();
    }

    /**
     * Получение пользователя по ID
     *
     * @param id идентификатор пользователя
     * @return запрошенный пользователь
     */
//    @TrackSubjectTypeAction
    public SubjectType getSubjectTypeById(Long id) {
        Optional<SubjectType> optSubjectType = subjectTypeRepo.findById(id);
        return optSubjectType.orElse(null);
    }

    /**
     * Добавление нового пользователя
     *
     * @param subjectType новый пользователь
     * @return добавленный пользователь
     */
//    @TrackSubjectTypeAction
    public SubjectType addSubjectType(SubjectType subjectType) {
        return subjectTypeRepo.save(subjectType);
    }

    /**
     * Изменение данных пользователя
     *
     * @param id          идентификатор пользователя
     * @param subjectTypeDetails пользователь с обновленными данными, (полученными из контроллера)
     * @return измененный пользователь
     */
//    @TrackSubjectTypeAction
    public SubjectType updateSubjectType(Long id, SubjectType subjectTypeDetails) {
        Optional<SubjectType> optionalSubjectType = subjectTypeRepo.findById(id);
        if (optionalSubjectType.isPresent()) {
            SubjectType subjectType = optionalSubjectType.get();
            subjectType.setName(subjectTypeDetails.getName());
            subjectType.setComment(subjectTypeDetails.getComment());
//            objectType.setIdExp(expenseTypeDetails.getIdExp());
            return subjectTypeRepo.save(subjectType);
        } else {
            throw new IllegalArgumentException("Тип объекта с id" + id + "не найден");
        }
    }

    /**
     * Удаление пользователя по ID
     *
     * @param id идентификатор пользователя
     */
    public void deleteSubjectType(Long id) {
        subjectTypeRepo.deleteById(id);
    }






}
