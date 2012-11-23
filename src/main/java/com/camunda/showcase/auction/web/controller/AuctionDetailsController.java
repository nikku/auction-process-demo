package com.camunda.showcase.auction.web.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.camunda.fox.webapp.faces.Util;
import com.camunda.showcase.auction.domain.Auction;
import com.camunda.showcase.auction.domain.Bid;
import com.camunda.showcase.auction.repository.AuctionRepository;
import com.camunda.showcase.auction.service.AuctionService;
import com.camunda.showcase.auction.service.BidException;

@Named
@ViewScoped
public class AuctionDetailsController implements Serializable {

  @Inject
  private AuctionRepository auctionRepository;
  
  @Inject
  private AuctionService auctionService;

  private Auction auction;
  private Bid highestBid;

  private int bid;
  private String bidderName;
  
  @Produces
  @RequestScoped
  @Named("detailsAuction")
  public Auction getAuctions() {
    return auction;
  }
  
  @Produces
  @Named("highestBid")
  public Bid getHighestBid() {
    return highestBid;
  }
  
  public void placeBid() {
    try {
      System.out.println(auction.getId() + "#" + bid + "#" + bidderName);
      
      long bidId = auctionService.placeBid(auction.getId(), bid, bidderName);
      highestBid = auctionService.findHighestBid(auction.getId());
      
      if (bidId != highestBid.getId()) {
        Util.addMessage(bidderName + ", your bid was added but you are not the highest bidder anymore");
      } else {
        Util.addMessage(bidderName + ", you are the highest bidder with your bid " + bid);
      }
      
    } catch (BidException e) {
      Util.addMessage("Could not place bid: " + e.getMessage());
    }
    
    Util.redirectTo("auction.jsf?auctionId=" + auction.getId());
  }
  
  public void setAuctionId(String auctionId) {
    
    boolean error = false;
    
    try {
      long id = Long.parseLong(auctionId);
      auction = auctionRepository.findById(id);
      highestBid = auctionService.findHighestBid(id);
    } catch (NumberFormatException e) {
      error = true;
    }
    
    if (auction == null || error) {
      Util.addMessage("Auction not found");
      Util.redirectTo("auctions.jsf");
    }
  }
  
  public int getBid() {
    return bid;
  }
  
  public void setBid(int bid) {
    this.bid = bid;
  }
  
  public String getBidderName() {
    return bidderName;
  }
  
  public void setBidderName(String bidderName) {
    this.bidderName = bidderName;
  }
}
