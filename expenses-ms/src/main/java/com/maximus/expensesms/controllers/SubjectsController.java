package com.maximus.expensesms.controllers;


import com.maximus.expensesms.models.ExpenseType;
import com.maximus.expensesms.models.Subject;
import com.maximus.expensesms.services.InteractionService;
import com.maximus.expensesms.services.SubjectService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс контроллер объектов расходов
 */
@Data
@RestController
@RequestMapping("/subjects")
public class SubjectsController {

    private final SubjectService subjectService;
    private final InteractionService interactionService;

    /**
     * Получение списка всех объектов по Get-запросу на эндпоинт "/subjects"
     */
    @GetMapping
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    /**
     * Получение объекта по ID по Get-запросу на эндпоинт "/subjects/id"
     */
    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable("id") Long id){
        return subjectService.getSubjectById(id);
    }

    @GetMapping("/{id}/name")
    public String getStringSubjectById(@PathVariable("id") Long id){
        return subjectService.getSubjectById(id).getName();
    }


    /**
     * Добавление нового объекта из тела Post-запроса на эндпоинта "/subjects"
     */
    @PostMapping
    public Subject addSubject(@RequestBody Subject subject){
        return subjectService.addSubject(subject);
    }

    /**
     * Изменение данных объекта из тела Put-запроса на эндпоинт "/subjects/id"
     * @param id идентификатор объекта
     * @param subject обновленные данные объекта в виде созданного объекта
     * @return обновленный объект
     */
    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Long id, @RequestBody Subject subject){
        return subjectService.updateSubject(id, subject);
    }

    /**
     * Удаление объекта по DELETE-запросу на эндпоинт "/subjects/{id}
     * @param id идентификатор объекта
     */
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id){
        subjectService.deleteSubject(id);
    }

    /**
     * Получение списка типов расходов по id объекта
     * по DELETE-запросу на эндпоинт "/subjects/{id}/expense-types"
     */
    @GetMapping("/{id}/expense-types")
    public List<ExpenseType> getExpenseTypesByObjectId(@PathVariable("id") Long objectId){
        return interactionService.getExpenseTypesBySubjectsId(objectId);
    }

}
