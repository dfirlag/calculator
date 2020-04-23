package com.dawidfirlag.calculator.userinterfaces.http;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorExceptionHandlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHandleInvalidExpressionException() throws Exception {
        this.mockMvc.perform(get("/calculator/get-calculated-expression")
            .queryParam("expression", URLEncoder.encode("1+2*(3-2)///", StandardCharsets.UTF_8)))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().json("{\"message\":\"Invalid expression: ///\"}"));
    }

    @Test
    public void testHandleDividedByZeroException() throws Exception {
        this.mockMvc.perform(get("/calculator/get-calculated-expression")
            .queryParam("expression", URLEncoder.encode("1+2*(3-2)/0", StandardCharsets.UTF_8)))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().json("{\"message\":\"Divided by zero\"}"));
    }
}
