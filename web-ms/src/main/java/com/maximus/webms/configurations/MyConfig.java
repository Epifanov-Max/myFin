package com.maximus.webms.configurations;


import com.maximus.webms.feignclients.WebBalanceFeignClient;
import com.maximus.webms.models.BalanceRecord;
import com.maximus.webms.services.SumRecords;
import com.maximus.webms.services.WebBalanceService;
import com.maximus.webms.services.WebExpenseService;
import com.maximus.webms.services.WebRevenueService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
@ComponentScan("com.maximus.webms")
public class MyConfig {

//    @Bean
//    public WebBalanceService getWebBalanceService(
//            WebBalanceFeignClient webBalanceFeignClient,
//            WebExpenseService webExpenseService,
//            WebRevenueService webRevenueService) {
//        return new WebBalanceService( webBalanceFeignClient, webExpenseService, webRevenueService);
//    }


}
