package com.camunda.showcase.auction.web.rest.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.camunda.showcase.auction.domain.Auction;
import com.camunda.showcase.auction.domain.Bid;

public class AuctionDTO {

  private Long id;
  
  private String name;
  private Date endTime;
  private String description;
  
  private boolean authorized;
  
  private int higestBid;
  private String highestBidder;
  
  public AuctionDTO(Auction auction) {
    this.name = auction.getName();
    this.endTime = auction.getEndTime();
    this.authorized = auction.isAuthorized();
    this.description = auction.getDescription();
    this.id = auction.getId();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void setAuthorized(boolean authorized) {
    this.authorized = authorized;
  }
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }

  public void setHighestBid(Bid bid) {
    this.higestBid = bid.getAmount();
    this.highestBidder = bid.getBidderName();
  }

  public int getHigestBid() {
    return higestBid;
  }
  
  public String getHighestBidder() {
    return highestBidder;
  }
  
  // static helpers ///////////////////////
  
  public static List<AuctionDTO> wrapAll(List<Auction> auctions) {
    ArrayList<AuctionDTO> results = new ArrayList<AuctionDTO>();
    for (Auction a: auctions) {
      results.add(new AuctionDTO(a));
    }
    
    return results;
  }
}
