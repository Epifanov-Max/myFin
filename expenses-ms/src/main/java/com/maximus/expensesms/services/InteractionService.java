package com.maximus.expensesms.services;

import com.maximus.expensesms.models.ExpenseType;
import com.maximus.expensesms.models.Subject;
import com.maximus.expensesms.models.SubjectType;
import com.maximus.expensesms.repositories.ExpenseTypeRepo;
import com.maximus.expensesms.repositories.SubjectRepo;
import com.maximus.expensesms.repositories.SubjectTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InteractionService {

    private final ExpenseTypeRepo expenseTypeRepo;
    private final SubjectTypeRepo subjectTypeRepo;
    private final SubjectRepo subjectRepo;

    private final SubjectTypeService subjectTypeService;
    private final ExpenseTypeService expenseTypeService;

    /**
     *Поиск видов расходов по типу объектов
     * @param subjectTypeId
     * @return
     */

    public List<ExpenseType> getExpenseTypesBySubjectsTypeId (Long subjectTypeId){

        return expenseTypeRepo.getExpenseTypesBySubjectTypesId(subjectTypeId);
    }

    /**
     * Добавление типу расходов к типу объектов по их id
     *
     */
    public SubjectType addExpenseTypeToSubjectType(Long expenseTypeId, SubjectType subjectType) {
        SubjectType resultSubjType = expenseTypeRepo.findById(expenseTypeId)
                .map(expType -> {
                    Long subjTypeId = subjectType.getId();

                    if (subjTypeId != 0L) {
                        SubjectType _subjType = subjectTypeRepo.findById(subjTypeId).orElseThrow(() ->
                                new RuntimeException("! Не найден тип объекта с id = " + subjTypeId));
                        expType.addSubjectType(_subjType);
                        expenseTypeRepo.save(expType);
                        return _subjType;
                    } else {
                        expType.addSubjectType(subjectType);
                        subjectTypeRepo.save(subjectType);
                        return subjectType;
                    }
                })
                .orElseThrow(() -> new RuntimeException("Не найден тип расходов с id = " + expenseTypeId));
        return resultSubjType;
    }

    /**
     * Добавление типу расходов к типу объектов по их названиям
     */
     public void addExpenseTypeToSubjectTypeByNames(String expenseTypeName, String subjectTypeName){
         addExpenseTypeToSubjectType(expenseTypeService.getExpenseTypeByName(expenseTypeName).getId(),
        subjectTypeService.getSubjectTypeByName(subjectTypeName));
     }


    /**
     * Получение списка типов объектов по типу расходов
     * @param expenseTypeId
     * @return
     */
    public List<SubjectType> getSubjectTypesByExpenseTypeId (Long expenseTypeId){

        return subjectTypeRepo.getSubjectTypeByExpenseTypesId(expenseTypeId);
    }

    /**
     * Получение списка типа расходов по ID объекта
     * @param subjectId
     * @return
     */
    public List<ExpenseType> getExpenseTypesBySubjectsId (Long subjectId){
        Optional<Subject> subjFound = subjectRepo.findById(subjectId);
        Long subjectTypeId = subjFound.get().getSubjectType().getId();
        return expenseTypeRepo.getExpenseTypesBySubjectTypesId(subjectTypeId);
    }

    /**
     * Получение списка объектов по id типа расходов
     */
    public List<Subject> getSubjectsByExpenseTypeId (Long expenseTypeId){
        List<SubjectType> subjTypes = getSubjectTypesByExpenseTypeId(expenseTypeId);
        List<Subject> subjectList = new ArrayList<>();
        for (SubjectType subjectType: subjTypes){
            subjectList.addAll(subjectRepo.getSubjectsBySubjectTypeId(subjectType.getId()));
        }
        return subjectList;

    }

    /**
     * Получение объекта по id типа объектов
     */
    public List<Subject> getSubjectBySubjectTypeId(Long subjectTypeId) {
        return subjectRepo.getSubjectsBySubjectTypeId(subjectTypeId);
    }
}
