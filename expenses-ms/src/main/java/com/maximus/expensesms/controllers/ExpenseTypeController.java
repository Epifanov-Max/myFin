package com.maximus.expensesms.controllers;


import com.maximus.expensesms.models.ExpenseCategory;
import com.maximus.expensesms.models.ExpenseType;
import com.maximus.expensesms.models.Subject;
import com.maximus.expensesms.models.SubjectType;
import com.maximus.expensesms.services.ExpenseTypeService;
import com.maximus.expensesms.services.InteractionService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс контроллер пользователей
 */

@Data
@RestController
@RequestMapping("/expense-types")
//@RequiredArgsConstructor
public class ExpenseTypeController {

    private final ExpenseTypeService expenseTypeService;
    private final InteractionService interactionService;

    /**
     * Получение списка всех пользователей по Get-запросу с эндпоинта "/expenseTypes"
     *
     * @return список всех пользователей
     */
    @GetMapping
    public List<ExpenseType> getAllExpenseTypes(){
        return expenseTypeService.getAllExpenseTypes();
    }


    /**
     * Получение пользователя по ID по Get-запросу c эндпоинта "/expenseTypes/id"
     * @param id идентификатор пользователя
     * @return запрашиваемый пользователь
     */
    @GetMapping("/{id}")
    public ExpenseType getExpenseTypeById(@PathVariable("id") Long id){
        return expenseTypeService.getExpenseTypeById(id);
    }

    /**
     * Добавление нового пользователя из тела Post-запроса с эндпоинта "/expenseTypes"
     * @param expenseType новый пользователь
     * @return добавленный пользователь
     */
    @PostMapping
    public ExpenseType addExpenseType(@RequestBody ExpenseType expenseType){
        return expenseTypeService.addExpenseType(expenseType);
    }

    /**
     * Изменение данных пользователя из тела Put-запроса
     * с эндпоинта "/expenseTypes/id"
     * @param id идентификатор пользователя
     * @param expenseType пользователь с обновленными данными
     * @return обновленный пользователь
     */
    @PutMapping("/{id}")
    public ExpenseType updateExpenseType(@PathVariable Long id, @RequestBody ExpenseType expenseType){
        return expenseTypeService.updateExpenseType(id, expenseType);
    }

    /**
     * Удаление пользователя по DELETE-запросу
     *      * с эндпоинта "/expenseTypes/{id}
     * @param id идентификатор пользователя
     */
    @DeleteMapping("/{id}")
    public void deleteExpenseType(@PathVariable Long id){
        expenseTypeService.deleteExpenseType(id);
    }

    @PostMapping("/{id}/subject-types")
    public SubjectType addSubjectTypeToExpenseType(@PathVariable("id") Long expenseTypeId, @RequestBody SubjectType subjectType){
        return interactionService.addSubjectTypeToExpenseType(expenseTypeId, subjectType);
    }

    /**
     * Показать типы объектов при заданном id типа расходов
     * @param expenseTypeId
     * @return
     */
    @GetMapping("/{id}/subject-types")
    public List<SubjectType> getSubjectTypesByExpenseTypeId(@PathVariable("id") Long expenseTypeId){
        return interactionService.getSubjectTypesByExpenseTypeId(expenseTypeId);
    }

    @GetMapping("/{id}/subjects")
    public List<Subject> getSubjectsByExpenseTypeId(@PathVariable("id") Long expenseTypeId){
        return interactionService.getSubjectsByExpenseTypeId(expenseTypeId);
    }

    @GetMapping("/categories")
    public List<ExpenseCategory> getAllExpenseCategories(){
        return expenseTypeService.getAllExpenseCategories();
    }



}
