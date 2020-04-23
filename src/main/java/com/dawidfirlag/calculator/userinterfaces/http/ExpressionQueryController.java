package com.dawidfirlag.calculator.userinterfaces.http;

import com.dawidfirlag.calculator.application.ExpressionQueryService;
import com.dawidfirlag.calculator.infrastructure.model.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/expression",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpressionQueryController {

    @Autowired
    ExpressionQueryService expressionQueryService;

    @GetMapping("/get-saved-expression")
    public List<Expression> saveExpression() {
        return expressionQueryService.getSavedExpression();
    }
}
