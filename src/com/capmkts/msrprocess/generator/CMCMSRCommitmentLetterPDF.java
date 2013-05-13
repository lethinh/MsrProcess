package com.capmkts.msrprocess.generator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.capmkts.msrprocess.constants.MsrConstants;
import com.capmkts.msrprocess.dao.CMCFileDAO;
import com.capmkts.msrprocess.dao.PricingGridDAO;
import com.capmkts.msrprocess.data.AgencyCommitmentLetter;
import com.capmkts.msrprocess.data.CMCMsrCommitmentLetter;
import com.capmkts.msrprocess.data.PatronCompany;
import com.capmkts.msrprocess.data.ServRateSheets;
import com.capmkts.msrprocess.util.DateUtil;
import com.capmkts.msrprocess.util.EmailUtil;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.TextField;

/**
 * CMCMSRCommitmentLetterPDF - This class populate field values in PDF File.
 * 
 * @author Rama Yerramilli
 * 
 */
public class CMCMSRCommitmentLetterPDF {
	
	 /** The original PDF file. */
    public static final String SRC_PDF_PATH = "CommitmentLetter.pdf";
//    		"resources/pdfs/CommitmentLetter.pdf";
    		
        
    
    PdfStamper stamper = null;
    
    /** The resulting pdf file with the field info. */
    ByteArrayOutputStream baos = new ByteArrayOutputStream();   
    
    // Create a reader to extract info
    PdfReader reader = null;
    
