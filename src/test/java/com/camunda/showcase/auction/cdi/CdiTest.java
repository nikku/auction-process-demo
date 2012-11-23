package com.camunda.showcase.auction.cdi;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.activiti.engine.ProcessEngine;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CdiTest {

	@Deployment
	public static Archive<?> createDeployment() {

		return ShrinkWrap.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	private BeanManager beanManager;

	@Test
	public void testInjectBeanManager() {
		Assert.assertNotNull(beanManager);

		System.out.println(beanManager);
	}

//
//	@Inject
//	private MyBean myBean;
//
//	@Inject
//	private ProcessEngine processEngine;
//
//	@Inject
//	@Friendly
//	private Greeter friendlyGreeter;
//
//	@Inject
//	private Greeter unfriendlyGreeter;
//	@Test
//	public void testProcessEngineAPI() {
//		processEngine.getRuntimeService().createProcessInstanceQuery().list();
//	}
//
//	@Test
//	public void testInjectMyBean() {
//		Assert.assertNotNull(myBean);
//
//		System.out.println(myBean);
//	}
//
//	@Test
//	public void testFriendlyGreeterIsFriendly() {
//		Assert.assertEquals("Hi, how are you Klaus! :))))", friendlyGreeter.greet("Klaus"));
//	}
//
//	@Test
//	public void testUnfriendlyGreeterIsNotFriendly() {
//		Assert.assertEquals("Hi", unfriendlyGreeter.greet("Klaus"));
//	}
}
