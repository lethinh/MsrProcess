package com.capmkts.msrprocess.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.capmkts.msrprocess.controller.FileUploadController;
import com.capmkts.msrprocess.dao.UserDAO;
import com.capmkts.msrprocess.data.User;

//import com.capmkts.msrprocess.data.User;

@ManagedBean
@ApplicationScoped
public class UserManagedBean
{
    private String username;
    private String password;
    private User user = null;
    
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public void login() throws Exception
    {
    	if(UserDAO.isValidUser(getUsername(), getPassword()))
        {
    		user = new User();
    		user.setUserName(this.username);
    		user.setPassword(this.password);
    		
    		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    		externalContext.redirect("http://localhost:8080/MsrProcess/upload.html");
//    		return "home";
        }
        else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("username", new FacesMessage("Invalid UserName and Password"));
//            return "login";
        }
    }
    
    public User getUser(){
    	return this.user;
    }
}