package com.capmkts.msrprocess.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.capmkts.msrprocess.util.HibernateUtil;

public class RaviTest {
	
	public static void main(String[] args) throws Exception{
		System.out.println(org.hibernate.Version.getVersionString());
		String URL = "jdbc:sqlserver://localhost:1433;databaseName=master;"; 

		String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 

		String ID = "tester"; 

		String PW = "Passw0rd"; 

		// This is where to load the driver 
		Class.forName (DRIVER); 
		// This is where to connect 
		Connection link = DriverManager.getConnection(URL, ID, PW); 
		// In my case, I'm connecting to the URL, ID and PW where you are connecting the driver.
		
		System.out.println(" Got the connection "+link);
		
//		Session session = HibernateUtil.getSession();
//		Query query = session.createQuery("select count(*) from Holiday where holidayDate = :compareDate");
//		List list = query.list();
//		int count = Integer.parseInt(list.get(0).toString());
//		System.out.println("COUNT: " + count);
//		
		
	}	

}
