package cars.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cars.pages.GooglePage;
import wrappers.GenericWrappers;

public class Google extends GenericWrappers{
	
	@BeforeClass
	public void startTestCase(){
		browserName 	= "Safari";
		dataSheetName 	= "no data sheet";
		testCaseName 	= "Search a text in Google";
		testDescription = "<font face=\"Arial\" color=\"blue\">To search a text in google</font>";
	}
	
	@Test(description = "Google search")
	public void searchInGoogle(){
		new GooglePage().doSearch("http://www.consumerreports.org/cro/index.htm");
	}
	
}
