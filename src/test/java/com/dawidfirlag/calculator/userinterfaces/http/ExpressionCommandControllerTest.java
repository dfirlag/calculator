package com.dawidfirlag.calculator.userinterfaces.http;

import com.dawidfirlag.calculator.application.CalculatorQueryService;
import com.dawidfirlag.calculator.domain.expression.repository.ExpressionCrudRepository;
import com.dawidfirlag.calculator.domain.interpreter.dto.ResultDto;
import com.dawidfirlag.calculator.infrastructure.model.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExpressionCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExpressionCrudRepository expressionCrudRepository;

    @Autowired
    private CalculatorQueryService calculatorQueryService;

    @BeforeEach
    public void init() {
        expressionCrudRepository.deleteAll();
    }

    @Test
    public void testSaveExpression() throws Exception {
        String expression = "1+2*(2+3)";

        this.mockMvc.perform(
            put("/expression/save-expression")
            .param("expression", expression)
        )
            .andExpect(status().isCreated());

        Expression expressionModel = this.expressionCrudRepository.findByExpression(expression);

        ResultDto resultDto = this.calculatorQueryService.calculateExpressionFromString(expression);

        Assertions.assertEquals(expression, expressionModel.getExpression());
        Assertions.assertEquals(resultDto.getDouble(), Double.parseDouble(expressionModel.getResult()));

    }
}
