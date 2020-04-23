package com.dawidfirlag.calculator.domain.expression.dto;

import java.util.List;

public class ExpressionListDto {
    private final int count;
    private final List<ExpressionListElementDto> list;

    public ExpressionListDto(int count, List<ExpressionListElementDto> list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public List<ExpressionListElementDto> getList() {
        return list;
    }
}
