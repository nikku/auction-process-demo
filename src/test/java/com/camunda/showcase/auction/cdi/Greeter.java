package com.camunda.showcase.auction.cdi;

public class Greeter {

	private boolean isFriendly;

	public Greeter(boolean isFriendly) {
		this.isFriendly = isFriendly;
	}

	public String greet(String person) {
		if (isFriendly) {
			return "Hi, how are you " + person + "! :))))";
		} else {
			return "Hi";
		}
	}
}
