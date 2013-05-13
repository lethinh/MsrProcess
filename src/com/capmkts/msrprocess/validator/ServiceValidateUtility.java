package com.capmkts.msrprocess.validator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.BigDecimalConverter;


import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.dao.CommitmentDataDAO;
import com.capmkts.msrprocess.dao.CommitmentLetterDAO;
import com.capmkts.msrprocess.dao.CountyCodeDAO;
import com.capmkts.msrprocess.dao.CreditDataDAO;
import com.capmkts.msrprocess.dao.RefProgramDAO;
import com.capmkts.msrprocess.data.CreditData;
import com.capmkts.msrprocess.data.CreditDataManualCheck;
import com.capmkts.msrprocess.data.AgencyPurchAdvices;

/**
 * ServiceValidateUtility - It contains methods which are useful to validate CSV and DAT file records. 
 * @author Rama Yerramilli
 *
 */
public class ServiceValidateUtility {

	public static boolean mandateElementExists(int[] array, int input) {
		for (int value : array)
			if (value == input)
				return true;
		return false;
	}
	
	/**
	 * When the servdata is uploaded, the sum of Servdata.CURRENTPRINCIPALBALANCE grouped by the servdata.COMMITMENTID needs to be <= remaining commitment amount"
     *  IF sum of Servdata.CURRENTPRINCIPALBALANCE grouped by the
	 * servdata.COMMITMENTID is within tolerance, close the commitment
	 * @param sumCurrentPrincipalBalance
	 * @param commitmentId
	 * @return inRange
	 */
	public static boolean performCurrentPrincipalBalanceTolerance(CreditData creditData){
		boolean isToleranceMet = false;
		CreditDataDAO creditDataDAO = new CreditDataDAO();
		
//		BigDecimal sumCurrentPrincipalBalance = 
//				creditDataDAO.getSumCurrentPrincipalBalance(creditData.getCOMMITMENTID());
		
		BigDecimal sumRequestPrincipalBalance = 
				CommitmentDataDAO.getTotalLoanAmount(String.valueOf(creditData.getAGENCYCOMMITMENTID()));
		
		//Get Loan Amount from Commitment Data table and do the comparison and return the result.
		CommitmentLetterDAO commitmentLetterDAO = new CommitmentLetterDAO();
		BigDecimal cmcCommitmentAmount = commitmentLetterDAO.getCMCCommitmentAmount(creditData.getCOMMITMENTID());
		
		MathContext mc = new MathContext(BigDecimal.ROUND_DOWN);

		BigDecimal twoPercent = new BigDecimal(MsrConstants.PRINCIPAL_BALANCE_TOLERANCE);
		BigDecimal twoPercentBalanceValue = cmcCommitmentAmount.multiply(twoPercent, mc);
		
		System.out.println("\nsumRequestPrincipalBalance: " + sumRequestPrincipalBalance);
		System.out.println("\ntwoPercentBalanceValue: " + twoPercentBalanceValue);
		System.out.println("\ncmcCommitmentAmount: " + cmcCommitmentAmount);
		
		BigDecimal addValue = cmcCommitmentAmount.add(twoPercentBalanceValue);
		BigDecimal subtractValue = cmcCommitmentAmount.subtract(twoPercentBalanceValue);
		System.out.println("addValue: " + addValue);
		System.out.println("subtractValue: " + subtractValue);
		
		if(
				(addValue.compareTo(sumRequestPrincipalBalance) >= 0) && 					
				(subtractValue.compareTo(sumRequestPrincipalBalance) <= 0)
			){
			isToleranceMet = true;				
		}
		System.out.println("Tolerance Met: " + isToleranceMet);
		return isToleranceMet;
	}	
	
