package com.camunda.showcase.auction.persistence;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.camunda.showcase.auction.domain.Auction;
import com.camunda.showcase.auction.domain.Bid;
import com.camunda.showcase.auction.repository.AuctionRepository;

@RunWith(Arquillian.class)
public class AuctionRepositoryTest {

	@Deployment
	public static Archive<?> createDeployment() {

		return ShrinkWrap.create(WebArchive.class)

				// add beans.xml to activate CDI
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")

				// add marker file for process archive
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")

				.addClass(Auction.class)
				.addClass(Bid.class)
				.addClass(AuctionRepository.class);
	}

	@Inject
	private AuctionRepository auctionRepository;

	@PersistenceContext
	private EntityManager em;

	@After
	public void after() {
    auctionRepository.deleteAll();
	}

	@Test
	public void testEntityManagerNotNull() {
	  Assert.assertNotNull(em);
	}

	@Test
	public void testFoo() {

	}

	@Test
	public void testListAuction() {
	  List<Auction> resultList = auctionRepository.findAll();

	  Assert.assertEquals(0, resultList.size());
	}

  @Test
  public void testCreateAuctionConstraintViolation() {
    Auction auction1 = new Auction("A1", null, new Date());

    try {
      auctionRepository.saveAndFlush(auction1);

      Assert.fail("expected constraint violation");
    } catch (Exception e) {
      // anticipated
    }
  }

  @Test
  public void testCreateAuction() throws Exception {
    Auction auction1 = new Auction("A1", "ASD", new Date());
    Auction auction2 = new Auction("A2", "ASDF", new Date());
    Auction auction3 = new Auction("A3", "ASD", new Date());

    auctionRepository.saveAndFlush(auction1);
    auction1.setName("A!!");
    auctionRepository.saveAndFlush(auction2);
    auctionRepository.saveAndFlush(auction3);

    try {
      em.createQuery("SELECT a FROM Auction a WHERE a.name = :name", Auction.class)
        .setParameter("name", "A!!")
        .getSingleResult();

      Assert.fail("Expected exception");
    } catch (NoResultException e) {
      // anticipated
    }
  }


  @Test
  public void testGetActive() throws Exception {

    Date later = new Date(System.currentTimeMillis() + 20000);

    Auction auction1 = new Auction("A1", "ASD", new Date());
    Auction auction2 = new Auction("A2", "ASDF", later);
    auction2.setAuthorized(true);
    Auction auction3 = new Auction("A3", "ASD", later);

    auctionRepository.saveAndFlush(auction1);
    auctionRepository.saveAndFlush(auction2);
    auctionRepository.saveAndFlush(auction3);

    List<Auction> activeAuctions = auctionRepository.findAllActive();
    Assert.assertEquals(1, activeAuctions.size());
  }


  @Test
  public void testAddAndQueryBids() throws Exception {

    Auction auction = new Auction("A1", "ASD", new Date());
    auctionRepository.saveAndFlush(auction);

    Bid highestNoBids = auctionRepository.findHighestBidByAuctionId(auction.getId());
    Assert.assertNull(highestNoBids);

    Bid bid1 = new Bid(20, "Klaus");
    bid1.setAuction(auction);

    Bid bid2 = new Bid(30, "Harry");
    bid2.setAuction(auction);

    auction.getBids().add(bid1);
    auction.getBids().add(bid2);

    auctionRepository.saveUpdate(auction);

    Bid highestBid = auctionRepository.findHighestBidByAuctionId(auction.getId());
    Assert.assertNotNull(highestBid);

    Assert.assertEquals("Harry", highestBid.getBidderName());
  }

  @Test
  public void testUpdateAuction() throws Exception {
    Auction auction = new Auction("A1", "ASD", new Date());

    auctionRepository.saveAndFlush(auction);

    auction.setName("A!!");
    auctionRepository.saveUpdate(auction);

    em.createQuery("SELECT a FROM Auction a WHERE a.name = :name", Auction.class)
      .setParameter("name", "A!!")
      .getSingleResult();
    }
}