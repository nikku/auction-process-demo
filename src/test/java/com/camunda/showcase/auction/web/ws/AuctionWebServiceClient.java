/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.camunda.showcase.auction.web.ws;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.WebServiceRef;

import com.camunda.showcase.auction.domain.Auction;
import com.camunda.showcase.auction.web.ws.gen.AuctionWSService;

/**
 *
 * @author nico.rehwaldt
 */
@Stateless
public class AuctionWebServiceClient {

  @WebServiceRef(wsdlLocation =
    "http://localhost:8080/auction-app/AuctionWS?wsdl")
  private AuctionWSService auctionWebService;

  public Long createAuction(Auction auction) {

    try {
      GregorianCalendar calendar = new GregorianCalendar();
      calendar.setTime(auction.getEndTime());

      return auctionWebService.getAuctionWSPort().createAuction(
          auction.getName(),
          auction.getDescription(),
          DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar)
      );
    } catch (DatatypeConfigurationException ex) {
      throw new IllegalArgumentException("Failed to create date time");
    }
  }
}