	/**
	 * This method will set Precision for a BigDecimal Field.
	 * @param inputValue
	 * @return
	 */
	public static BigDecimal getRoundedValue(BigDecimal inputValue){
		BigDecimal outputValue = inputValue;
		
		// These values required to set BigInteger Behavior.
		// At this point, we will set these values only in get methods.
		int scale = 2;
		RoundingMode roundingMode = RoundingMode.HALF_UP;
		
		try{
			if(inputValue != null){
				outputValue.setScale(scale, roundingMode);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return outputValue;
	}

	//SELECT CountyName FROM dbo.StateCountyCodes WHERE CountyCode = '057'
	/**
	 * Populate County Name in CreditData object based on CountyCode.
	 * @param creditData
	 */
	public static void setPropertyCounty(CreditData creditData){
			
		if(creditData.getCOUNTYCODE() != null) {
			try{
				int countyCode = Integer.parseInt(creditData.getCOUNTYCODE());
			}catch(Exception ex){				
				String countyCodeValue = CountyCodeDAO.getCountyCode(creditData.getCOUNTYCODE());
			  	creditData.setCOUNTYCODE(new String(countyCodeValue));			 
			}
		}
	}
	
	/**
	 * This method will get LOTYPE value.
	 * @param inputValue
	 * @return
	 */
	public static int getLoType(CreditData creditData){
		int loType = 0;
		//*LOTYPE ID can be found in table 'refProgram' using 'LOANPROGRAMID' from the Servicing File.
		//Call Database class to get LOTYPE from refProgram table using LOANPROGRAMID
		RefProgramDAO refProgramDAO = new RefProgramDAO();
		try{
			if(creditData.getLOANPROGRAMID() != null){				
				loType = refProgramDAO.getLoTypeByLoanProgramId(Integer.parseInt(creditData.getLOANPROGRAMID()));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		creditData.getLOANPROGRAMID();
		return loType;					
	}
		
	
	/**
	 * This method will set default format for a Date Field.
	 * @param inputValue
	 * @return
	 */
	public static Date getFormattedDate(Date inputValue){
		Date outputValue = inputValue;
		Calendar calTo = Calendar.getInstance();
		
		try{
			if(inputValue != null){
				calTo.setTime(inputValue);
				
				String pattern = "MM/dd/yyyy";
				DateFormat  format = new SimpleDateFormat(pattern);
				
				try {
				  
					String strOutputValue = format.format(inputValue);
					outputValue = new Date(strOutputValue);
					
					System.out.println(outputValue);
				  
				} catch (Exception e) {
				  e.printStackTrace();
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return outputValue;
	}
	
	/**
	 * This method will set default format for a Date Field.
	 * @param inputValue
	 * @return
	 */
	public static String getFormattedDateString(Date inputValue){
		String outputValue = null;
		
		try{
			if(inputValue != null){		
				
				String pattern = "MM/dd/yyyy";
				DateFormat  format = new SimpleDateFormat(pattern);
				
				try {
				  
					outputValue = format.format(inputValue);	
					System.out.println(outputValue);
				  
				} catch (Exception e) {
				  e.printStackTrace();
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return outputValue;
	}
	

	public static boolean mandateFieldExists(String[] array, String input) {
		for (String value : array)
			if (value.trim().equalsIgnoreCase(input.trim())){
				return true;
			}
		return false;
	}

	public static void setCsvFileBeanValue(CreditData csvFileBean,
			String _fieldName, String _value) {

		Class newClass = csvFileBean.getClass();
		Method[] methods = newClass.getDeclaredMethods();

		for (int i = 0; i < methods.length; i++) {

			Class[] dataType = methods[i].getParameterTypes();

			if (dataType.length > 0) {
				// System.out.println(" **** " + dataType[0].getSimpleName());
				String parameterDataType = dataType[0].getSimpleName();

				if (parameterDataType.equals("String")) {
					Object[] parameters = { _value };
					if (methods[i].getName().equalsIgnoreCase(
							"set" + _fieldName)) {
						try {
							methods[i].invoke(csvFileBean, parameters);
						} catch (Exception e) {
						}
						break;
					}
				} else if (parameterDataType.equals("Date")) {
					Date _valueDate = null;

					if (methods[i].getName().equalsIgnoreCase(
							"set" + _fieldName)) {

						try {
							_valueDate = new Date(_value);
						} catch (Exception e) {
							/*System.out
									.println("Date exception occured: Method name: "
											+ methods[i]
											+ "Value is: "
											+ _value);*/
						}
						Object[] parameters = { _valueDate };
						
						try {
							methods[i].invoke(csvFileBean, parameters);
							
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						break;
					}
				} else if (parameterDataType.equals("BigDecimal")) {

					BigDecimal _valueDecimal = null;
					if (methods[i].getName().equalsIgnoreCase(
							"set" + _fieldName)) {

						try {
							_valueDecimal = new BigDecimal(_value);
						} catch (Exception e) {
							/*System.out
									.println("BigDecimal exception occured: Method name: "
											+ methods[i]
											+ "Value is: "
											+ _value);*/
							_valueDecimal = null;
						}
						Object[] parameters = { _valueDecimal };

						try {
							methods[i].invoke(csvFileBean, parameters);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						break;
					}
				} else if (parameterDataType.equals("int")) {
					int _valueInt = 0;

					if (methods[i].getName().equalsIgnoreCase(
							"set" + _fieldName)) {

						try {
							_valueInt = Integer.parseInt(_value);
						} catch (Exception e) {
							/*System.out
									.println("int exception occured: Method name: "
											+ methods[i]
											+ "Value is: "
											+ _value);*/
						}
						Object[] parameters = { _valueInt };

						try {
							methods[i].invoke(csvFileBean, parameters);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						break;
					}

				}

			}
		}
	}

	public static void setDatFileBeanValue(AgencyPurchAdvices datFileBean,
			String _fieldName, String _value) {

		Class newClass = datFileBean.getClass();
		Method[] methods = newClass.getDeclaredMethods();

		for (int i = 0; i < methods.length; i++) {

			Class[] dataType = methods[i].getParameterTypes();

			if (dataType.length > 0) {
				// System.out.println(" **** " + dataType[0].getSimpleName());
				String parameterDataType = dataType[0].getSimpleName();

				if (parameterDataType.equals("String")) {
					Object[] parameters = { _value };
					if (methods[i].getName().equalsIgnoreCase(
							"set" + _fieldName)) {
						try {
							methods[i].invoke(datFileBean, parameters);
						} catch (Exception e) {
						}
						break;
					}
				} else if (parameterDataType.equals("Date")) {
					Date _valueDate = null;

					if (methods[i].getName().equalsIgnoreCase(
							"set" + _fieldName)) {

						try {
							_valueDate = new Date(_value);
						} catch (Exception e) {
							/*System.out
									.println("Date exception occured: Method name: "
											+ methods[i]
											+ "Value is: "
											+ _value);*/
						}
						Object[] parameters = { _valueDate };
						try {
							methods[i].invoke(datFileBean, parameters);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						break;
					}
				} else if (parameterDataType.equals("BigDecimal")) {

					BigDecimal _valueDecial = null;
					if (methods[i].getName().equalsIgnoreCase(
							"set" + _fieldName)) {

						try {
							_valueDecial = new BigDecimal(_value);
						} catch (Exception e) {
							/*System.out
									.println("BigDecimal exception occured: Method name: "
											+ methods[i]
											+ "Value is: "
											+ _value);*/
							_valueDecial = null;
						}
						Object[] parameters = { _valueDecial };

						try {
							methods[i].invoke(datFileBean, parameters);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						break;
					}
				} else if (parameterDataType.equals("int")) {
					int _valueInt = 0;

					if (methods[i].getName().equalsIgnoreCase(
							"set" + _fieldName)) {

						try {
							_valueInt = Integer.parseInt(_value);
						} catch (Exception e) {
							/*System.out
									.println("int exception occured: Method name: "
											+ methods[i]
											+ "Value is: "
											+ _value);*/
						}
						Object[] parameters = { _valueInt };

						try {
							methods[i].invoke(datFileBean, parameters);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						break;
					}

				}

			}
		}
	}
	
	/**
	 * This method will set Default Values to Bean.
	 * @param classObj
	 */
	public void setDefaultValuesToBean(Object classObj){
		try {
			
		for (Field field : classObj.getClass().getDeclaredFields()) {
			 Class t = field.getType();
			 Object v = field.get(classObj);
			
			 if(t == boolean.class && Boolean.FALSE.equals(v)) {
				 // Set default value
				 field.setBoolean(classObj, false);
			 }
			 //BigInteger, int
			 else if(t.isPrimitive() && ((Number) v).doubleValue() == 0){
				 // Set default value
				 field.set(classObj, 0);
			 }
			 //String
			 else if(!t.isPrimitive() && v == null){
				 // Set default value
				 field.set(classObj, null);				 
			 }
			 //Date
			 else if(!t.isPrimitive() && t == Date.class){
				 // Set default value
				 field.set(classObj, new Date("01/01/1700"));				 
			 }
			}  
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will format Default Values in Bean.
	 * @param classObj
	 */
	public void formatValuesInBean(Object classObj){
		try {
			
		for (Field field : classObj.getClass().getDeclaredFields()) {
			 Class t = field.getType();
			 Object v = field.get(classObj);
			
			 if(t == boolean.class && Boolean.FALSE.equals(v)) {
				 // Set default value
				 field.setBoolean(classObj, false);
			 }
			 //BigDecimal
			 else if(t.isPrimitive() && ((BigDecimal) v).doubleValue() == 0){
				 // Set default value
				 field.set(classObj, new BigDecimal(new BigInteger("0"), 2));
			 }
			 //int
			 else if(t.isPrimitive() && ((Number) v).doubleValue() == 0){
				 // Set default value
				 field.set(classObj, 0);
			 }
			 //String
			 else if(!t.isPrimitive() && v == null){
				 // Set default value
				 field.set(classObj, null);				 
			 }
			 //Date
			 else if(!t.isPrimitive() && t == Date.class){
				 // Set default value
				 field.set(classObj, new Date("01/01/1700"));				 
			 }
			}  
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Copy one bean properties to another bean. 
	 * To handle null values, data type classes have to be registered with BeanUtils.
	 * @param tempCSVFile
	 * @param csvFileBean
	 */
	public static void copyBean(Object tempCSVFile, Object csvFileBean) {
		
		try {
			Date defaultValue = null;
			DateConverter converter = new DateConverter(defaultValue);
			ConvertUtils.register(converter, java.util.Date.class);
			
			BigDecimal defaultDecimalValue = null;
			BigDecimalConverter bigDecimalConvertor = new BigDecimalConverter(defaultDecimalValue);
			
			ConvertUtils.register(bigDecimalConvertor, BigDecimal.class);
			
			BeanUtils.copyProperties(tempCSVFile, csvFileBean);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
