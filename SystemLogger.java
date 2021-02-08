package edu.depaul.se433.week3;

public class SystemLogger implements Logger {
	
	@Override
	public void log(String msg) {
		System.out.println(msg);
	}

}
