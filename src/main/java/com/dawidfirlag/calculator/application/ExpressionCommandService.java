package com.dawidfirlag.calculator.application;

import com.dawidfirlag.calculator.domain.expression.repository.ExpressionCrudRepository;
import com.dawidfirlag.calculator.domain.interpreter.dto.ResultDto;
import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;
import com.dawidfirlag.calculator.infrastructure.model.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpressionCommandService {

    private final CalculatorQueryService calculatorQueryService;

    private final ExpressionCrudRepository expressionCrudRepository;

    @Autowired
    public ExpressionCommandService(
        CalculatorQueryService calculatorQueryService,
        ExpressionCrudRepository expressionCrudRepository
    ) {
        this.calculatorQueryService = calculatorQueryService;
        this.expressionCrudRepository = expressionCrudRepository;
    }

    @Transactional
    public Expression saveExpression(String expression)
        throws InvalidExpressionException, DividedByZeroException {
        ResultDto result = calculatorQueryService.calculateExpressionFromString(expression);
        Expression expressionModel = new Expression();
        expressionModel.setExpression(expression);
        expressionModel.setResult(Double.toString(result.getDouble()));
        return expressionCrudRepository.save(expressionModel);
    }

    @Transactional
    public void deleteExpression(Long id) {
        expressionCrudRepository.deleteById(id);
    }
}
