package com.capmkts.msrprocess.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.capmkts.msrprocess.bean.Extension;
import com.capmkts.msrprocess.bean.Portfolio;
import com.capmkts.msrprocess.dao.ExtensionRequestDAO;


@ManagedBean(name = "extendController")
@RequestScoped
public class ExtendController {
	public void processExtendRequest() {
		try{
			System.out.println("\nAccessing Extension Controller!");
			Extension extension = new Extension();
			Portfolio[] selectedPortfolioArray = extension.getSelectedPortfolios();
		
			ExtensionRequestDAO extensionRequestDAO = new ExtensionRequestDAO();
			
//			for(Portfolio portfolio : selectedPortfolioArray){
//				extensionRequestDAO.extensionRequest(portfolio);
//			}
		}catch(Exception ex){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			RequestContext context = RequestContext.getCurrentInstance(); 
		    context.execute("wvExceptionError.show()");
		}
	}
}
