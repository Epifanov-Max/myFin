package com.maximus.expensesms.services;

import com.maximus.expensesms.models.records.PaymentRecord;
import com.maximus.expensesms.models.records.Periodicity;
import com.maximus.expensesms.models.records.Reminder;
import com.maximus.expensesms.repositories.ReminderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ReminderService {

    private final ReminderRepository reminderRepo;
    private final PaymentRecordsService paymentRecordsService;
    private final SubjectService subjectService;
    private final ExpenseTypeService expenseTypeService;


//    public void addReminder(Long paymentRecordId, Periodicity periodicity, LocalDate nextPaymentDate){
    public void addReminder(Reminder reminder){
        if (!reminder.getNextDate1().isBefore(LocalDate.now())) {
            PaymentRecord pmntRecord = paymentRecordsService.getRecordById(reminder.getExpenseRecordId());
            String subjectName = subjectService.getSubjectById(pmntRecord.getIdSubject()).getName();
            String expenseTypeName = expenseTypeService.getExpenseTypeById(pmntRecord.getIdExpenseType()).getName();
//            Reminder newReminder = new Reminder(reminder);
            reminder.setInputTime(new Date(System.currentTimeMillis()));
            reminder.setNextDate2();
            reminder.setSubjectName(subjectName);
            reminder.setExpenseTypeName(expenseTypeName);
            reminderRepo.save(reminder);
        }

    }



    public List<Reminder> getAllReminders (){
        return reminderRepo.findAll();
    }

    //TODO UPDATE REMINDER

    public List<Reminder> getCloseReminders(){

        List<Reminder> remindersToNotify = new ArrayList<>();
        for (Reminder reminder: getAllReminders() ){

            // если платеж просрочен
            if (LocalDate.now().isAfter(reminder.getNextDate1())) {
                delayNotification(reminder.getSubjectName(), reminder.getExpenseTypeName());

            } else if (LocalDate.now().isAfter(reminder.getNextDate1().minusDays(7))){
                remindersToNotify.add(reminder);
                log.info("Добавлена запись напоминания по платежу в список для вывода на экран:  объект " + reminder.getSubjectName() + ", тип расходов -"
                        + reminder.getExpenseTypeName() );
            }
        }
        return remindersToNotify;

    }

    public void reminderNotifications(){
        String notification = " Дата платежа  {%s} по объекту {%s}  наступит через {%d} дней";
        getCloseReminders().forEach(reminder -> {
            int daysLeft = Period.between(LocalDate.now(),reminder.getNextDate1()).getDays();

            System.out.printf((notification), reminder.getExpenseTypeName(), reminder.getSubjectName(), daysLeft);
        });
        //TODO отправить в контроллер сообщения с напоминаниями
    }

    public void delayNotification(String subjectName, String expenseTypeName){
        //TODO Отправить в контроллер сообщение о просрочке платежа
        System.out.println("Платеж по объекту " + subjectName + " типа '" + expenseTypeName +
                "' просрочен! " );
    }


}
