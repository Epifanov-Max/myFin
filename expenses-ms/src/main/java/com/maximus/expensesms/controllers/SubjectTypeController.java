package com.maximus.expensesms.controllers;


import com.maximus.expensesms.models.SubjectType;
import com.maximus.expensesms.services.SubjectTypeService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс контроллер типов объектов
 */
@Data
@RestController
@RequestMapping("/subjects")
public class SubjectTypeController {

    private final SubjectTypeService subjectTypeService;

    /**
     * Получение списка всех типов объектов по Get-запросу на эндпоинт "/subjects/type"
     */
    @GetMapping("/type")
    public List<SubjectType> getAllSubjectTypes(){
        return subjectTypeService.getAllSubjectTypes();
    }

    /**
     * Получение типа объектов по ID по Get-запросу на эндпоинт "/subjects/type/id"
     */
    @GetMapping("/type/{id}")
    public SubjectType getSubjectTypeById(@PathVariable("id") Long id){
        return subjectTypeService.getSubjectTypeById(id);
    }

    /**
     * Добавление нового типа объектов из тела Post-запроса на эндпоинт "/subjects/type"
     */
    @PostMapping("/type")
    public SubjectType addSubjectType(@RequestBody SubjectType subjectType){
        return subjectTypeService.addSubjectType(subjectType);
    }

    /**
     * Изменение данных типа объектов по id объекта и данным из тела Put-запроса
     * на эндпоинт "/subjects/type/id"
     */
    @PutMapping("/type/{id}")
    public SubjectType updateSubjectType(@PathVariable Long id, @RequestBody SubjectType subjectType){
        return subjectTypeService.updateSubjectType(id, subjectType);
    }

    /**
     * Удаление типа объектов по Id по DELETE-запросу на эндпоинт "/subjects/type/id
     */
    @DeleteMapping("/subjects/type/id")
    public void deleteSubjectType(@PathVariable Long id){
        subjectTypeService.deleteSubjectType(id);
    }



}
