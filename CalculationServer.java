package edu.depaul.se433.week3;

import java.util.HashMap;
import java.util.Map;

/**
 * The CalculationServer accepts a Query which represents
 * a request for a calculation.  It performs the calculation,
 * if it is valid and results a Response containing the
 * answer.
 * 
 * For the sake of demonstration, assume this server is complicated to setup and run.
 * We definitely want to use mocks of this server in our unit tests.
 */
public class CalculationServer {
	
	private Map<String, Operation> operations;
	
	public CalculationServer() {
		loadOperations();
	}
	
	public CalculationServer(Map<String, Operation> operations) {
		this.operations = operations;
	}

	public Response calculate(Query q) {
		try {
			Operation o = findOperation(q.operation());
			if (o == null) {
				return failed(StatusType.UNKNOWN_OPERATION, "Operation: " + q.operation() + "was not found");
			}
			double result = o.execute(q.leftValue(), q.rightValue());
			return success(result);
		} catch (Exception e) {
			return failed(e);
		}
	}
	
	private Operation findOperation(String operationName) {
		return operations.get(operationName);
	}
	
	private Response success(double value) {
		return new Response(StatusType.OK, value);
	}
	
	private Response failed(Exception e) {
		return new Response(e.getMessage());
	}
	
	private Response failed(StatusType status, String message) {
		return new Response(status, message);
	}
	
	private void loadOperations() {
		operations = new HashMap<>();
		operations.put("ADD", new Add());
		operations.put("DIVIDE", new Divide());
	}
}
