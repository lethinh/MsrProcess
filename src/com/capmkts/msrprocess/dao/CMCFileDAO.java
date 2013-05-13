package com.capmkts.msrprocess.dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.capmkts.msrprocess.data.CMCFile;
import com.capmkts.msrprocess.util.HibernateUtil;

public class CMCFileDAO {

	public void saveFile(File file, String uploadType, Boolean valid,
			String inValidMessage, Integer cmcCommitmentNumber,Integer agencyCommitmentNumber) {

		CMCFile cmcFile = new CMCFile();
		cmcFile.setFileData(getBytes(file));
		cmcFile.setFileName(file.getName());
		cmcFile.setFileType(uploadType);
		cmcFile.setValid(valid);
		
		if(cmcCommitmentNumber != null){
			cmcFile.setCmcCommitmentNumber(cmcCommitmentNumber);
		}

		if(agencyCommitmentNumber != null){
			cmcFile.setAgencyCommitmentNumber(agencyCommitmentNumber);
		}
		if (!cmcFile.getValid()) {
			cmcFile.setInvalidMessage(inValidMessage);
		}
		cmcFile.setCreatedDate(new Date());

		HibernateUtil.save(cmcFile);
	}
	
	/**
	 * This method will save CMCMSRCommitmentLetter PDF into Database
	 * @param file
	 * @param uploadType
	 * @param valid
	 * @param inValidMessage
	 * @param cmcCommitmentNumber
	 * @param agencyCommitmentNumber
	 */
	public void saveFile(byte[] pdfFile, String fileName, String uploadType, Boolean valid,
			String inValidMessage, Integer cmcCommitmentNumber,Integer agencyCommitmentNumber) {

		CMCFile cmcFile = new CMCFile();
		cmcFile.setFileData(pdfFile);
		cmcFile.setFileName(fileName);
		
		if(uploadType != null){
			cmcFile.setFileType(uploadType);
		}
		
		cmcFile.setValid(valid);
		
		if(cmcCommitmentNumber != null){
			cmcFile.setCmcCommitmentNumber(cmcCommitmentNumber);
		}

		if(agencyCommitmentNumber != null){
			cmcFile.setAgencyCommitmentNumber(agencyCommitmentNumber);
		}
		if (!cmcFile.getValid()) {
			cmcFile.setInvalidMessage(inValidMessage);
		}
		cmcFile.setCreatedDate(new Date());

		HibernateUtil.save(cmcFile);
	}

	public void getFile() {

	}

	private byte[] getBytes(File file) {

		FileInputStream inputStream = null;
		byte[] fileContent = null;

		try {
			inputStream = new FileInputStream(file);
			fileContent = new byte[(int) file.length()];
			inputStream.read(fileContent);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return fileContent;
	}

	public static void main(String[] args) {
		File file = new File("C://Sample CommitRequest.xls");

		CMCFileDAO cmcFileDAO = new CMCFileDAO();

		byte[] bytes = cmcFileDAO.getBytes(file);

		String strFileContent = new String(bytes);

		System.out.println("File content : ");
		System.out.println(strFileContent);

	}
	
	public List getAgencyCommitmentLetterStatus(String agencyCommitmentNumber){
		Session session = HibernateUtil.getSession();
		List list = null;
//		String commitmentNumber = null;
		try{
			Query query = session.createQuery(
					"SELECT fileType FROM CMCFile " +
					"WHERE AgencyCommitmentNumber = '" +agencyCommitmentNumber+ "'");
			list = query.list();

		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return list;

	}
	
	public List getCommitmentRequestList(String cmcCommitmentNumber){
		Session session = HibernateUtil.getSession();
		List list = null;
//		String commitmentNumber = null;
		try{
			Query query = session.createQuery(
					"SELECT fileType FROM CMCFile " +
					"WHERE CMCCommitmentNumber = '" +cmcCommitmentNumber+ "'");
			list = query.list();

		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return list;

	}
	
	public String getFileName(String commitNumber, String fileType){
		Session session = HibernateUtil.getSession();
		List list = null;
		String fileName = null;
		String commitType = getCommitNumberType(commitNumber);
		try{
			Query query = session.createQuery(
					"SELECT fileName FROM CMCFile " +
					"WHERE "+commitType+" = '" +commitNumber+ "' AND fileType = '" +fileType+ "'");
			list = query.list();
			if(!list.isEmpty()){
				fileName = list.get(0).toString();
			}
			else{
				if (fileType.equalsIgnoreCase("CMC Commitment Letter")){
					fileName = "Not Available";
				}
				else{
					fileName = "Not Received";
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
//		System.out.println("\n\nFileName: " + fileName+ "\n\n");
		return fileName;
	}
	
	public String getErrorLog(){
		Session session = HibernateUtil.getSession();
		List list = null;
		String errorList = "";
//		String commitmentNumber = null;
		try{
			Query query = session.createQuery(
					"SELECT invalidMessage FROM CMCFile " +
					"WHERE createdDate = (SELECT MAX(createdDate) FROM CMCFile) " +
					"AND fileType = 'CMC_Servicing_Data_File_Agency_PA'");
			list = query.list();
			System.out.println("\n\nList: "+list+"\n\n");
			errorList = list.get(0).toString();
			System.out.println("\n\nerrorList: "+errorList+ "\n\n");
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		
		return errorList;

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
}
