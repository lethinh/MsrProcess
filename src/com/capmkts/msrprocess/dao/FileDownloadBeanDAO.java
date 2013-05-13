package com.capmkts.msrprocess.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.capmkts.msrprocess.bean.FileDownloadBean;
import com.capmkts.msrprocess.util.HibernateUtil;

public class FileDownloadBeanDAO {
	public FileDownloadBean getFileDownloadBeanData(String AgencyOrCmcCommitmentNumber){
		FileDownloadBean fileDownloadBean = new FileDownloadBean();
		fileDownloadBean.setFileName(getFileName(AgencyOrCmcCommitmentNumber));
		fileDownloadBean.setFileType(getFileType(AgencyOrCmcCommitmentNumber));
		fileDownloadBean.setFileContent(getFileData(AgencyOrCmcCommitmentNumber));
		
		return fileDownloadBean;
	}
	
	public byte[] getFileData(String commitNumber){
		Session session = HibernateUtil.getSession();
		List<byte[]> list = null;
		byte[] fileData = null;
		String commitType = getCommitNumberType(commitNumber);
		try{
			Query query = session.createQuery(
					"select fileData FROM CMCFile " +
					"WHERE "+commitType+" = '" +commitNumber+"'");
//			System.out.println("QUERY: " +query);
			list = query.list();
			if(!list.isEmpty()){
				fileData = list.get(0);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
//		System.out.println("\n\nFileData: " + fileData+ "\n\n");
		return fileData;
	}
	
	public String getFileType(String commitNumber){
		Session session = HibernateUtil.getSession();
		List list = null;
		String fileType = null;
		String commitType = getCommitNumberType(commitNumber);
		try{
			Query query = session.createQuery(
					"SELECT fileType FROM CMCFile " +
					"WHERE "+commitType+" = '" +commitNumber+"'");
			list = query.list();
			if(!list.isEmpty()){
				fileType = list.get(0).toString();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		System.out.println("\n\nFileType: " + fileType+ "\n\n");
		return fileType;
	}
	
	public String getFileName(String commitNumber){
		Session session = HibernateUtil.getSession();
		List list = null;
		String fileName = null;
		String commitType = getCommitNumberType(commitNumber);
		try{
			Query query = session.createQuery(
					"SELECT fileName FROM CMCFile " +
					"WHERE "+commitType+" = '" +commitNumber+"'");
			list = query.list();
			if(!list.isEmpty()){
				fileName = list.get(0).toString();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		System.out.println("\n\nFileName: " + fileName+ "\n\n");
		return fileName;
	}
	
	public String getCommitNumberType(String number){
		String type = null;
			if (number.length() > 5){
				type = "agencyCommitmentNumber";
			}
			else if (number.length() < 6){
				type = "cmcCommitmentNumber";
			}
			else{
				type = "unknown";
			}
		return type;
	}
	
	public static void main(String[] args) {
		String fileName;
		FileDownloadBeanDAO fileUploadBeanDAO = new FileDownloadBeanDAO();
		fileName = fileUploadBeanDAO.getFileName("1041");
		System.out.println("FileName: ");
		System.out.println(fileName);

	}
}
