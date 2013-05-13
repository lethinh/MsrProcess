package com.capmkts.msrprocess.bean;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.capmkts.msrprocess.dao.AgencyCommitmentLetterDAO;
import com.capmkts.msrprocess.dao.CMCAgencyCommitNumberDAO;
import com.capmkts.msrprocess.dao.CMCFileDAO;
import com.capmkts.msrprocess.dao.CommitmentDataDAO;
import com.capmkts.msrprocess.dao.ExtensionRequestDAO;
import com.capmkts.msrprocess.dao.FileDownloadBeanDAO;
import com.capmkts.msrprocess.util.DateUtil;

@ManagedBean(name = "fileDownload")
@ViewScoped
public class FileDownload {
	private Long id; // +setter
	private StreamedContent file;
	private List<Portfolio> portfolios;

	private Portfolio selectedPortfolio;

	private Portfolio[] selectedPortfolios;

	private PortfolioDataModel portfolioModel;

	public FileDownload() {
		portfolios = new ArrayList<Portfolio>();
		
		populatePortfolios(portfolios, 50);

		portfolioModel = new PortfolioDataModel(portfolios);
		
		try {
			CMCFileDAO cmcFileDAO = new CMCFileDAO();
			CMCAgencyCommitNumberDAO cmcAgencyCommitNumberDAO = new CMCAgencyCommitNumberDAO();
			
			String CMCCommitmentLetterFileName, agencyCommitmentLetterFileName, 
				commitmentRequestFileName, servicingDataFileName, paFileName;
			
			Portfolio portfolio = portfolioModel.getRowData();
			
			System.out.println("\n\nAgency Commit File Name: " +portfolio.getAgencyCommitmentLetterFileName());
			System.out.println("\n\nCMC Commit File Name: " +portfolio.getCMCCommitmentLetterFileName());
			
			String cmcNumber = portfolio.getCMCCommitmentNumber();
			String agencyCommitmentID = cmcAgencyCommitNumberDAO
					.getAgencyCommitNumber(cmcNumber);
			
			String validNumber = cmcNumber;
			CMCCommitmentLetterFileName = cmcFileDAO.getFileName(cmcNumber, "CMC Commitment Letter");
			agencyCommitmentLetterFileName = cmcFileDAO.getFileName(cmcNumber, "Commitment Letter");
			commitmentRequestFileName = cmcFileDAO.getFileName(cmcNumber, "Commitment Request");
			servicingDataFileName = cmcFileDAO.getFileName(cmcNumber, "Servicing Data");
			paFileName = cmcFileDAO.getFileName(cmcNumber, "Purchase Advice");
			
			if (CMCCommitmentLetterFileName.equalsIgnoreCase("Not Available")){
    			CMCCommitmentLetterFileName = cmcFileDAO.getFileName(agencyCommitmentID, "CMC Commitment Letter");
    			validNumber = agencyCommitmentID;
    		}
			if (agencyCommitmentLetterFileName.equalsIgnoreCase("Not Received")){
    			agencyCommitmentLetterFileName = cmcFileDAO.getFileName(agencyCommitmentID, "Commitment Letter");
    			validNumber = agencyCommitmentID;
    		}
			if (commitmentRequestFileName.equalsIgnoreCase("Not Received")){
    			commitmentRequestFileName = cmcFileDAO.getFileName(agencyCommitmentID, "Commitment Request");
    			validNumber = agencyCommitmentID;
    		}
    		if (servicingDataFileName.equalsIgnoreCase("Not Received")){
    			servicingDataFileName = cmcFileDAO.getFileName(agencyCommitmentID, "Servicing Data");
    			validNumber = agencyCommitmentID;
    		}
    		if (paFileName.equalsIgnoreCase("Not Received")){
    			paFileName = cmcFileDAO.getFileName(agencyCommitmentID, "Purchase Advice");
    			validNumber = agencyCommitmentID;
    		}
			
			FileDownloadBeanDAO fileDownloadDAO = new FileDownloadBeanDAO();
			
			FileDownloadBean fileDownloadBean = fileDownloadDAO.getFileDownloadBeanData(validNumber);

			String contentType = "";
			
			if (fileDownloadBean.getFileType() != null) {
				if ((fileDownloadBean.getFileType().equalsIgnoreCase("xls"))
						|| (fileDownloadBean.getFileType()
								.equalsIgnoreCase("xlsx"))) {
					contentType = "application/vnd.ms-excel";
				}
				if (fileDownloadBean.getFileType().equalsIgnoreCase("dat")) {
					contentType = "application/octet-stream";
				}
				if (fileDownloadBean.getFileType().equalsIgnoreCase("csv")) {
					contentType = "text/csv";
				}
			}

			// InputStream stream = getFileStream();
			InputStream stream = new ByteArrayInputStream(
					fileDownloadBean.getFileContent());
			file = new DefaultStreamedContent(stream, contentType,
					fileDownloadBean.getFileName());

			 //InputStream stream = getFileStream();
			 //file = new DefaultStreamedContent(stream, "text/csv",  "downloaded_primefaces.csv");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public Portfolio[] getSelectedPortfolios() {
		return selectedPortfolios;
	}

	public void setSelectedPortfolios(Portfolio[] selectedPortfolio) {

		this.selectedPortfolios = selectedPortfolios;
	}

	public Portfolio getSelectedPortfolio() {
		return selectedPortfolio;
	}

	public void setSelectedPortfolio(Portfolio selectedPortfolio) {
		this.selectedPortfolio = selectedPortfolio;
	}

	private void populatePortfolios(List<Portfolio> list, int size) {

		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

		CMCFileDAO cmcFileDAO = new CMCFileDAO();
		CMCAgencyCommitNumberDAO cmcAgencyCommitNumberDAO = new CMCAgencyCommitNumberDAO();
		AgencyCommitmentLetterDAO agencyCommitmentLetterDAO = new AgencyCommitmentLetterDAO();
		CommitmentDataDAO commitmentDataDAO = new CommitmentDataDAO();

		DocsReceived docsReceived = new DocsReceived();
		List commitmentRequestStatusList;
		List cmcCommitNumbers = cmcAgencyCommitNumberDAO.getCMCCommitNumbers();
		
		String cmcNumber, commitStatus, agencyCommitmentID, commitDate, expirationDate;
		String CMCCommitmentLetterFileName, agencyCommitmentLetterFileName, commitmentRequestFileName, servicingDataFileName, paFileName;
		
		int extendCount = 0;
		int n = 1;
		String origCommitmentAmt, balance, servicingDataReceivedStatus, purchaseAdviceReceivedStatus;
		List agencyCommitmentIDAll;
		for (int i = 0; i < cmcCommitNumbers.size(); i++) {
//			System.out.println("\n\nCMCCommitNumbers.size: " +cmcCommitNumbers.size()+ "\n\n");
			
			cmcNumber = String.valueOf(cmcCommitNumbers.get(i));
			
			agencyCommitmentIDAll = cmcAgencyCommitNumberDAO.getAgencyCommitNumberAll(cmcNumber);
			
			System.out.println("\n\nAgencyCommitNumbers.size: " +agencyCommitmentIDAll.size()+ "\n\n");
			
			if (agencyCommitmentIDAll.size() > 1){
				agencyCommitmentID = agencyCommitmentIDAll.get(agencyCommitmentIDAll.size()-n).toString();
				n+=1;
				if (n>agencyCommitmentIDAll.size()){
					break;
				}
			}
			else{
				agencyCommitmentID = cmcAgencyCommitNumberDAO.getAgencyCommitNumber(cmcNumber);
			}
			commitStatus = cmcAgencyCommitNumberDAO
					.getCMCCommitStatus(cmcNumber);
			commitDate = agencyCommitmentLetterDAO
					.getCommitmentDate(agencyCommitmentID);
			expirationDate = agencyCommitmentLetterDAO
					.getExpirationDate(agencyCommitmentID);
			origCommitmentAmt = agencyCommitmentLetterDAO
					.getOrigCommitmentAmt(agencyCommitmentID);
			balance = String.valueOf(commitmentDataDAO
					.getTotalLoanAmount(agencyCommitmentID));

			extendCount = agencyCommitmentLetterDAO
					.getExtendCount(agencyCommitmentID);

			
			//Handle null commitment numbers
//    		if (!cmcNumber.equals("null")){
    			CMCCommitmentLetterFileName = cmcFileDAO.getFileName(cmcNumber, "CMC Commitment Letter");
    			agencyCommitmentLetterFileName = cmcFileDAO.getFileName(cmcNumber, "Commitment Letter");
    			commitmentRequestFileName = cmcFileDAO.getFileName(cmcNumber, "Commitment Request");
    			servicingDataFileName = cmcFileDAO.getFileName(cmcNumber, "Servicing Data");
    			paFileName = cmcFileDAO.getFileName(cmcNumber, "Purchase Advice");
//    		}
    		if (CMCCommitmentLetterFileName.equalsIgnoreCase("Not Available")){
    			CMCCommitmentLetterFileName = cmcFileDAO.getFileName(agencyCommitmentID, "CMC Commitment Letter");
    		}

    		if (agencyCommitmentLetterFileName.equalsIgnoreCase("Not Received")){
    			agencyCommitmentLetterFileName = cmcFileDAO.getFileName(agencyCommitmentID, "Commitment Letter");
    		}
    		if (commitmentRequestFileName.equalsIgnoreCase("Not Received")){
    			commitmentRequestFileName = cmcFileDAO.getFileName(agencyCommitmentID, "Commitment Request");
    		}
    		if (servicingDataFileName.equalsIgnoreCase("Not Received")){
    			servicingDataFileName = cmcFileDAO.getFileName(agencyCommitmentID, "Servicing Data");
    		}
    		if (paFileName.equalsIgnoreCase("Not Received")){
    			paFileName = cmcFileDAO.getFileName(agencyCommitmentID, "Purchase Advice");
    		}
			
			DateFormat formatter = null;
			Date commitDateObj = null;
			Date expirationDateObj = null;
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				commitDateObj = (Date) formatter.parse(commitDate);
				expirationDateObj = (Date) formatter.parse(expirationDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// Date dateToday = new Date();

			// Testing Purposes
			Calendar cal = Calendar.getInstance();
			// cal.set(2012, 9, 9);
			cal.add(Calendar.DATE, -1);
			Date d = cal.getTime();

			String checkExpiration = checkExpiration(commitDateObj,
					expirationDateObj, d);
			System.out.println("\nToday's Date: " + d);
			System.out.println("\n\nDays In Between: " + checkExpiration
					+ "\n\n");
			if (checkExpiration.equals("0")) {
				checkExpiration = "<1";
			}
			// Handle null values
			if (origCommitmentAmt.equals("null"))
				origCommitmentAmt = "0.00";
			if (balance.equals("null"))
				balance = "0.00";

			// Check to see if servicing files received
			commitmentRequestStatusList = cmcFileDAO
					.getCommitmentRequestList(cmcNumber);
			servicingDataReceivedStatus = docsReceived.getStatus(
					commitmentRequestStatusList, "servicing data");
			purchaseAdviceReceivedStatus = docsReceived.getStatus(
					commitmentRequestStatusList, "purchase advice");
			if (servicingDataReceivedStatus.equals("Received")
					&& purchaseAdviceReceivedStatus.equals("Received")) {
				// Check requested amount vs agency committed amount to make
				// sure they match
				if (!origCommitmentAmt.equals(balance)) {
					balance = String.valueOf(Double.valueOf(origCommitmentAmt)
							- Double.valueOf(balance));
				} else {
					balance = "0.00";
				}
			}

			list.add(new Portfolio(
					cmcNumber,
					agencyCommitmentID,
					commitDate,
					expirationDate,
					defaultFormat.format(Double.parseDouble(origCommitmentAmt)),
					defaultFormat.format(Double.parseDouble(balance)),
					checkExpiration, 
					commitStatus, 
					extendCount,
					CMCCommitmentLetterFileName,
					agencyCommitmentLetterFileName,
    				commitmentRequestFileName,
    				servicingDataFileName,
    				paFileName
					)
			);
		}
	}

	public String checkExpiration(Date commitDateObj, Date expirationDateObj,
			Date todayDate) {
		String expiration;
		long daysBetween;

		if (expirationDateObj.before(todayDate)) {
			expiration = "Expired";
		} else {
			daysBetween = DateUtil.getWorkingDaysBetweenTwoDates(
					expirationDateObj, todayDate);
			expiration = String.valueOf(daysBetween);
		}

		return expiration;
	}

	public PortfolioDataModel getPortfolioModel() {
		return portfolioModel;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Portfolio Selected",
				((Portfolio) event.getObject()).getCMCCommitmentNumber());

		Portfolio portfolio = ((Portfolio) event.getObject());
		processExtendRequest(portfolio, 7, 1);

		portfolios = new ArrayList<Portfolio>();

		populatePortfolios(portfolios, 50);

		portfolioModel = new PortfolioDataModel(portfolios);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void retrieveCMCCommitmentLetter() {

		System.out
				.println("\n*****************onRowSselect***************************!");
		
		System.out.println("\n\nTESTING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
		

//		try {
//
			Portfolio portfolio = portfolioModel.getRowData();
			System.out.println("CMC Commitment Number: " +portfolio.getCMCCommitmentNumber());
//			System.out
//					.println("\n*****************onRowSselect***************************!"
//							+ portfolio.getExtendCount());
//
//			if (portfolio.getExtendCount() == 0) {
//				processExtendRequest(portfolio, 7, 1);
//
//				portfolios = new ArrayList<Portfolio>();
//
//				populatePortfolios(portfolios, 50);
//
//				portfolioModel = new PortfolioDataModel(portfolios);
//
//				FacesMessage msg = new FacesMessage(
//						"Request Extended for 7 Days! for Portfolio CommitmentNumber: ",
//						portfolio.getCMCCommitmentNumber());
//				FacesContext.getCurrentInstance().addMessage(null, msg);
//			} else {
//				FacesMessage msg = new FacesMessage(
//						"Request is already Extended! It cannot be extended further! CMC Commitment #: ",
//						portfolio.getCMCCommitmentNumber());
//				FacesContext.getCurrentInstance().addMessage(null, msg);
//			}
//		} catch (Exception ex) {
//			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//					"Error", ex.getMessage());
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//			RequestContext context = RequestContext.getCurrentInstance();
//			context.execute("wvExceptionError.show()");
//
//		}
	}

	public void retrieveAgencyCommitLetter() {
		try {
			CMCFileDAO cmcFileDAO = new CMCFileDAO();
			CMCAgencyCommitNumberDAO cmcAgencyCommitNumberDAO = new CMCAgencyCommitNumberDAO();
			String agencyCommitmentLetterFileName;
			Portfolio portfolio = portfolioModel.getRowData();
			String cmcNumber = portfolio.getCMCCommitmentNumber();
			String agencyCommitmentID = cmcAgencyCommitNumberDAO
					.getAgencyCommitNumber(cmcNumber);
			
			String validNumber = cmcNumber;
			agencyCommitmentLetterFileName = cmcFileDAO.getFileName(cmcNumber, "Commitment Letter");
			System.out.println("\n\nagencyCommitmentLetterFileName: " +agencyCommitmentLetterFileName+ "\n\n");
			if (agencyCommitmentLetterFileName.equalsIgnoreCase("Not Received")){
    			agencyCommitmentLetterFileName = cmcFileDAO.getFileName(agencyCommitmentID, "Commitment Letter");
    			validNumber = agencyCommitmentID;
    		}
			
			FileDownloadBeanDAO fileDownloadDAO = new FileDownloadBeanDAO();
			
			FileDownloadBean fileUploadBean = fileDownloadDAO.getFileDownloadBeanData(validNumber);

			String contentType = "";
			
			if (fileUploadBean.getFileType() != null) {
				if ((fileUploadBean.getFileType().equalsIgnoreCase("xls"))
						|| (fileUploadBean.getFileType()
								.equalsIgnoreCase("xlsx"))) {
					contentType = "application/vnd.ms-excel";
				}
				if (fileUploadBean.getFileType().equalsIgnoreCase("dat")) {
					contentType = "application/octet-stream";
				}
				if (fileUploadBean.getFileType().equalsIgnoreCase("csv")) {
					contentType = "text/csv";
				}
			}

			// InputStream stream = getFileStream();
			InputStream stream = new ByteArrayInputStream(
					fileUploadBean.getFileContent());
			file = new DefaultStreamedContent(stream, contentType,
					fileUploadBean.getFileName());

			 //InputStream stream = getFileStream();
			 //file = new DefaultStreamedContent(stream, "text/csv",  "downloaded_primefaces.csv");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void retrieveCommitmentRequest() {

		System.out
				.println("\n*****************onRowSselect***************************!");

	}
	
	public void retrieveServicingData() {

		System.out
				.println("\n*****************onRowSselect***************************!");

	}
	
	public void retrievePAFileName() {

		System.out
				.println("\n*****************onRowSselect***************************!");

	}
	
	public void unProcessRequest() {

		System.out
				.println("\n*****************onRowUnselect***************************!");

		try {
			Portfolio portfolio = portfolioModel.getRowData();

			
			System.out
					.println("\n*****************onRowSselect***************************!"
							+ portfolio.getExtendCount());

			if (portfolio.getExtendCount() == 1) {
				processExtendRequest(portfolio, -7, 0);

				portfolios = new ArrayList<Portfolio>();

				populatePortfolios(portfolios, 50);
				FacesMessage msg = new FacesMessage(
						"Request Extension Cancelled! for Portfolio CommitmentNumber: ",
						portfolio.getCMCCommitmentNumber());
				FacesContext.getCurrentInstance().addMessage(null, msg);

				portfolioModel = new PortfolioDataModel(portfolios);
			} else {
				FacesMessage msg = new FacesMessage(
						"Request cannnot be processed! You can undo the request extension only if it is already extended! ",
						portfolio.getCMCCommitmentNumber());
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			/*
			 * Date newExpireDate = DateUtil.addDays( new
			 * Date(portfolio.getcommitmentDate()), -7);
			 * 
			 * System.out .println(
			 * "\n*****************onRowUnselect***************************!" +
			 * newExpireDate);
			 * 
			 * 
			 * if (newExpireDate.after(new Date(portfolio.getcommitmentDate())))
			 * { processExtendRequest(portfolio, -7);
			 * 
			 * portfolios = new ArrayList<Portfolio>();
			 * 
			 * populatePortfolios(portfolios, 50); FacesMessage msg = new
			 * FacesMessage( "Request Extension Cancelled!",
			 * portfolio.getCMCCommitmentNumber());
			 * FacesContext.getCurrentInstance().addMessage(null, msg);
			 * 
			 * portfolioModel = new PortfolioDataModel(portfolios); } else {
			 * FacesMessage msg = new FacesMessage(
			 * "Request Extension is not Cancelled! Expiry Date cannot be less tah Commitment Date!"
			 * , portfolio.getCMCCommitmentNumber());
			 * FacesContext.getCurrentInstance().addMessage(null, msg);
			 * 
			 * }
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error", ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("wvExceptionError.show()");

		}

	}

	public void onRowUnselect(UnselectEvent event) throws Exception {
		System.out
				.println("\n*****************onRowUnselect***************************!");

		FacesMessage msg = new FacesMessage("Portfolio Unselected",
				((Portfolio) event.getObject()).getExpirationDate());

		Portfolio portfolio = ((Portfolio) event.getObject());
		processExtendRequest(portfolio, -7, 0);

		portfolios = new ArrayList<Portfolio>();

		populatePortfolios(portfolios, 50);

		portfolioModel = new PortfolioDataModel(portfolios);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void processExtendRequest(Portfolio portfolio, int extensionDaysCount, int extendCount) {
		try {
			System.out.println("\nAccessing Extension Controller!");

			ExtensionRequestDAO extensionRequestDAO = new ExtensionRequestDAO();

			extensionRequestDAO.extensionRequest(portfolio, extensionDaysCount, extendCount);

		} catch (Exception ex) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error", ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("wvExceptionError.show()");
		}
	}

}
