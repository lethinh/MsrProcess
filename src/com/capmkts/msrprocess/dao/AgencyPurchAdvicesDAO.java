package com.capmkts.msrprocess.dao;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.capmkts.msrprocess.data.AgencyPurchAdvices;
import com.capmkts.msrprocess.data.CMCFile;
import com.capmkts.msrprocess.util.FileUtil;
import com.capmkts.msrprocess.util.HibernateUtil;

public class AgencyPurchAdvicesDAO {

	public void save(AgencyPurchAdvices datFile) {

		HibernateUtil.save(datFile);
	}
	
	public void saveFile(File csvFile, String uploadType, boolean valid,
			String inValidMessage, Integer cmcCommitmentNumber,int sellerNumber) {

		CMCFile cmcFile = new CMCFile();
		cmcFile.setFileData(FileUtil.getBytes(csvFile));
		cmcFile.setFileName(csvFile.getName());
		cmcFile.setFileType(uploadType);
		cmcFile.setValid(valid);
		
		if(cmcCommitmentNumber != null){
			cmcFile.setCmcCommitmentNumber(cmcCommitmentNumber);
		}
		
	
		if (!cmcFile.getValid()) {
			cmcFile.setInvalidMessage(inValidMessage);
		}
		cmcFile.setCreatedDate(new Date());

		HibernateUtil.save(cmcFile);
	}
	
	public void saveAll(List<AgencyPurchAdvices> datFileALL) {

		Session session = HibernateUtil.getSession();
		Transaction txn = null;
		try {
			System.out.println(" AgencyPurchAdvices.SAVEALL ");

			txn = session.beginTransaction();

			for (int i = 0; i < datFileALL.size(); i++) {
				session.save(datFileALL.get(i));
			}

			txn.commit();
			session.flush();

		} catch (Exception ex) {
			txn.rollback();
			
		}finally{
			session.close();
		}
	}
	
}
