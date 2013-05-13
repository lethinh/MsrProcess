package com.capmkts.msrprocess.bean;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
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

import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.dao.AgencyCommitmentLetterDAO;
import com.capmkts.msrprocess.dao.CMCAgencyCommitNumberDAO;
import com.capmkts.msrprocess.dao.CMCFileDAO;
import com.capmkts.msrprocess.dao.CommitmentDataDAO;
import com.capmkts.msrprocess.dao.ExtensionRequestDAO;
import com.capmkts.msrprocess.dao.FileDownloadBeanDAO;
import com.capmkts.msrprocess.util.DateUtil;

@ManagedBean(name = "extension")
@ViewScoped
public class Extension implements Serializable {

	private List<Portfolio> portfolios;

	private Portfolio selectedPortfolio;

	private Portfolio[] selectedPortfolios;

	private PortfolioDataModel portfolioModel;

	public Extension() {
		portfolios = new ArrayList<Portfolio>();

		populatePortfolios(portfolios, 50);

		portfolioModel = new PortfolioDataModel(portfolios);
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
		List tempCMCCommitNumbers = cmcAgencyCommitNumberDAO.getCMCCommitNumbers();
		
		List<String> cmcCommitNumbers = new ArrayList<String>(new LinkedHashSet<String>(tempCMCCommitNumbers));
		String agencyCommitmentID;
		String cmcNumber, commitStatus, commitDate, expirationDate;
		String CMCCommitmentLetterFileName, agencyCommitmentLetterFileName, commitmentRequestFileName, servicingDataFileName, paFileName;
		int extendCount = 0;
		String origCommitmentAmt, balance, servicingDataReceivedStatus, purchaseAdviceReceivedStatus;
		for (int i = 0; i < cmcCommitNumbers.size(); i++) {

			cmcNumber = String.valueOf(cmcCommitNumbers.get(i));
			agencyCommitmentID = cmcAgencyCommitNumberDAO
					.getAgencyCommitNumber(cmcNumber);
			commitStatus = cmcAgencyCommitNumberDAO
					.getCMCCommitStatus(cmcNumber);
			System.out.println("\nAgency Commitment ID: " +agencyCommitmentID+ "\n\n");
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
			
			CMCCommitmentLetterFileName = cmcFileDAO.getFileName(cmcNumber, "CMC Commitment Letter");
			agencyCommitmentLetterFileName = cmcFileDAO.getFileName(cmcNumber, "Commitment Letter");
			commitmentRequestFileName = cmcFileDAO.getFileName(cmcNumber, "Commitment Request");
			servicingDataFileName = cmcFileDAO.getFileName(cmcNumber, "Servicing Data");
			paFileName = cmcFileDAO.getFileName(cmcNumber, "Purchase Advice");
			
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
		processExtendRequest(portfolio, MsrConstants.EXTENSION_DAYS, 1);

		portfolios = new ArrayList<Portfolio>();

		populatePortfolios(portfolios, 50);

		portfolioModel = new PortfolioDataModel(portfolios);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void processRequest() {

		System.out
				.println("\n*****************onRowSselect***************************!");

		try {

			Portfolio portfolio = portfolioModel.getRowData();
			System.out
					.println("\n*****************onRowSselect***************************!"
							+ portfolio.getExtendCount());

			if (portfolio.getExtendCount() == 0) {
				processExtendRequest(portfolio, MsrConstants.EXTENSION_DAYS, 1);

				portfolios = new ArrayList<Portfolio>();

				populatePortfolios(portfolios, 50);

				portfolioModel = new PortfolioDataModel(portfolios);

				FacesMessage msg = new FacesMessage(
						"Request Extended for 10 Days! for Portfolio CommitmentNumber: ",
						portfolio.getCMCCommitmentNumber());
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				FacesMessage msg = new FacesMessage(
						"Request is already Extended! It cannot be extended further! CMC Commitment #: ",
						portfolio.getCMCCommitmentNumber());
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception ex) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error", ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("wvExceptionError.show()");

		}

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
				processExtendRequest(portfolio, -MsrConstants.EXTENSION_DAYS, 0);

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
		processExtendRequest(portfolio, -MsrConstants.EXTENSION_DAYS, 0);

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
