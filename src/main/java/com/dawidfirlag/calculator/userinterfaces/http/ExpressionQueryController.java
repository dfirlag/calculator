package com.dawidfirlag.calculator.userinterfaces.http;

import com.dawidfirlag.calculator.application.ExpressionQueryService;
import com.dawidfirlag.calculator.domain.expression.dto.ExpressionListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/expression", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpressionQueryController {

    @Autowired
    ExpressionQueryService expressionQueryService;

    @GetMapping("/get-saved-expressions")
    public ExpressionListDto getSavedExpressions() {
        return expressionQueryService.getSavedExpressions();
    }
}
