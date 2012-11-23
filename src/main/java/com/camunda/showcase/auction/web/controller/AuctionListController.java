package com.camunda.showcase.auction.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.camunda.showcase.auction.domain.Auction;
import com.camunda.showcase.auction.service.AuctionService;

@Named
@ViewScoped
public class AuctionListController implements Serializable {

  @Inject
  private AuctionService auctionService;

  @Produces
  @RequestScoped
  @Named("activeAutions")
  public List<Auction> getAuctionsAndBids() {
    return auctionService.getActiveAuctions();
  }
}
