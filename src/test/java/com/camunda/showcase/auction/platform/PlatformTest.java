/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camunda.showcase.auction.platform;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.camunda.fox.platform.api.ProcessArchiveService;
import com.camunda.fox.platform.api.ProcessEngineService;
import com.camunda.fox.platform.spi.ProcessArchive;
import junit.framework.Assert;

/**
 *
 * @author nico.rehwaldt
 */
@RunWith(Arquillian.class)
public class PlatformTest {

  @EJB(lookup="java:global/camunda-fox-platform/process-engine/PlatformService!com.camunda.fox.platform.api.ProcessEngineService")
  private ProcessEngineService processEngineService;

  @EJB(lookup="java:global/camunda-fox-platform/process-engine/PlatformService!com.camunda.fox.platform.api.ProcessArchiveService")
  private ProcessArchiveService processArchiveService;

  @Deployment
  public static Archive<?> createDeployment() {
    return ShrinkWrap.create(WebArchive.class)
				// add beans.xml to activate CDI
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Test
  public void testGetEngines(){
    Assert.assertNotNull(processEngineService);

    System.out.println(processEngineService.getProcessEngineNames());
  }

  @Test
  public void testGetArchiveService(){
    Assert.assertNotNull(processArchiveService);

    System.out.println(processArchiveService.getInstalledProcessArchives());
  }
}
