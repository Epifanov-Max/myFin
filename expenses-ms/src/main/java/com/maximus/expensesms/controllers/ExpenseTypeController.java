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
 * Класс контроллер типов расходов
 */
@Data
@RestController
@RequestMapping("/expense-types")
public class ExpenseTypeController {

    private final ExpenseTypeService expenseTypeService;
    private final InteractionService interactionService;

    /**
     * Получение списка всех типов расходов по Get-запросу на эндпоинт "/expenseTypes"
     */
    @GetMapping
    public List<ExpenseType> getAllExpenseTypes(){
        return expenseTypeService.getAllExpenseTypes();
    }


    /**
     * Получение типа расходов по ID по Get-запросу на эндпоинт "/expenseTypes/id"
     */
    @GetMapping("/{id}")
    public ExpenseType getExpenseTypeById(@PathVariable("id") Long id){
        return expenseTypeService.getExpenseTypeById(id);
    }

    /**
     * Добавление нового типа расходов из тела Post-запроса на эндпоинт "/expenseTypes"
     * @param expenseType новый тип расходов
     * @return добавленный тип расходов
     */
    @PostMapping
    public ExpenseType addExpenseType(@RequestBody ExpenseType expenseType){
        return expenseTypeService.addExpenseType(expenseType);
    }

    /**
     * Изменение данных типа расходов из тела Put-запроса
     * на эндпоинт "/expenseTypes/id"
     * @param id идентификатор типа расходов
     * @param expenseType тип расходов с обновленными данными
     * @return обновленный типа расходов
     */
    @PutMapping("/{id}")
    public ExpenseType updateExpenseType(@PathVariable Long id, @RequestBody ExpenseType expenseType){
        return expenseTypeService.updateExpenseType(id, expenseType);
    }

    /**
     * Удаление типа рсаходов по DELETE-запросу
     *      * на эндпоинт "/expenseTypes/{id}
     * @param id идентификатор типа расходов
     */
    @DeleteMapping("/{id}")
    public void deleteExpenseType(@PathVariable Long id){
        expenseTypeService.deleteExpenseType(id);
    }

    /**
     * Добавление типа объектов к типу расходов
     * @param expenseTypeId id типа расходов
     * @param subjectType id типа объектов
     * @return добавленный тип объектов
     */
    @PostMapping("/{id}/subject-types")
    public SubjectType addExpenseTypeToSubjectType(@PathVariable("id") Long expenseTypeId, @RequestBody SubjectType subjectType){
        return interactionService.addExpenseTypeToSubjectType(expenseTypeId, subjectType);
    }

    /**
     * Получить типы объектов при заданном id типа расходов
     * @param expenseTypeId
     * @return
     */
    @GetMapping("/{id}/subject-types")
    public List<SubjectType> getSubjectTypesByExpenseTypeId(@PathVariable("id") Long expenseTypeId){
        return interactionService.getSubjectTypesByExpenseTypeId(expenseTypeId);
    }

    /**
     * Получить список объектов по id типа расходов
     */
    @GetMapping("/{id}/subjects")
    public List<Subject> getSubjectsByExpenseTypeId(@PathVariable("id") Long expenseTypeId){
        return interactionService.getSubjectsByExpenseTypeId(expenseTypeId);
    }

    /**
     * Получить список категорий расходов
     */
    @GetMapping("/categories")
    public List<ExpenseCategory> getAllExpenseCategories(){
        return expenseTypeService.getAllExpenseCategories();
    }



}
