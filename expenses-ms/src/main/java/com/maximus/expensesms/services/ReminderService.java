package com.maximus.expensesms.services;

import com.maximus.expensesms.models.records.PaymentRecord;
import com.maximus.expensesms.models.records.Periodicity;
import com.maximus.expensesms.models.records.Reminder;
import com.maximus.expensesms.repositories.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReminderService {

    private final ReminderRepository reminderRepo;
    private final PaymentRecordsService paymentRecordsService;
    private final SubjectService subjectService;
    private final ExpenseTypeService expenseTypeService;


    public void addReminder(Long paymentRecordId, Periodicity periodicity, LocalDate nextPaymentDate){
        if (!nextPaymentDate.isBefore(LocalDate.now())) {
            Reminder newReminder = new Reminder(paymentRecordId,periodicity,nextPaymentDate);
            reminderRepo.save(newReminder);
        }

    }
    public List<Reminder> getAllReminders (){
        return reminderRepo.findAll();
    }

    //TODO UPDATE REMINDER

    public List<Reminder> getCloseReminders(){

        List<Reminder> remindersToNotify = new ArrayList<>();
        for (Reminder reminder: getAllReminders() ){
            PaymentRecord pmntRecord = paymentRecordsService.getRecordById(reminder.getExpenseRecordId());
            String subjectName = subjectService.getSubjectById(pmntRecord.getIdSubject()).getName();
            String expenseTypeName = expenseTypeService.getExpenseTypeById(pmntRecord.getIdExpenseType()).getName();
            // если платеж просрочен
            if (LocalDate.now().isAfter(reminder.getNextDate1())) {
                System.out.println("Платеж по объекту " + subjectName + " типа '" + expenseTypeName +
                        "' просрочен! " );

            } else if (LocalDate.now().isAfter(reminder.getNextDate1().minusDays(7))){
                remindersToNotify.add(reminder);
                



            }



        }

    }

}
