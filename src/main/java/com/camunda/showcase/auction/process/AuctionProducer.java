package com.camunda.showcase.auction.process;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.activiti.cdi.annotation.ProcessVariable;

import com.camunda.showcase.auction.domain.Auction;
import com.camunda.showcase.auction.repository.AuctionRepository;

@Named
@ApplicationScoped
public class AuctionProducer {

  @Inject
  private AuctionRepository auctionRepository;

  @Named
  @Produces
  public Auction getAuction(@ProcessVariable("auctionId") Object auctionId) {
    return auctionRepository.findById((Long) auctionId);
  }
}
