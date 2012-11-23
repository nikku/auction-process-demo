package com.camunda.showcase.auction.web.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.camunda.fox.webapp.faces.Util;
import com.camunda.showcase.auction.domain.Auction;
import com.camunda.showcase.auction.service.AuctionService;

@Named
@ViewScoped
public class AuthorizeAuctionController implements Serializable {

  @Inject
  private AuctionService auctionService;
  
  private String taskId;

  @Produces
  @RequestScoped
  @Named("authorizeAuction")
  public Auction getAuction() {
    return auctionService.findAuctionByTaskId(taskId);
	}
  
  public void authorize(boolean authorized, String taskId, String callbackUrl) {
    auctionService.authorizeAuction(taskId, authorized);
    
    Util.redirectTo(callbackUrl);
	}
  
  public void startTask(String taskId) {
    this.taskId = taskId;
  }
  
  public String getTaskId() {
    return taskId;
  }
  
  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }
}
