package com.capmkts.msrprocess.bean;

import java.io.Serializable;
import java.text.ParseException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import com.capmkts.msrprocess.util.DateUtil;

@ManagedBean(name="portfolio")
@RequestScoped
public class Portfolio implements Serializable{
	public String CMCCommitmentNumber;
	public String agencyCommitmentNumber;
    public String commitmentDate;
    public String expirationDate;
    public String originalBalance;
    public String currentBalance;
    public String remainingDays;
    public String currentStatus;
    public int extendCount;
    public String CMCCommitmentLetterFileName;
    public String agencyCommitmentLetterFileName;
    public String commitmentRequestFileName;
    public String servicingDataFileName;
    public String paFileName;
    
    DateUtil dateUtil = new DateUtil();
    
    public Portfolio(){
    	
    }
  
    public Portfolio(String CMCCommitmentNumber, String agencyCommitmentNumber, String commitmentDate, String expirationDate, 
    		String originalBalance, String currentBalance, String remainingDays, String currentStatus, 
    		int extendCount, String CMCCommitmentLetterFileName, String agencyCommitmentLetterFileName, String commitmentRequestFileName, 
			String servicingDataFileName, String paFileName) {
            this.CMCCommitmentNumber = CMCCommitmentNumber;
            this.agencyCommitmentNumber  = agencyCommitmentNumber;
            this.commitmentDate = commitmentDate;
            this.expirationDate = expirationDate;
            this.originalBalance = originalBalance;
            this.currentBalance = currentBalance;
            this.remainingDays = remainingDays;
            this.currentStatus = currentStatus;
            this.extendCount = extendCount; 
            this.CMCCommitmentLetterFileName = CMCCommitmentLetterFileName;
            this.agencyCommitmentLetterFileName = agencyCommitmentLetterFileName;
            this.commitmentRequestFileName = commitmentRequestFileName;
            this.servicingDataFileName = servicingDataFileName;
            this.paFileName = paFileName;
    }

    
    public int getExtendCount() {
        return extendCount;
	}
	
	public void setExtendCount(int ExtendCount) {
	        this.extendCount = ExtendCount;
	}

    public String getCMCCommitmentNumber() {
            return CMCCommitmentNumber;
    }

    public void setCMCCommitmentNumber(String CMCCommitmentNumber) {
            this.CMCCommitmentNumber = CMCCommitmentNumber;
    }
    
    public String getAgencyCommitmentNumber(){
    		return agencyCommitmentNumber;
    }
    
    public void setAgencyCommitmentNumber(String agencyCommitmentNumber){
    	this.agencyCommitmentNumber = agencyCommitmentNumber;
    }

    public String getcommitmentDate() throws ParseException {
//    		System.out.println("\b\bCommit Date: " +commitmentDate.substring(0, Math.min(commitmentDate.length(), 10)) + "\n\n");
            return dateUtil.formatPortfolioDate(commitmentDate.substring(0, Math.min(commitmentDate.length(), 10)));
    }

    public void setCommitmentDate(String commitmentDate) {
            this.commitmentDate = commitmentDate;
    }

    public String getExpirationDate() throws ParseException {
//    		System.out.println("\n\nExpired Date: " + expirationDate.substring(0, Math.min(expirationDate.length(), 10))+"\n\n");
            return dateUtil.formatPortfolioDate(expirationDate.substring(0, Math.min(expirationDate.length(), 10)));
    }

    public void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
    }

    public String getOriginalBalance() {
            return originalBalance;
    }

    public void setOriginalBalance(String originalBalance) {
            this.originalBalance = originalBalance;
    }

    public String getCurrentBalance() {
    	return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
    	this.currentBalance = currentBalance;
    }
    
    public String getRemainingDays() {
    	return remainingDays;
    }

    public void setRemainingDays(String remainingDays) {
    	this.remainingDays = remainingDays;
    }

    public String getCurrentStatus() {
    	return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
    	this.currentStatus = currentStatus;
    }
    
    public String getCMCCommitmentLetterFileName(){
    	return CMCCommitmentLetterFileName;
    }
    
    public void setCMCCommitmentLetterFileName(String CMCCommitmentLetterFileName){
    	this.CMCCommitmentLetterFileName = CMCCommitmentLetterFileName;
    }
    
    public String getAgencyCommitmentLetterFileName(){
    	return agencyCommitmentLetterFileName;
    }

    public void setAgencyCommitmentLetterFileName(String agencyCommitmentLetterFileName){
    	this.agencyCommitmentLetterFileName = agencyCommitmentLetterFileName;
    }
    
    public String getCommitmentRequestFileName(){
    	return commitmentRequestFileName;
    }

    public void setCommitmentRequestFileName(String commitmentRequestFileName){
    	this.commitmentRequestFileName = commitmentRequestFileName;
    }
    
    public String getServicingDataFileName(){
    	return servicingDataFileName;
    }

    public void setServicingDataFileName(String servicingDataFileName){
    	this.servicingDataFileName = servicingDataFileName;
    }
    
    public String getPaFileName(){
    	return paFileName;
    }

    public void setPaFileName(String paFileName){
    	this.paFileName = paFileName;
    }
    
    @Override
    public boolean equals(Object obj) {
            if(obj == null)
                    return false;
           
            if(!(obj instanceof Portfolio))
                    return false;
           
            Portfolio compare = (Portfolio) obj;
   
            return compare.CMCCommitmentNumber.equals(this.CMCCommitmentNumber);
    }

    @Override
    public String toString() {
    	//	System.out.println("\nDocs{" + "CMCCommitmentNumber=" + CMCCommitmentNumber + ", acl=" + acl + ", Commitment Request=" + manufacturer + ", color=" + color + ", price=" + price + '}');
    	return "Portfolio{" + "CMCCommitmentNumber=" + CMCCommitmentNumber + ", " +
    			"Commitment Date=" + commitmentDate + 
    			", Expiration Date=" + expirationDate + 
    			", Original Balance=" + originalBalance + 
    			", Current Balance=" + currentBalance +
    			", Remaining Days=" + remainingDays +
    			", Extend Count=" + extendCount +
    			", Current Status=" + currentStatus + '}';	
    }
}
