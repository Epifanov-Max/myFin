package com.maximus.webms.controllers;

import com.maximus.webms.models.*;
import com.maximus.webms.services.WebRevenueService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
@Controller
@RequestMapping
public class WebRevenueController {

    private final WebRevenueService webRevenueServiceMain;

    @GetMapping("/revenues-list")
    public ModelAndView showData() {
        ModelAndView mav = new ModelAndView("revenues-list");
        List<RevenueRecord> listRevenues = webRevenueServiceMain.getAllRevenueRecords();
        mav.addObject("revenues", listRevenues);
        Map<Long, String> map = new HashMap<>(webRevenueServiceMain.mapPaymentStringRecords());
        mav.addObject("stringValue", map);

//        String resultSum = sumOfRevenues(getListOfPaymentRecordsIds(paymentRecordsService.getAllPaymentRecords()));
        String resultSum = "1000";
        mav.addObject("sum", resultSum);
        return mav;
    }

    @GetMapping("/revenues-list/revenue-types")
    public ResponseEntity<List<RevenueType>> getAllRevenueTypes() {
        return new ResponseEntity<>(webRevenueServiceMain.getAllRevenueTypes(), HttpStatus.OK);
    }

    @GetMapping("/add-revenue-record")
    public ModelAndView addRevenueRecordForm() {
        ModelAndView mav = new ModelAndView("add-revenue-form");
        RevenueRecord newRevenueRecord = new RevenueRecord(0L, 0L, null, "",  null, null);
        List<RevenueType> listRevenueType = webRevenueServiceMain.getAllRevenueTypes();
        String page_name = "Добавление доходной операции";
        mav.addObject("page_name", page_name);
        mav.addObject("revenueRecord", newRevenueRecord);
        mav.addObject("revenueTypes", listRevenueType);
        return mav;
    }

    @PostMapping(path = "/revenues-list/save-revenue-record")
    public String addRevenueRecord(RevenueRecord revenueRecord) {
        webRevenueServiceMain.addRevenueRecord(revenueRecord);
        return "redirect:/revenues-list";
    }

    @GetMapping("/revenues-list/deleteData")
    public String deleteRevenueRecord(@RequestParam("id") Long id){
        webRevenueServiceMain.deleteRevenueRecord(id);
        return "redirect:/revenues-list";
    }

    //TODO UPDATES
    @GetMapping("/revenues-list/updateData")
    public ModelAndView updateDataForm(@RequestParam("id") Long revenueRecordId) {
        ModelAndView mav = new ModelAndView("add-revenue-form");
        RevenueRecord updRecord = webRevenueServiceMain.getRevenueRecordById(revenueRecordId);
        List<RevenueType> listRevenueType = webRevenueServiceMain.getAllRevenueTypes();
        String page_name = "Обновить данные по записи";
        mav.addObject("page_name", page_name);
        mav.addObject("revenueRecord", updRecord);
        mav.addObject("revenueTypes", listRevenueType);

//        fileGateway.writeToFile("project_table_actions.txt", "Данные проекта с id='" + projectId + "' обновлены в " +
//                LocalDateTime.now());
        return mav;
    }

    @GetMapping("/revenues/settings")
    public ModelAndView showSettingsRevenues() {
        ModelAndView mav = new ModelAndView("settings-revenues");
        List<RevenueType> listRevenueTypes = webRevenueServiceMain.getAllRevenueTypes();
        RevenueType newRevType = new RevenueType(0L,"","");
        String page_name = "Типы доходов";
        mav.addObject("revenueType", newRevType);
        mav.addObject("page_name", page_name);
        mav.addObject("revenueTypes", listRevenueTypes);
        return mav;
    }

    @GetMapping("/revenue-types/save-revenuetype")
    public ModelAndView showRevenueTypesForm() {
        ModelAndView mav = new ModelAndView("add-revenuetype-form");
        RevenueType newRevType = new RevenueType(0L,"","");
        String page_name = "Введите новый тип дохода";
        mav.addObject("revenueType", newRevType);
        mav.addObject("page_name", page_name);
        return mav;
    }


    @PostMapping(path = "/revenue-types/save-revenuetype")
    public String addRevenueType( RevenueType revenueType) {
        webRevenueServiceMain.addRevenueType(revenueType);
        return "redirect:/revenues/settings";
    }

    @GetMapping("/revenue-types/updateData")
    public ModelAndView updateRevenueDataForm(@RequestParam("id") Long revenueTypeId) {
        ModelAndView mav = new ModelAndView("add-revenuetype-form");
        RevenueType updRevenueType = webRevenueServiceMain.getRevenueTypeById(revenueTypeId);
        String page_name = "Обновить данные по записи";
        mav.addObject("page_name", page_name);
        mav.addObject("revenueType", updRevenueType);

//        fileGateway.writeToFile("project_table_actions.txt", "Данные проекта с id='" + projectId + "' обновлены в " +
//                LocalDateTime.now());
        return mav;
    }

}