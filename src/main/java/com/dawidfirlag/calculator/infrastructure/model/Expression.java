package com.dawidfirlag.calculator.infrastructure.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "expressions")
public class Expression {
    @Id
    @GeneratedValue
    private long id;
    private String expression;
    private String result;
    private String created;

    public Expression() {
    }

    @PrePersist
    public void beforeCreate() {
        this.setCreated(Timestamp.valueOf(LocalDateTime.now()).toString());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
