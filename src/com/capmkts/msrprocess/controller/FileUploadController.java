package com.capmkts.msrprocess.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.io.FileUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import com.capmkts.msrprocess.data.User;
import com.capmkts.msrprocess.service.ServiceFactory;
import com.capmkts.msrprocess.test.CheckDB;
import com.capmkts.msrprocess.util.HibernateUtil;
import com.capmkts.msrprocess.validator.DataValidator;
import javax.faces.context.FacesContext;  
import javax.faces.event.ActionEvent;  

@ManagedBean(name = "fileUploadController")
//@ApplicationScoped
@ViewScoped
public class FileUploadController {

	private String uploadType;
	DataValidator dataValidator = null;

	
	public String getUploadType() {
		//System.out.println("  UPLOAD TYPE " + this.uploadType);
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public void upload(FileUploadEvent event) {
//		CheckDB checkDB = new CheckDB();
//		try{
//			checkDB.generateCSVCheck("622");  //pass variable here for patron specific
//		}catch(Exception e){}
		
		System.out.println("this.uploadTYpe " + this.uploadType);
		
		System.out.println("event.getFile() " + event.getFile());
		
		try{
			
			dataValidator = null;
		
			if (this.uploadType != null && !this.uploadType.equals("None")) {
				System.out.println(event.getFile().getFileName());
				String [] temp = event.getFile().getFileName().split("\\\\");
				
				File file = new File("C:\\filesUploaded\\" +temp[temp.length-1]);
			      try{
			         InputStream inputStream= event.getFile().getInputstream();
			         OutputStream out=new FileOutputStream(file);
			         byte buf[]=new byte[1024];
			         int len;
			         while((len=inputStream.read(buf))>0){
			            out.write(buf,0,len);
			         }
			         out.close();
			         inputStream.close();
			      }
			      catch (IOException e){
			      }
				
//				String fileName = event.getFile().getFileName();
//				File file = new File(fileName);
			      
				dataValidator = ServiceFactory.processFiles(this.uploadType, file);
				
				//Remove temp file
				if (file.delete())
					System.out.println(file.getName() + " is deleted!");
				else 
					System.out.println(file.getName() + " is NOT deleted!");
					
				System.out.println("\n\n****dataValidator.isValid()******" + dataValidator.isValid()+ "\n\n");
				System.out.println("\n\n****dataValidator******" + dataValidator.getMessageList().toString()+ "\n\n");
				
				if(!dataValidator.isValid()){
					FacesMessage msg = new FacesMessage(dataValidator.getMessageList().toString(), "");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				
				}
				else{
					FacesMessage msg = new FacesMessage(dataValidator.getMessageList().toString(), "");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				
				}

			}else{
				dataValidator.addMessage("Please select a valid upload type!");
//			    RequestContext context = RequestContext.getCurrentInstance(); 
//			    context.execute("wvConfirmSelectFileType.show()"); 
			}
		}catch(Exception ex){
			System.out.println(ex.toString());
			
			FacesMessage msg = new FacesMessage();
			if (ex.toString().contains("Office 2007")){
				msg.setDetail("Cannot Complete Request. XLSX extension is currently not supported. Please upload an XLS file.");
			}
			else if(ex.toString().contains("DefaultUploadedFile")){
				msg.setDetail("Please select an upload type when uploading your files.");
			}
			else if(ex.toString().contains("PRIMARY KEY constraint")){
				msg.setDetail("Agency Commitment Number already exists. Please check with CMC Funding if you believe this is incorrect.");
			}
			else if(ex.toString().contains("NumberFormatException: For input string:")){
				msg.setDetail("Invalid Commitment Request. Please check your file for correct formatting. Refer to manual if needed.");
			}
			else if(ex.toString().contains("Cannot get a numeric value from a text cell")){
				msg.setDetail("Missing or Invalid SRP values/column");
			}
			else{
				msg.setDetail("Internal Error. Please contact CMC support.");
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
			/*RequestContext context = RequestContext.getCurrentInstance(); 
		    context.execute("wvExceptionError.show()");*/
		}

	}
	
	public void upload(){
		
		System.out.println("****dataValidator.isValid()******" + dataValidator.isValid());
		System.out.println("****dataValidator******" + dataValidator.getMessageList().toString());
		
		if(!dataValidator.isValid()){
			FacesMessage msg = new FacesMessage(dataValidator.getMessageList().toString(), "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
		}
		else{
			FacesMessage msg = new FacesMessage(dataValidator.getMessageList().toString(), "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
		}
		
	}
	
	/** The following are error variables */
	//wv30PortfolioCap
	//wvInvalidCMCState
	//wvMonthlyLoanCap
	//wvPortCapCalifornia

	public void requestType(ValueChangeEvent event) {

		this.uploadType = event.getNewValue().toString();
		
	}
}
