package com.capmkts.msrprocess.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.capmkts.msrprocess.bean.FileDownloadBean;
import com.capmkts.msrprocess.dao.FileDownloadBeanDAO;

@ManagedBean(name = "fileDownloadController")
@RequestScoped
public class FileDownloadController {
	private Long id; // +setter
	private StreamedContent file;

	public FileDownloadController() {
		try {
			
			FileDownloadBeanDAO fileDownloadDAO = new FileDownloadBeanDAO();
			
			FileDownloadBean fileUploadBean = fileDownloadDAO.getFileDownloadBeanData("1042");

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

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public InputStream getFileStream() {
		InputStream fileStream = null;
		byte[] fileByteArray = new byte[1024];

		try {
			fileByteArray = FileUtils.readFileToByteArray(new File(
					"C:\\Caps\\Docs\\ServicingFile_Success.csv"));
			fileStream = new ByteArrayInputStream(fileByteArray);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileStream;

	}
}
