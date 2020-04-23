package com.dawidfirlag.calculator.application;

import com.dawidfirlag.calculator.domain.expression.dto.ExpressionListDto;
import com.dawidfirlag.calculator.domain.expression.dto.ExpressionListElementDto;
import com.dawidfirlag.calculator.domain.expression.repository.ExpressionCrudRepository;
import com.dawidfirlag.calculator.infrastructure.model.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpressionQueryService {

    @Autowired
    ExpressionCrudRepository expressionCrudRepository;

    public ExpressionListDto getSavedExpressions() {
        List<Expression> expressions =  expressionCrudRepository.findAll();
        return new ExpressionListDto(
            expressions.size(),
            expressions
                .stream()
                .map(expression -> new ExpressionListElementDto(
                    expression.getId(),
                    expression.getExpression(),
                    expression.getResult())
                ).collect(Collectors.toList())
        );
    }
}
