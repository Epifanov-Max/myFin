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

/** Веб-контроллер записей доходов */
@Data
@RequiredArgsConstructor
@Controller
@RequestMapping
public class WebRevenueController {

    private final WebRevenueService webRevenueServiceMain;

    /** Передача формы представления списка доходов */
    @GetMapping("/revenues-list")
    public ModelAndView showData() {
        ModelAndView mav = new ModelAndView("revenues-list");
        List<RevenueRecord> listRevenues = webRevenueServiceMain.getAllRevenueRecords();
        mav.addObject("revenues", listRevenues);
        Map<Long, String> map = new HashMap<>(webRevenueServiceMain.mapRevenueStringRecords());
        mav.addObject("stringValue", map);
        return mav;
    }

    /** Получение списка типов доходов */
    @GetMapping("/revenues-list/revenue-types")
    public ResponseEntity<List<RevenueType>> getAllRevenueTypes() {
        return new ResponseEntity<>(webRevenueServiceMain.getAllRevenueTypes(), HttpStatus.OK);
    }

    /** Передача формы представления добавления записи дохода */
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

    /** Передача данных для сохранение новой записи доходов и возврат на страницу списка доходов*/
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

    /** Передача формы представления обновления записи дохода на базе формы добавления записи дохода  */
    @GetMapping("/revenues-list/updateData")
    public ModelAndView updateDataForm(@RequestParam("id") Long revenueRecordId) {
        ModelAndView mav = new ModelAndView("add-revenue-form");
        RevenueRecord updRecord = webRevenueServiceMain.getRevenueRecordById(revenueRecordId);
        List<RevenueType> listRevenueType = webRevenueServiceMain.getAllRevenueTypes();
        String page_name = "Обновить данные по записи";
        mav.addObject("page_name", page_name);
        mav.addObject("revenueRecord", updRecord);
        mav.addObject("revenueTypes", listRevenueType);
        return mav;
    }

    /** Передача формы представления списка типов доходов */
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

    /** Передача формы представления добавления списка типов доходов */
    @GetMapping("/revenue-types/save-revenuetype")
    public ModelAndView showRevenueTypesForm() {
        ModelAndView mav = new ModelAndView("add-revenuetype-form");
        RevenueType newRevType = new RevenueType(0L,"","");
        String page_name = "Введите новый тип дохода";
        mav.addObject("revenueType", newRevType);
        mav.addObject("page_name", page_name);
        return mav;
    }

    /** Передача данных для сохранения нового типа доходов и возврат на страницу списка типов доходов */
    @PostMapping(path = "/revenue-types/save-revenuetype")
    public String addRevenueType( RevenueType revenueType) {
        webRevenueServiceMain.addRevenueType(revenueType);
        return "redirect:/revenues/settings";
    }

    /** Передача формы представления обновления типов доходов на базе формы добавления типа доходов  */
    @GetMapping("/revenue-types/updateData")
    public ModelAndView updateRevenueDataForm(@RequestParam("id") Long revenueTypeId) {
        ModelAndView mav = new ModelAndView("add-revenuetype-form");
        RevenueType updRevenueType = webRevenueServiceMain.getRevenueTypeById(revenueTypeId);
        String page_name = "Обновить данные по записи";
        mav.addObject("page_name", page_name);
        mav.addObject("revenueType", updRevenueType);
        return mav;
    }

}