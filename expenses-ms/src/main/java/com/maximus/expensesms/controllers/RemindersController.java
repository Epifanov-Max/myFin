package com.maximus.expensesms.controllers;


import com.maximus.expensesms.models.records.Periodicity;
import com.maximus.expensesms.models.records.Reminder;
import com.maximus.expensesms.services.ReminderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/reminders")
public class RemindersController {


    private final ReminderService reminderService;

    @GetMapping
    public List<Reminder> getAllReminders() {
        return reminderService.getAllReminders();
    }

    @PostMapping
    public void addReminder(@RequestBody Reminder reminder){
        reminderService.addReminder(reminder);
    }

    @GetMapping("/close-reminders")
    public List<String> notifications (){
        return reminderService.reminderNotifications();
    }
}
