package com.lms.utilities;

import java.io.IOException;
import java.util.ArrayList;



public class BaseClass {

	public static String eXCEL = "src/test/resources/TestData/LMS_data.xlsx";	
//	public LoginPageObject loginpage;
//	public DashboardPageObject dashboard;
	
		// Extract the data from Excel and return the value as ArrayList
		public static ArrayList<String> excelValue(String sheetName, String testCase) throws IOException {
			//LoggerLoad.info("ArrayList excelValuemethod");
			Excelreader ed = new Excelreader();
			ArrayList<String> data = ed.dataDriven(sheetName, testCase);
			System.out.println("PrintvaluesfromExcel"+data);
			return data;
		}
	
		
}
