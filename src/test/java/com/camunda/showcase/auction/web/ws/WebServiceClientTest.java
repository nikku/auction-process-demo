package com.camunda.showcase.auction.web.ws;

import java.util.Date;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.camunda.showcase.auction.domain.Auction;
import com.camunda.showcase.auction.domain.Bid;
import com.camunda.showcase.auction.web.ws.gen.AuctionWSService;
import junit.framework.Assert;

/**
 *
 * @author nico.rehwaldt
 */
@Ignore
@RunWith(Arquillian.class)
public class WebServiceClientTest {

  @Deployment
  public static Archive<?> createDeployment() {
    return ShrinkWrap.create(WebArchive.class)

      // add beans.xml to activate CDI
      .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")

      // add process diagram
      .addAsResource("auction-process.bpmn")
      .addPackage(AuctionWSService.class.getPackage())
      .addClass(AuctionWebServiceClient.class)
      .addClass(Auction.class)
      .addClass(Bid.class);
  }

  @Inject
  private AuctionWebServiceClient auctionWebServiceClient;

  @Test
  public void testCreateAuction() {
    Auction testAuction = new Auction("My auction", "My spectacular auction details", new Date(System.currentTimeMillis() + 120000));
    Long auctionId = auctionWebServiceClient.createAuction(testAuction);

    Assert.assertNotNull(auctionId);
  }
}
