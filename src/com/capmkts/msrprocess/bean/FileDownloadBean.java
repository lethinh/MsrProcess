package com.capmkts.msrprocess.bean;

public class FileDownloadBean {

   private String fileType = "";
   private String fileName = "";
   private byte[] fileContent;
   
   
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

   
}