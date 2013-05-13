package com.capmkts.msrprocess.generator;

import java.awt.Color;
import java.io.FileOutputStream;

import javax.swing.text.TabExpander;

import com.capmkts.msrprocess.data.Buyer;
import com.capmkts.msrprocess.data.CommitmentDetails;
import com.capmkts.msrprocess.data.CommitmentLetter;
import com.capmkts.msrprocess.data.Patron;
import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.collection.PdfTargetDictionary;

public class CommitmentLetterGenerator {
	
	public void generate(CommitmentLetter commitmentLetter){
		
		try{
		
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\ITextTest.pdf"));
			document.open();
			
			// SELLER Information
				PdfPTable sellerTable = prepareSellerTable(document, commitmentLetter.getPatron());
				document.add(sellerTable);
				
			// COMMITMENT Details
				PdfPTable commitmentDetailsTable = prepareCommitmentDetailsTable(document, commitmentLetter.getCommitmentDetails());
				document.add(commitmentDetailsTable);
					
			// BUYER Information
				PdfPTable buyerTable = prepareBuyerTable(document, commitmentLetter.getBuyer());
				document.add(buyerTable);

            document.close();
	
			// code to store the document in the Database

		}catch(Exception ex){
			
		}
		
	}
	
	
	private PdfPTable prepareSellerTable(Document document, Patron patron){

		PdfPTable sellerTable = new PdfPTable(4); 

		Paragraph sellerHeader = new Paragraph("Seller Information",FontFactory.getFont("Verdana", 8, Font.BOLD,BaseColor.BLUE));

        PdfPCell sellerCell = new PdfPCell(sellerHeader);
        sellerCell.setColspan(4);
        sellerCell.setBorder(0);   // removes border
        sellerCell.setBackgroundColor(BaseColor.WHITE);   //sets BG color to yellow.

        sellerTable.addCell(sellerCell);
        sellerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        
        return sellerTable;
		
	}
	
	private PdfPTable prepareCommitmentDetailsTable(Document document, CommitmentDetails commitmentDetails){

		PdfPTable commitmentTable = new PdfPTable(4); 

		Paragraph commitmentHeader = new Paragraph("Commitment Details",FontFactory.getFont("Verdana", 8, Font.BOLD,BaseColor.BLUE));

        PdfPCell commitmentCell = new PdfPCell(commitmentHeader);
        commitmentCell.setColspan(4);
        commitmentCell.setBorder(0);   // removes border
        commitmentCell.setBackgroundColor(BaseColor.WHITE);   //sets BG color to yellow.

        commitmentTable.addCell(commitmentCell);
        commitmentTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        
        return commitmentTable;

	}
	
	private PdfPTable prepareBuyerTable(Document document, Buyer buyer){
		
		PdfPTable buyerTable = new PdfPTable(4); 

		Paragraph buyerHeader = new Paragraph("Buyer Information",FontFactory.getFont("Verdana", 8, Font.BOLD,BaseColor.BLUE));

        PdfPCell buyerCell = new PdfPCell(buyerHeader);
        buyerCell.setColspan(4);
        buyerCell.setBorder(0);   // removes border
        buyerCell.setBackgroundColor(BaseColor.WHITE);   //sets BG color to yellow.

        buyerTable.addCell(buyerCell);
        buyerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        
        return buyerTable;
		
	}
	
	public static void main(String[] args) {
		
		CommitmentLetterGenerator commitmentLetterGenerator = new CommitmentLetterGenerator();
		commitmentLetterGenerator.generate(new CommitmentLetter());
	}
		
}
