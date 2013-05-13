package com.capmkts.msrprocess.controller;


import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
 
import org.primefaces.context.RequestContext;


@ManagedBean
@RequestScoped
public class ExtensionController{

	public void extend(ActionEvent event) {
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "System Error",  "Please try again later.");  
	     FacesContext.getCurrentInstance().addMessage(null, message);  
		
	}

}
