package com.capmkts.msrprocess.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

//@ManagedBean
//@RequestScoped
public class FileType {
	
	private List<String> uploadTypes = new ArrayList<String>();


	public List<String> getUploadTypes() {
		System.out.println(" RRRR - Inside get upload types");

		if(uploadTypes.size() == 0){
			uploadTypes.add("Commitment Request/Letter");
			uploadTypes.add("Servicing Data/ PA");
			uploadTypes.add("Loan Docs");

		}

		return uploadTypes;
	}

	public void setUploadTypes(List<String> uploadTypes) {

		this.uploadTypes = uploadTypes;
	}
	
	
	
	

}
