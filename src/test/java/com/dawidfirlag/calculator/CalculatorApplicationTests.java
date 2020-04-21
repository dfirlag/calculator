package com.dawidfirlag.calculator;

import com.dawidfirlag.calculator.userinterfaces.http.CalculatorQueryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CalculatorApplicationTests {

	@Autowired
	private CalculatorQueryController controller;

	@Test
	void contextLoads() {
		assertThat(this.controller).isNotNull();
	}
}
