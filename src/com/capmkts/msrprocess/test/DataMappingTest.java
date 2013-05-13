package com.capmkts.msrprocess.test;

import java.io.File;
import java.util.*;

import com.capmkts.msrprocess.datamapping.DataMapper;

public class DataMappingTest {
	public static void main(String[] args) throws Exception{

		String path = ("C:\\ServicingTest.csv");
		DataMapper dataMapper = new DataMapper();
		System.out.println(dataMapper.cmcDataMapper(path, "BOI", "COISSUE"));

	}
}
