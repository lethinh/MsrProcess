package com.capmkts.msrprocess.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
/**
 * Using Apache POI API read Microsoft Excel (.xls) file and convert into CSV file with Java API.
 * Using Java API read Comma Separated Values(.csv) file and convert into XLS file with Apache POI API.    
 * <p>
 * Microsoft Excel file is converted to CSV for all type of columns data.
 * </p>
 * <pre>
 * DataConvertionUtil.csvToEXCEL(csvFileName,excelFileName);
 * DataConvertionUtil.excelToCSV(excelFileName,csvFileName);
 * </pre>
 * @version 1.0, 13/July/2012
 * @author Stephen Babu.P
 * 
 */
public class DataConversionUtil
{
    /***
     * Date format used to convert excel cell date value
     */
    private static final String OUTPUT_DATE_FORMAT= "yyyy-MM-dd";
    /**
     * Comma separated characters
     */
    private static final String CVS_SEPERATOR_CHAR=",";
    /**
     * New line character for CSV file
     */
    private static final String NEW_LINE_CHARACTER="\r\n";
   
    /**
     * Convert CSV file to Excel file
     * @param csvFileName
     * @param excelFileName
     * @throws Exception
     */
    public static void csvToEXCEL(String csvFileName,String excelFileName) throws Exception{
        checkValidFile(csvFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFileName)));
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        FileOutputStream writer = new FileOutputStream(new File(excelFileName) );
        HSSFSheet mySheet = myWorkBook.createSheet();
        String line= "";
        int rowNo=0;
        while ( (line=reader.readLine()) != null ){
            String[] columns = line.split(CVS_SEPERATOR_CHAR);
             HSSFRow myRow =mySheet.createRow(rowNo);
            for (int i=0;i<columns.length;i++){
                HSSFCell myCell = myRow.createCell(i);
                myCell.setCellValue(columns[i]);
            }
             rowNo++;
        }
        myWorkBook.write(writer);
        writer.close();
    }
    /**
     * Convert the Excel file data into CSV file
     * @param excelFileName
     * @param csvFileName
     * @throws Exception
     */
    public static void excelToCSV(String excelFileName,String csvFileName) throws Exception{
//            checkValidFile(csvFileName);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(excelFileName)));
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator  rowIter =  mySheet.rowIterator();
            String csvData="";
            while (rowIter.hasNext()) {
                    HSSFRow myRow = (HSSFRow) rowIter.next();
                    for ( int i=0;i<myRow.getLastCellNum();i++){
                          csvData += getCellData(myRow.getCell(i));
                    }
                    csvData+=NEW_LINE_CHARACTER;
            }
            writeCSV(csvFileName, csvData);
    }
    
    public static String getExcelToCSVString(File file) throws Exception{
//      checkValidFile(csvFileName);
      HSSFWorkbook myWorkBook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(file)));
      HSSFSheet mySheet = myWorkBook.getSheetAt(0);
      Iterator  rowIter =  mySheet.rowIterator();
      String csvData="";
      while (rowIter.hasNext()) {
              HSSFRow myRow = (HSSFRow) rowIter.next();
              for ( int i=0;i<myRow.getLastCellNum();i++){
                    csvData += getCellData(myRow.getCell(i));
              }
              csvData+=NEW_LINE_CHARACTER;
      }
      return csvData;
}
    
    /**
     * Write the string into a text file
     * @param csvFileName
     * @param csvData
     * @throws Exception
     */
    private static void writeCSV(String csvFileName,String csvData) throws Exception{
    	File file = new File(csvFileName);
    	// if file doesnt exists, then create it
    		if (!file.exists()) {
    			file.createNewFile();
    		}
        FileOutputStream writer = new FileOutputStream(file);
        writer.write(csvData.getBytes());
        writer.close();
    }
    /**
     * Get cell value based on the excel column data type
     * @param myCell
     * @return
     */
    private static String getCellData( HSSFCell myCell) throws Exception{
        String cellData="";
         if ( myCell== null){
             cellData += CVS_SEPERATOR_CHAR;;
         }else{
             switch(myCell.getCellType() ){
                 case  HSSFCell.CELL_TYPE_STRING  :
                 case  HSSFCell.CELL_TYPE_BOOLEAN  :
                          cellData +=  myCell.getRichStringCellValue ()+CVS_SEPERATOR_CHAR;
                          break;
                 case  HSSFCell.CELL_TYPE_NUMERIC :
                	 	 DecimalFormat df = new DecimalFormat("0.00#");
                	 	 String temp = getNumericValue(myCell);
                	 	 if (temp.length() > 20){
                	 		temp = df.format(Double.parseDouble(temp.replaceAll("\\,", ""))) + ",";
                	 	 }
                	 	 
                         cellData += temp;
                         break;
                 case  HSSFCell.CELL_TYPE_FORMULA :
                         cellData +=  getFormulaValue(myCell);
             default:
                 cellData += CVS_SEPERATOR_CHAR;;
             }
         }
         return cellData;
    }
    /**
     * Get the formula value from a cell
     * @param myCell
     * @return
     * @throws Exception
     */
    private static String getFormulaValue(HSSFCell myCell) throws Exception{
        String cellData="";
         if ( myCell.getCachedFormulaResultType() == HSSFCell.CELL_TYPE_STRING  || myCell.getCellType () ==HSSFCell.CELL_TYPE_BOOLEAN) {
             cellData +=  myCell.getRichStringCellValue ()+CVS_SEPERATOR_CHAR;
         }else  if ( myCell.getCachedFormulaResultType() == HSSFCell.CELL_TYPE_NUMERIC ) {
             cellData += getNumericValue(myCell)+CVS_SEPERATOR_CHAR;
         }
         return cellData;
    }
    /**
     * Get the date or number value from a cell
     * @param myCell
     * @return
     * @throws Exception
     */
    private static String getNumericValue(HSSFCell myCell) throws Exception {
        String cellData="";
         if ( HSSFDateUtil.isCellDateFormatted(myCell) ){
               cellData += new SimpleDateFormat(OUTPUT_DATE_FORMAT).format(myCell.getDateCellValue()) +CVS_SEPERATOR_CHAR;
           }else{
               cellData += new BigDecimal(myCell.getNumericCellValue()).toString()+CVS_SEPERATOR_CHAR ;
           }
        return cellData;
    }
    private static void checkValidFile(String fileName){
        boolean valid=true;
        try{
            File f = new File(fileName);
            if ( !f.exists() || f.isDirectory() ){
                valid=false;
            }
        }catch(Exception e){
            valid=false;
        }
        if ( !valid){
            System.out.println("File doesn't exist: " + fileName);
            System.exit(0);
        }
    }
    public static void main(String[] args) throws Exception
    {
        String excelfileName1="C:\\test\\FNMA377367.xls";
        String csvFileName1="C:\\test\\FNMA377367.csv";
        File file = new File("C:\\test\\FNMA377367.xls");
        String test = getExcelToCSVString(file);
        System.out.println(test);
//        String excelfileName2="D:\\stephen\\files\\excel-file2.xls";
//        String csvFileName2="D:\\stephen\\files\\csv-file2.csv";
//        excelToCSV(excelfileName1,csvFileName1);
//        csvToEXCEL(csvFileName2,excelfileName2);
    }

}