	/**
	 * 1. From PDF template it will create the PDF , populate the data in PDF
			using CMCMsrCommitmentLetter object.

		2. Store this PDF in CMCFiles tables with CMCCommitmentNumber &
			AgencyCommitNumber
	 */
	public void preparePDF(CMCMsrCommitmentLetter cmcMsrCommitmentLetter, BigDecimal bal, int count ) {
		DateUtil dateUtil = new DateUtil();
		try{			
			if(cmcMsrCommitmentLetter != null){
				//Source PDF Tempalte Path
				String srcPDFPath = "";
				Integer agencyCommitmentNumber =  null;
				
				reader = new PdfReader(SRC_PDF_PATH);
		        
		        if(reader != null){
			        //stamper = new PdfStamper(reader, new FileOutputStream("C://Caps//Docs//CommitmentLetter-Output.pdf"));
		        	stamper = new PdfStamper(reader, baos);
			        
			        if(stamper != null){
				        // Get the fields from the reader
				        AcroFields form = stamper.getAcroFields();
	
				        if(form != null){
					        //Seller Information
					        if(cmcMsrCommitmentLetter.getPatronCompany() != null){
					        	PatronCompany patronCompany = cmcMsrCommitmentLetter.getPatronCompany();
					        	
					        	if(patronCompany.getPatronCoName() != null){
					        		form.setField("PATRON_CONAME", patronCompany.getPatronCoName());
					        	}
					        	if(patronCompany.getContactName() != null){
					        		form.setField("CONTACT_NAME", patronCompany.getContactName());
					        	}
					        	if(patronCompany.getContactEmail() != null){
					        		form.setField("CONTACT_EMAIL", patronCompany.getContactEmail());
					        	}
					        	if(patronCompany.getContactPhone() != null){
					        		form.setField("CONTACT_PHONE", patronCompany.getContactPhone());
					        	}
					        } else{
					        	System.out.println("PatronCompany object is null ");
					        }
					        
					        //Commitment Details
					        form.setField("COMMITMENT_NUMBER", String.valueOf(cmcMsrCommitmentLetter.getCommitmentNumber()));
					        
					        AgencyCommitmentLetter agencyCommitmentLetter = cmcMsrCommitmentLetter.getAgencyCommitmentLetter();
					        
					        DecimalFormat formatter = new DecimalFormat("###,###,###,##0.00");
					        BigDecimal total = new BigDecimal("0.00");
					        
					        if(agencyCommitmentLetter != null){
					        	agencyCommitmentNumber = agencyCommitmentLetter.getAgencyCommitmentID();
					        	
								String strDate = "";
					        	try{
					        	 SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy, HH:mm");
								    Date now = new Date();
								    strDate = sdfDate.format(now);
								    System.out.println("" + strDate);
					        	}catch(Exception ex){
					        		//
					        	}
								
					        	if(agencyCommitmentLetter.getExpirationDate() != null){
					        		form.setField("COMMITMENT_TODAY_DATE", strDate);
					        	}
								
								if(agencyCommitmentLetter.getCommitmentDate() != null){
					        		form.setField("AGENCY_COMMITMENT_DATE", dateUtil.formatPDFDate(agencyCommitmentLetter.getCommitmentDate().toString()));
					        	}
								
					        	if(agencyCommitmentLetter.getExpirationDate() != null){
					        		form.setField("CMC_EXPIRY_DATE", dateUtil.formatPDFDate(agencyCommitmentLetter.getExpirationDate().toString()));
					        	}
					        	if(agencyCommitmentLetter.getDataDeliveryDate() != null){
					        		form.setField("DATA_DELIVERY_DATE", dateUtil.formatPDFDate(agencyCommitmentLetter.getDataDeliveryDate().toString()));
					        	}
					        	if(agencyCommitmentLetter.getTargetFundDate() != null){
					        		form.setField("TARGET_FUNDING_DATE", dateUtil.formatPDFDate(agencyCommitmentLetter.getTargetFundDate().toString()));
					        	}
					        	if(agencyCommitmentLetter.getRemittanceType() != null){
					        		form.setField("REMIT_TYPE_DESCRIPTION", agencyCommitmentLetter.getRemittanceType());
					        	}		
					        	form.setField("BUYER", MsrConstants.BUYER);

                                form.setField("BUYER_CONTACT", MsrConstants.BUYER_CONTACT);

                                form.setField("BUYER_EMAIL", MsrConstants.BUYER_EMAIL);

                                form.setField("BUYER_PHONE", MsrConstants.BUYER_PHONE);
                                
					        	if(cmcMsrCommitmentLetter.getUpb() != null){
					        		System.out.println("\n\nBEFORE CONVERSION: " + bal.setScale(2, cmcMsrCommitmentLetter.getUpb().ROUND_HALF_DOWN).toPlainString() +"\n\n");
					        		total = bal.setScale(2, cmcMsrCommitmentLetter.getUpb().ROUND_HALF_DOWN);
					        		System.out.println("\n\nAfter Conversion: " +formatter.format(total));
					        		form.setField("UNPAID_PRINCIPAL_BALANCE", "$" + formatter.format(total.doubleValue()));
					        	}					        	
					        						        	
					        	form.setField("LOAN_COUNT", String.valueOf(count));
					        	form.setField("COMMITMENT_TYPE", "Mandatory");
					        }else{
					        	System.out.println("Agency Commitment Letter object is null ");
					        }
					        
					      //TODO: Rama 12/18/2012 Add Pricing Grid data to the PDF.
					       /* PricingGridDAO pricingGridDAO = new PricingGridDAO();
					        
					        //30 Year Price Grid
					        ServRateSheets[] serv30RateSheetList = pricingGridDAO.getPricingGrid(30);

					        if(serv30RateSheetList != null || serv30RateSheetList.length == 12){
					        	int i =1;
					        	for(ServRateSheets servRateSheet : serv30RateSheetList){
					        		System.out.println("\nNOTE RATE: " + String.valueOf(servRateSheet.getNoteRate()));
					        		System.out.println("\nSERVE PRICE: " + String.valueOf(servRateSheet.getServPrice()));
					        		form.setField("30_NR_" + i, String.valueOf(servRateSheet.getNoteRate()));
					        		form.setField("30_SRP_" + i, String.valueOf(servRateSheet.getServPrice()));
					        		i++;
					        	}
//					        	for(ServRateSheets servRateSheet : serv30RateSheetList){
//					        		System.out.println("\nNOTE RATE: " +servRateSheet.getNoteRate());
//					        		System.out.println("\nSERVE PRICE: " +servRateSheet.getServPrice());
//					        		form.setField("30_NR_" + i, String.valueOf(servRateSheet.getNoteRate()));
//					        		form.setField("30_SRP_" + i, String.valueOf(servRateSheet.getServPrice()));
//					        		i++;
//					        	}
					        	
					        }else {
					        	System.out.println("Error while reading serv30RateSheetList: List is either null or have <12 values ");
					        }
					        
					        //20 year Price Grid
					        ServRateSheets[] serv20RateSheetList = pricingGridDAO.getPricingGrid(20);
					        if(serv20RateSheetList != null || serv20RateSheetList.length == 12){
					        	int i =1;
					        	
					        	for(ServRateSheets servRateSheet : serv20RateSheetList){
					        		form.setField("20_NR_" + i, String.valueOf(servRateSheet.getNoteRate()));
					        		form.setField("20_SRP_" + i, String.valueOf(servRateSheet.getServPrice()));
					        		i++;
					        	}
					        	
					        }else {
					        	System.out.println("Error while reading serv20RateSheetList: List is either null or have <12 values ");
					        }
					        
					        //15 Year Price Grid
					        ServRateSheets[] serv15RateSheetList = pricingGridDAO.getPricingGrid(15);
					        if(serv15RateSheetList != null || serv15RateSheetList.length == 12){
					        	int i =1;
					        	
					        	for(ServRateSheets servRateSheet : serv15RateSheetList){
					        		form.setField("15_NR_" + i, String.valueOf(servRateSheet.getNoteRate()));
					        		form.setField("15_SRP_" + i, String.valueOf(servRateSheet.getServPrice()));
					        		i++;
					        	}
					        	
					        }else {
					        	System.out.println("Error while reading serv15RateSheetList: List is either null or have <12 values ");
					        }*/
					        
				        }else{
				        	System.out.println("Error while reading Source PDF Templace file: Form is null ");
				        }	  
			        }else{
			        	System.out.println("Error while reading Source PDF Templace file: Stamper is null ");
			        }			        
		        }
		        		        
		        stamper.close();
		        
		        System.out.println(" Output PDF File Size is: " + baos.toByteArray().length);
		        
		       
		        
		        //TODO: Remove these 2 lines after testing!
		        String fileName = "CMCCommitmentLetter_" + cmcMsrCommitmentLetter.getCommitmentNumber()+".pdf";
		        File file = new File(MsrConstants.CMC_COMMIT_LETTERS+fileName);
		        OutputStream outputStream = new FileOutputStream (file); 
		        baos.writeTo(outputStream);
		        
		        
		        //Insert PDF Byte Array into CMCFile table.
		        CMCFileDAO cmcFileDAO = new CMCFileDAO();
				cmcFileDAO.saveFile(baos.toByteArray(),fileName,MsrConstants.COMMITMENT_REQUEST,true,
											"",cmcMsrCommitmentLetter.getCommitmentNumber(),agencyCommitmentNumber);
		        //Send email
		        EmailUtil emailUtil = new EmailUtil();
//		        emailUtil.sendEmail(baos.toByteArray(), fileName);
		        emailUtil.sendEWSEmail(baos.toByteArray(), fileName);

		        outputStream.close();
				baos.close();
				
				//Clean bin folder by removing residual PDF file
				File dir = new File(MsrConstants.LOCALBINFOLDER);
				List<String> fileNames = new ArrayList<String>();

				File[] files = dir.listFiles();
				for (File fileToDelete : files) {
					if (!fileToDelete.isDirectory()) {
						if (fileToDelete.getPath().contains("CMCCommitmentLetter_")) {
							fileToDelete.delete();
						}
					}
				}

				
		        //EMail PDF
		        /*boolean isEmailSend = EmailUtil.sendEmail(baos.toByteArray());
		        
		        if(isEmailSend){
		        	System.out.println(" Email is delivered Successfully! " );
		        }else{
		        	System.out.println(" Email is failed! " );
		        }*/
		        
		       /* // setting some response headers
	            response.setHeader("Expires", "0");
	            response.setHeader("Cache-Control",
	                "must-revalidate, post-check=0, pre-check=0");
	            response.setHeader("Pragma", "public");
	            // setting the content type
	            response.setContentType("application/pdf");
	            // the contentlength
	            response.setContentLength(baos.size());
	            // write ByteArrayOutputStream to the ServletOutputStream
	            OutputStream os = response.getOutputStream();
	            baos.writeTo(os);
	            os.flush();
	            os.close();*/
		//		reader.close();
		//		baos.close();
		        
		        
			}else{
	        	System.out.println("CMCMsrCommitmentLetter object is null ");
	        }
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			if(reader != null){
				reader.close();
			}
			
			try {
				if(baos != null){
					baos.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(stamper != null){
				stamper.setFormFlattening(true);
		        try {
					stamper.close();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
