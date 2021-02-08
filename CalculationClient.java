package edu.depaul.se433.week3;

/**
 * Handles calls to a CalculationServer.  Knows how to formulate
 * correct queries and how to unpack results when they're received.
 */
public class CalculationClient {
	private CalculationServer server;
	private Logger logger;
	
	public CalculationClient(CalculationServer server, Logger logger) {
		this.server = server;
		this.logger = logger;
	}
	
	public double add(double a, double b) {
		Query q = new Query("ADD", a, b);
		Response r = server.calculate(q);
		if (r.getStatus().equals(StatusType.OK)) {
			return r.getResult();
		} else {
			logger.log("ADD failed: " + r.getMessage());
			throw new RuntimeException(r.getStatus().name());
		}
	}
	
	public double divide(double a, double b) {
		Query q = new Query("DIVIDE", a, b);
		Response r = server.calculate(q);
		if (r.getStatus().equals("OK")) {
			return r.getResult();
		} else {
			logger.log("DIVIDE failed: " + r.getMessage());
			throw new RuntimeException(r.getStatus().name());
		}
	}
}
