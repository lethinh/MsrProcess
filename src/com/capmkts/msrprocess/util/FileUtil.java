package com.capmkts.msrprocess.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	
	public static byte[] getBytes(File file) {

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

	public boolean writeCSV(String fileName, String content, boolean append){
		boolean writeSuccessful = false;
		try{
			FileUtils.writeStringToFile(new File(fileName), content, append);
		}catch(Exception e){
			e.printStackTrace();
		}
		return writeSuccessful;
	}
	
	public String[] getEmailList(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        
        //TO DO: test this code
//        lines.removeAll(Arrays.asList("", null));
        
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }

}
