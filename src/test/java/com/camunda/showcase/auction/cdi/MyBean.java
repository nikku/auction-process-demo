package com.camunda.showcase.auction.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

@ApplicationScoped
@Default
public class MyBean {

	@Produces
	@Friendly
	public Greeter createFriendlyGreeter() {
		return new Greeter(true);
	}

	@Produces
	public Greeter createUnfriendlyGreeter() {
		return new Greeter(false);
	}
}
