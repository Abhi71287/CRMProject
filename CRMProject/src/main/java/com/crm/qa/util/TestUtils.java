package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;



public class TestUtils extends TestBase {

	public static long PAGE_LOAD_TIME_OUT = 20;
	public static long IMPLICIT_WAIT = 10;
	public static String TEST_DATA_SHEET_PATH = "../CRMProject/src/main/java/com/crm/qa/testdata/crmData.xlsx";

	static XSSFWorkbook book;
	static Sheet sheet;

	public static void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TEST_DATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = new XSSFWorkbookFactory().create(file);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
//		System.out.println(sheet.getLastRowNum()+ "....."+sheet.getRow(0).getLastCellNum());
		
		for(int i= 0;i<sheet.getLastRowNum();i++) {
			for(int k=0 ;k<sheet.getRow(0).getLastCellNum();k++) {
				data [i][k] = sheet.getRow(i+1).getCell(k).toString();
			}
		}
       return data;
	}

	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
}
