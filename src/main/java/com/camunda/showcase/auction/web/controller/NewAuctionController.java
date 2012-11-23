package com.camunda.showcase.auction.web.controller;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.camunda.fox.webapp.faces.Util;
import com.camunda.showcase.auction.domain.Auction;
import com.camunda.showcase.auction.service.AuctionService;

@Named
@RequestScoped
public class NewAuctionController {
	
  private Auction auction = new Auction();
  
  @Produces
  @Named("newAuction")
  @RequestScoped
  public Auction getAuction() {
    return auction;
  }
  
  @Inject
  private AuctionService auctionService;
	
	public void createAuction() {
	  
		auctionService.createAuction(auction);
		
		Util.addMessage("Auction created");
    Util.redirectTo("./newAuction.jsf");
	}
}
