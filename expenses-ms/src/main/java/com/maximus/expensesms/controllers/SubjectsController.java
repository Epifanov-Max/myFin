package com.maximus.expensesms.controllers;


import com.maximus.expensesms.models.ExpenseType;
import com.maximus.expensesms.models.Subject;
import com.maximus.expensesms.services.InteractionService;
import com.maximus.expensesms.services.SubjectService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс контроллер пользователей
 */

@Data
@RestController
@RequestMapping("/subjects")
//@RequiredArgsConstructor
public class SubjectsController {

    private final SubjectService subjectService;
    private final InteractionService interactionService;

    /**
     * Получение списка всех объектов по Get-запросу с эндпоинта "/expenseTypes"
     *
     * @return список всех пользователей
     */
    @GetMapping
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    /**
     * Получение пользователя по ID по Get-запросу c эндпоинта "/expenseTypes/id"
     * @param id идентификатор пользователя
     * @return запрашиваемый пользователь
     */
    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable("id") Long id){
        return subjectService.getSubjectById(id);
    }

    /**
     * Добавление нового пользователя из тела Post-запроса с эндпоинта "/expenseTypes"
     * @param subject новый пользователь
     * @return добавленный пользователь
     */
    @PostMapping
    public Subject addSubject(@RequestBody Subject subject){
        return subjectService.addSubject(subject);
    }

    /**
     * Изменение данных пользователя из тела Put-запроса
     * с эндпоинта "/expenseTypes/id"
     * @param id идентификатор пользователя
     * @param subject пользователь с обновленными данными
     * @return обновленный пользователь
     */
    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Long id, @RequestBody Subject subject){
        return subjectService.updateSubject(id, subject);
    }

    /**
     * Удаление пользователя по DELETE-запросу
     *      * с эндпоинта "/expenseTypes/{id}
     * @param id идентификатор пользователя
     */
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id){
        subjectService.deleteSubject(id);
    }

    @GetMapping("/{id}/expense-types")
    public List<ExpenseType> getExpenseTypesByObjectId(@PathVariable("id") Long objectId){
        return interactionService.getExpenseTypesBySubjectsId(objectId);
    }

}
