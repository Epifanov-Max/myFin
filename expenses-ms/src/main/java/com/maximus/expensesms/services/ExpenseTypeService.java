package com.maximus.expensesms.services;

import com.maximus.expensesms.models.ExpenseCategory;
import com.maximus.expensesms.models.ExpenseType;
import com.maximus.expensesms.repositories.ExpenseCategoryRepo;
import com.maximus.expensesms.repositories.ExpenseTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Класс-сервис обработки типов расходов
 */
@Service
@RequiredArgsConstructor
public class ExpenseTypeService {

    private final ExpenseTypeRepo expenseTypeRepo;
    private final ExpenseCategoryRepo expenseCategoryRepo;

    /**
     * Получение списка всех категорий расходов
     *
     * @return список всех категорий расходов
     */
    public List<ExpenseCategory> getAllExpenseCategories() {
        return expenseCategoryRepo.findAll();
    }

    /**
     * Получение списка всех типов расходов
     *
     * @return список всех типов расходов
     */
    public List<ExpenseType> getAllExpenseTypes() {
        return expenseTypeRepo.findAll();
    }

    /**
     * Получение типа расходов по ID
     *
     * @param id идентификатор типа расходов
     * @return тип расходов
     */
    public ExpenseType getExpenseTypeById(Long id) {
        Optional<ExpenseType> optExpenseType = expenseTypeRepo.findById(id);
        return optExpenseType.orElse(null);
    }

    /**
     * Добавление нового типа расходов
     *
     * @param expenseType новый тип расходов
     * @return добавленный тип расходов
     */
    public ExpenseType addExpenseType(ExpenseType expenseType) {

        if (expenseTypeRepo.findAll().stream()
                .noneMatch(item -> item.getName().equals(expenseType.getName()))) {
            return expenseTypeRepo.save(expenseType);
        }
        return null;
    }

    public ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory) {

        if (expenseCategoryRepo.findAll().stream()
                .noneMatch(item -> item.getName().equals(expenseCategory.getName()))) {
            return expenseCategoryRepo.save(expenseCategory);
        }
        return null;
    }

    /**
     * Изменение данных типа расходов
     *
     * @param id                 идентификатор типа расходов
     * @param expenseTypeDetails типа расходов с обновленными данными,
     *                           (полученными из контроллера)
     * @return измененный тип расхода
     */

    public ExpenseType updateExpenseType(Long id, ExpenseType expenseTypeDetails) {
        Optional<ExpenseType> optionalExpenseType = expenseTypeRepo.findById(id);
        if (optionalExpenseType.isPresent()) {
            ExpenseType expenseType = optionalExpenseType.get();
            expenseType.setName(expenseTypeDetails.getName());
            return expenseTypeRepo.save(expenseType);
        } else {
            throw new IllegalArgumentException("Тип расходов с id" + id + "не найден");
        }
    }

    /**
     * Удаление типа расхода по ID
     *
     * @param id идентификатор типа расхода
     */
    public void deleteExpenseType(Long id) {
        expenseTypeRepo.deleteById(id);
    }

    /**
     * Получение списка типов расходов по Id категории расходов
     *
     * @param categoryId идентификатор категории расходов
     * @return список типов расходов
     */
    public List<ExpenseType> getExpenseTypesByCategoryId(Long categoryId) {
        return expenseTypeRepo.getExpenseTypesByExpenseCategoryId(categoryId);
    }

    public ExpenseCategory getExpenseCategoryById(Long categoryId){
        return expenseCategoryRepo.getReferenceById(categoryId);
    }

    /**
     * Генерация категорий объектов при первом запуске приложения
     */
    public void generateExpenseCategories() {
        if (expenseCategoryRepo.findAll().isEmpty()) {

            addExpenseCategory(new ExpenseCategory("Неопределена", "Используется также программой при корректировке записей"));
            addExpenseCategory(new ExpenseCategory("Покупки", "Приобретение товаров и объектов"));
            addExpenseCategory(new ExpenseCategory("Услуги", "Оплата услуг"));
        }

    }
    /** Поиск категории затрат по ее названию */
    private ExpenseCategory getExpenseCategoryByName(String categoryName){
        return expenseCategoryRepo.findAll().stream()
                        .filter(expCat -> expCat.getName().equals(categoryName)).findFirst().orElse(new ExpenseCategory());
            }


    public ExpenseType getExpenseTypeByName(String expenseTypeName){
        return expenseTypeRepo.findAll().stream()
                .filter(expType -> expType.getName().equals(expenseTypeName)).findFirst().orElse(new ExpenseType());
    }

    /**
     * Генерация типов затрат при первом запуске приложения
     */
    public void generateExpenseTypes() {
        if(expenseTypeRepo.findAll().isEmpty()){

            addExpenseType(new ExpenseType("Неопределено",getExpenseCategoryByName("Неопределена") ));
            addExpenseType(new ExpenseType("Оплата ЖКХ",getExpenseCategoryByName("Услуги") ));
            addExpenseType(new ExpenseType("Страхование",getExpenseCategoryByName("Услуги") ));
            addExpenseType(new ExpenseType("Покупка продуктов",getExpenseCategoryByName("Покупки") ));
            addExpenseType(new ExpenseType("Покупка мебели",getExpenseCategoryByName("Покупки") ));
            addExpenseType(new ExpenseType("Выплаты по кредитам",getExpenseCategoryByName("Услуги") ));
        }
    }



}