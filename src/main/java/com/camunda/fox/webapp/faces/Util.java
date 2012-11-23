package com.camunda.fox.webapp.faces;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Util {

  public static FacesContext facesContext() {
    return FacesContext.getCurrentInstance();
  }
  
  public static void addMessage(String message) {
    FacesContext facesContext = facesContext();
    if (facesContext != null) {
      facesContext.addMessage(null, new FacesMessage(message));
    }
  }
  
  public static ExternalContext externalContext() {
    FacesContext facesContext = facesContext();
    if (facesContext != null) {
      return facesContext().getExternalContext();
    } else {
      return null;
    }
  }
  
  public static void redirectTo(String url) {
    try {
      ExternalContext externalContext = externalContext();
      if (externalContext != null) {
        externalContext().getFlash().setKeepMessages(true);
        externalContext().redirect(url);
      }
    } catch (IOException e) {
      throw new FacesException(e);
    }
  }
}
