package com.dawidfirlag.calculator.userinterfaces.http;

import com.dawidfirlag.calculator.application.CalculatorQueryService;
import com.dawidfirlag.calculator.application.ExpressionCommandService;
import com.dawidfirlag.calculator.application.ExpressionQueryService;
import com.dawidfirlag.calculator.domain.expression.dto.ExpressionDto;
import com.dawidfirlag.calculator.domain.expression.repository.ExpressionCrudRepository;
import com.dawidfirlag.calculator.domain.interpreter.dto.ResultDto;
import com.dawidfirlag.calculator.infrastructure.model.Expression;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    private ExpressionCommandService expressionCommandService;

    @Autowired
    private ExpressionQueryService expressionQueryService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        expressionCrudRepository.deleteAll();
    }

    @Test
    public void testSaveExpression() throws Exception {
        String expression = "1+2*(2+3)";
        ExpressionDto expressionDto = new ExpressionDto(expression);

        this.mockMvc.perform(
            post("/expression/save-expression")
            .content(this.objectMapper.writeValueAsBytes(expressionDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
            .andExpect(status().isCreated());

        Expression expressionModel = this.expressionCrudRepository.findByExpression(expression);

        ResultDto resultDto = this.calculatorQueryService.calculateExpressionFromString(expression);

        Assertions.assertEquals(expression, expressionModel.getExpression());
        Assertions.assertEquals(resultDto.getDouble(), Double.parseDouble(expressionModel.getResult()));
    }

    @Test
    public void testDeleteExpression() throws Exception {
        String expression = "1+2*(2+3)";
        Expression expressionModel = this.expressionCommandService.saveExpression(expression);

        this.mockMvc.perform(
            delete("/expression/delete-expression/" + Long.toString(expressionModel.getId()))
        )
            .andExpect(status().isOk());

        Assertions.assertEquals(this.expressionQueryService.getSavedExpressions().getList().size(), 0);
    }
}
