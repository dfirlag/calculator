package com.dawidfirlag.calculator.userinterfaces.http;

import com.dawidfirlag.calculator.application.ExpressionCommandService;
import com.dawidfirlag.calculator.domain.expression.repository.ExpressionCrudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExpressionQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExpressionCrudRepository expressionCrudRepository;

    @Autowired
    private ExpressionCommandService expressionCommandService;

    @BeforeEach
    public void init() {
        expressionCrudRepository.deleteAll();
    }

    @Test
    public void testSaveExpression() throws Exception {
        String expression1 = "1+2*(2+3)";
        String expression2 = "2+2*(2+3)";

        expressionCommandService.saveExpression(expression1);
        expressionCommandService.saveExpression(expression2);

        this.mockMvc.perform(get("/expression/get-saved-expressions"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().json("{\"count\":2,\"list\":[{\"expression\":\"1+2*(2+3)\",\"result\":\"11.0\"},{\"expression\":\"2+2*(2+3)\",\"result\":\"12.0\"}]}"));
    }
}
