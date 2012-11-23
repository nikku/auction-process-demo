package com.camunda.showcase.auction.web.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.camunda.showcase.auction.domain.Auction;
import com.camunda.showcase.auction.domain.Bid;
import com.camunda.showcase.auction.repository.AuctionRepository;
import com.camunda.showcase.auction.service.AuctionService;
import com.camunda.showcase.auction.web.rest.dto.AuctionDTO;

@Path("auction")
@Stateless
public class AuctionResource {

  @Inject
  private AuctionRepository auctionRepository;
  
  @Inject
  private AuctionService auctionService;
  
  @GET
  public List<AuctionDTO> list() {
    
    return AuctionDTO.wrapAll(auctionRepository.findAll());
  }
  
  @GET
  @Path("{id}")
  public AuctionDTO get(@PathParam("id") Long id) {
    Auction auction = auctionRepository.findById(id);
    
    if (auction != null) {

      AuctionDTO result = new AuctionDTO(auction);
      Bid highestBid = auctionService.findHighestBid(auction.getId());
      
      if (highestBid != null) {
        result.setHighestBid(highestBid);
      }
      
      return result;
    }
    
    throw new WebApplicationException(Response.Status.NOT_FOUND);
  }
}
