package com.maximus.webms.controllers;

import com.maximus.webms.configurations.MvcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = MvcConfig.class)
@WebMvcTest(WebBalanceController.class)
class WebBalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void showDataTest() throws Exception {
        this.mockMvc.perform(get("/balance-list"))
                .andExpect(status().isOk())
                .andExpect(view().name("balance-list"))
                .andExpect(content()
                        .string(containsString("Сальдо")));
    }
}