package com.maximus.expensesms.controllers;


import com.maximus.expensesms.models.SubjectType;
import com.maximus.expensesms.services.SubjectTypeService;
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
public class SubjectTypeController {

    private final SubjectTypeService subjectTypeService;



    /**
     * Получение списка всех пользователей по Get-запросу с эндпоинта "/expenseTypes"
     *
     * @return список всех пользователей
     */
    @GetMapping("/type")
    public List<SubjectType> getAllSubjectTypes(){
        return subjectTypeService.getAllSubjectTypes();
    }

    /**
     * Получение пользователя по ID по Get-запросу c эндпоинта "/expenseTypes/id"
     * @param id идентификатор пользователя
     * @return запрашиваемый пользователь
     */
    @GetMapping("/type/{id}")
    public SubjectType getSubjectTypeById(@PathVariable("id") Long id){
        return subjectTypeService.getSubjectTypeById(id);
    }

    /**
     * Добавление нового пользователя из тела Post-запроса с эндпоинта "/expenseTypes"
     * @param subjectType новый пользователь
     * @return добавленный пользователь
     */
    @PostMapping("/type")
    public SubjectType addSubjectType(@RequestBody SubjectType subjectType){
        return subjectTypeService.addSubjectType(subjectType);
    }

    /**
     * Изменение данных пользователя из тела Put-запроса
     * с эндпоинта "/expenseTypes/id"
     * @param id идентификатор пользователя
     * @param subjectType пользователь с обновленными данными
     * @return обновленный пользователь
     */
    @PutMapping("/type/{id}")
    public SubjectType updateSubjectType(@PathVariable Long id, @RequestBody SubjectType subjectType){
        return subjectTypeService.updateSubjectType(id, subjectType);
    }

    /**
     * Удаление пользователя по DELETE-запросу
     *      * с эндпоинта "/expenseTypes/{id}
     * @param id идентификатор пользователя
     */
    @DeleteMapping("/type/{id}")
    public void deleteSubjectType(@PathVariable Long id){
        subjectTypeService.deleteSubjectType(id);
    }



}
