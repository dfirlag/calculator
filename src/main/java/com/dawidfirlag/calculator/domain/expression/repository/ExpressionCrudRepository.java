package com.dawidfirlag.calculator.domain.expression.repository;

import com.dawidfirlag.calculator.infrastructure.model.Expression;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpressionCrudRepository extends CrudRepository<Expression, Long> {

    List<Expression> findAll();

    Expression save(Expression expression);

    Expression findByExpression(String expression);
}
