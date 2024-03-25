package com.maximus.expensesms.services;

import com.maximus.expensesms.models.SubjectType;
import com.maximus.expensesms.repositories.SubjectTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Класс-сервис обработки типов объектов
 */
@Service
@RequiredArgsConstructor
public class SubjectTypeService {

    private final SubjectTypeRepo subjectTypeRepo;

    /**
     * Получение списка всех типов расходов
     */
    public List<SubjectType>  getAllSubjectTypes() {
        return subjectTypeRepo.findAll();
    }

    /**
     * Получение типа объектов расходов по id
     */
    public SubjectType getSubjectTypeById(Long id) {
        Optional<SubjectType> optSubjectType = subjectTypeRepo.findById(id);
        return optSubjectType.orElse(null);
    }

    /**
     * Добавление нового типа объектов
     */
    public SubjectType addSubjectType(SubjectType subjectType) {
        return subjectTypeRepo.save(subjectType);
    }

    /**
     * Изменение данных типа объектов по id и объекту с обновленными данными
     */
    public SubjectType updateSubjectType(Long id, SubjectType subjectTypeDetails) {
        Optional<SubjectType> optionalSubjectType = subjectTypeRepo.findById(id);
        if (optionalSubjectType.isPresent()) {
            SubjectType subjectType = optionalSubjectType.get();
            subjectType.setName(subjectTypeDetails.getName());
            subjectType.setComment(subjectTypeDetails.getComment());
            return subjectTypeRepo.save(subjectType);
        } else {
            throw new IllegalArgumentException("Тип объекта с id" + id + "не найден");
        }
    }

    /**
     * Удаление типа объектов по ID
     */
    public void deleteSubjectType(Long id) {
        subjectTypeRepo.deleteById(id);
    }
}
