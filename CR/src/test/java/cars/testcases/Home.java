package cars.testcases;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cars.pages.HomePage;
import utils.ExcelTransaction;
import utils.PropertiesTransaction;
import wrappers.GenericWrappers;

public class Home extends GenericWrappers{
	
	@BeforeClass
	public void startTestCase(){
		browserName 	= "Safari";
		dataSheetName 	= "CARS_Redesign";
		testCaseName 	= "Above the Fold Test";
		testDescription = "<font face=\"Arial\" color=\"blue\">To verify image of the car model</font>";
	}
	
	@Test(description = "Above the fold Image present test")
	@Parameters("runForCars")
	public void aboveTheFold(String range){
		List<String> lstCarsUrl = null;
	
		try {
			lstCarsUrl = ExcelTransaction.readXlsFile(range, "AboveTheFold", "../CR/resources/cars/CarsRedesign_DATASHEET.xls");
			Properties prop = PropertiesTransaction.readPropertyFile("../CR/objects/cars/common.properties");
			HomePage hpage = new HomePage();
			
			for(String carsUrl : lstCarsUrl){
				hpage.verifyAboveTheFold(prop.getProperty("BASE_URL") , carsUrl);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
