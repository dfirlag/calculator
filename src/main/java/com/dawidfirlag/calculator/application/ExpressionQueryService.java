package com.dawidfirlag.calculator.application;

import com.dawidfirlag.calculator.domain.expression.repository.ExpressionCrudRepository;
import com.dawidfirlag.calculator.domain.interpreter.dto.ResultDto;
import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;
import com.dawidfirlag.calculator.infrastructure.model.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpressionQueryService {

    @Autowired
    ExpressionCrudRepository expressionCrudRepository;

    public List<Expression> getSavedExpression() {
        return expressionCrudRepository.findAll();
    }
}
