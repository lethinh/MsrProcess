package com.capmkts.msrprocess.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

public class Docs implements Serializable {

        public String CMCCommitmentNumber;
        public String acl;
        public String commitmentRequest;
        public String servicingData;
        public String pa;
        public String agencyCommitmentLetterFileName;
        public String commitmentRequestFileName;
        public String servicingDataFileName;
        public String paFileName;
       
	public Docs(String CMCCommitmentNumber, String acl, String commitmentRequest, String servicingData, 
			String pa, String agencyCommitmentLetterFileName, String commitmentRequestFileName, 
			String servicingDataFileName, String paFileName) {
                this.CMCCommitmentNumber = CMCCommitmentNumber;
                this.acl = acl;
                this.commitmentRequest = commitmentRequest;
                this.servicingData = servicingData;
                this.pa = pa;
                this.agencyCommitmentLetterFileName = agencyCommitmentLetterFileName;
                this.commitmentRequestFileName = commitmentRequestFileName;
                this.servicingDataFileName = servicingDataFileName;
                this.paFileName = paFileName;
        }

        public String getCMCCommitmentNumber() {
                return CMCCommitmentNumber;
        }

        public void setCMCCommitmentNumber(String CMCCommitmentNumber) {
                this.CMCCommitmentNumber = CMCCommitmentNumber;
        }

        public String getAcl() {
                return acl;
        }

        public void setACL(String acl) {
                this.acl = acl;
        }

        public String getCommitmentRequest() {
                return commitmentRequest;
        }

        public void setCommitmentRequest(String commitmentRequest) {
                this.commitmentRequest = commitmentRequest;
        }

        public String getServicingData() {
                return servicingData;
        }

        public void setServicingData(String servicingData) {
                this.servicingData = servicingData;
        }

        public String getPa() {
        	return pa;
        }

        public void setPa(String pa) {
        	this.pa = pa;
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
               
                if(!(obj instanceof Docs))
                        return false;
               
                Docs compare = (Docs) obj;
       
                return compare.CMCCommitmentNumber.equals(this.CMCCommitmentNumber);
        }

//        @Override
//        public int hashCode() {
//                int hash = 1;
//               
//            return hash * 31 + CMCCommitmentNumber.hashCode();
//        }

    @Override
    public String toString() {
//    	System.out.println("\nDocs{" + "CMCCommitmentNumber=" + CMCCommitmentNumber + ", acl=" + acl + ", Commitment Request=" + manufacturer + ", color=" + color + ", price=" + price + '}');
        return "Docs{" + "CMCCommitmentNumber=" + CMCCommitmentNumber + ", Agency Commitment Letter=" + 
		acl + ", Commitment Request=" + commitmentRequest + ", Servicing Data=" + servicingData + 
		", Purchase Advice=" + pa + ",Agency Commitment Letter File Name=" +agencyCommitmentLetterFileName+"'}";
    }
}
