package edu.depaul.se433.week3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculationServerTest {

	@Test
	@DisplayName("Should successfully add 2 numbers")
	public void addTest() {
		CalculationServer server = new CalculationServer();
		Query add = new Query("ADD", 5, 7);
		Response resp = server.calculate(add);
		assertEquals(12.0, resp.getResult(), 1e-2);
	}
	
	@Test
	@DisplayName("Should handle unknown operation correctly")
	void unknownOperationTest() {
		CalculationServer server = new CalculationServer();
		Query add = new Query("XOR", 5, 7);
		Response resp = server.calculate(add);
		assertEquals("UNKNOWN_OPERATION", resp.getStatus().name());
	}
}
