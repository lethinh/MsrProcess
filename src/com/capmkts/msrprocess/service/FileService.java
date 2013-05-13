package com.capmkts.msrprocess.service;

import java.io.File;

import com.capmkts.msrprocess.validator.DataValidator;

public interface FileService {
	
	public abstract void process(File file,DataValidator dataValidator)throws Exception; 

}
