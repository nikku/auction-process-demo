package com.camunda.showcase.auction.web.ws;

import java.util.Date;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.camunda.showcase.auction.domain.Auction;
import com.camunda.showcase.auction.service.AuctionService;

/**
 *
 * @author nico.rehwaldt
 */
@WebService
public class AuctionWS {

  @Inject
  private AuctionService auctionService;

  @WebMethod
  public Long createAuction(String name, String description, Date date) {
    Auction auction = new Auction(name, description, date);

    return auctionService.createAuction(auction);
  }
}
