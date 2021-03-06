package com.dawidfirlag.calculator.userinterfaces.http;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCalculatedExpressionShouldReturnJsonResult() throws Exception {
        this.mockMvc.perform(get("/calculator/get-calculated-expression")
            .queryParam("expression", URLEncoder.encode("1+2*(3-1)", StandardCharsets.UTF_8)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"double\":5.0}")));

        this.mockMvc.perform(get("/calculator/get-calculated-expression")
            .queryParam("expression", URLEncoder.encode("1+5*(2-1)", StandardCharsets.UTF_8)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"double\":6.0}")));
    }
}
