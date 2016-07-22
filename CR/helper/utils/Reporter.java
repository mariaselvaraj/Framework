package utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import wrappers.GenericWrappers;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Reporter extends GenericWrappers{
	
	private static ExtentTest test;
	private static ExtentReports extent;

	public static void reportStep(String desc, String status, boolean addimg) {

		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
        try {
        	if(addimg)
        		FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
        } catch (WebDriverException | IOException e) {
			e.printStackTrace();
		}
		
		// Write if it is successful or failure or information
		if(status.toUpperCase().equals("PASS")){
			test.log(LogStatus.PASS, (addimg) ? desc + test.addScreenCapture("./../reports/images/"+number+".jpg") : desc);
		}else if(status.toUpperCase().equals("FAIL")){
			test.log(LogStatus.FAIL, (addimg) ? desc + test.addScreenCapture("./../reports/images/"+number+".jpg") : desc);
			throw new RuntimeException("FAILED");
		}else if(status.toUpperCase().equals("INFO")){
			test.log(LogStatus.ERROR, desc);
		}
	}

	
	public static void startResult(){
		extent = new ExtentReports("./reports/result.html", false);
		extent.loadConfig(new File("../CR/resources/common/extent-config.xml"));
	}
	
	public static void startTestCase(){
		test = extent.startTest(testCaseName, testDescription);
	}

	public static void endResult(){
		extent.endTest(test);
		extent.flush();
	}


}
