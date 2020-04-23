DROP TABLE expressions;

CREATE TABLE expressions (
    id IDENTITY,
    expression CLOB NOT NULL,
    result CLOB NOT NULL,
    created TIMESTAMP NOT NULL
);