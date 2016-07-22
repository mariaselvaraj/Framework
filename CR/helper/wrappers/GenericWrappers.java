package wrappers;

import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import modal.Environment;
import utils.PropertiesTransaction;
import utils.Reporter;
import utils.supporter;

public class GenericWrappers implements IGenericWrapper {

	protected static RemoteWebDriver driver = null;

	protected String browserName;
	protected String dataSheetName;
	protected static String testCaseName;
	protected static String testDescription;

	private final String cmnConfigPath = "../CR/objects/common/config.properties";

	protected String fontRedSt = "<font face=\"courier new\" color=\"red\">";
	protected String fontGreenSt = "<font face=\"courier new\" color=\"green\">";
	protected String fontEnd = "</font>";
	protected String strongSt = "<strong>";
	protected String strongEnd = "</strong>";

	protected String hubIP = "192.168.110.40", hubPort = "4444";

	@BeforeSuite
	public void beforeSuite() {
		Reporter.startResult();
	}

	@AfterSuite
	public void afterSuite() {
		Reporter.endResult();
	}

	@BeforeMethod
	public void beforeMethod() {
		Reporter.startTestCase();
		// invokeAppInLocal(browserName);
	}

	@AfterMethod
	public void afterMethod() {
		quitBrowser();
	}

	public String getRedHtmlTextToPrint(String text) {
		return this.fontRedSt + text + this.fontEnd;
	}

	public String getGreenHtmlTextToPrint(String text) {
		return this.fontGreenSt + text + this.fontEnd;
	}

	public GenericWrappers() {
		// set only for the first time
		Properties prop = PropertiesTransaction.readPropertyFile(cmnConfigPath);
		Environment.setBrowser(prop.getProperty("BROWSER"));
		Environment.setMachine(prop.getProperty("MACHINE"));
		Environment.setQaEnv(prop.getProperty("QAENV"));
		Environment.setHub(prop.getProperty("HUB"));
		Environment.setOs(prop.getProperty("OS"));

		Environment
				.setAddImgPass((prop.getProperty("ADDSS_PASS").toUpperCase().equalsIgnoreCase("YES")) ? true : false);
		Environment
				.setAddImgFail((prop.getProperty("ADDSS_FAIL").toUpperCase().equalsIgnoreCase("YES")) ? true : false);
	}

	public void invokeApps(String url) {
		if (driver == null) {
			if (this.setDriver()) {
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			} else {
				// driver not set
			}
		}
		driver.get(url);
	}

	public boolean verifyText(String exptext) {
		boolean bReturn = false;
		try {
			if (driver.getTitle().equalsIgnoreCase(exptext)) {
				Reporter.reportStep("The text expected matches with the value :" + exptext, "PASS",
						Environment.getAddImgPass());
				bReturn = true;
			} else
				Reporter.reportStep(
						"The text expected:" + driver.getTitle() + " did not match with the value :" + exptext, "FAIL",
						Environment.getAddImgFail());

		} catch (Exception e) {
			Reporter.reportStep("The text expected did not match", "ERROR", Environment.getAddImgError());
		}

		return bReturn;
	}

	public boolean verifyIsDisplayedByXpath(String exptext) {
		boolean bReturn = false;
		try {
			if (driver.getTitle().equalsIgnoreCase(exptext)) {
				Reporter.reportStep("The text expected matches with the value :" + exptext, "PASS",
						Environment.getAddImgPass());
				bReturn = true;
			} else
				Reporter.reportStep(
						"The text expected:" + driver.getTitle() + " did not match with the value :" + exptext, "FAIL",
						Environment.getAddImgFail());

		} catch (Exception e) {
			Reporter.reportStep("The text expected did not match", "ERROR", Environment.getAddImgError());
		}

		return bReturn;
	}

	public void quitBrowser() {
		/*
		 * try { driver.quit(); } catch (Exception e) { Reporter.reportStep(
		 * "The browser:" + driver.getCapabilities().getBrowserName() +
		 * " could not be closed.", "ERROR", Environment.getAddImgFail()); }
		 */

	}

	private boolean setDriver() {
		boolean isDriverSet = false;
		try {
			DesiredCapabilities capabilities;
			URL url = new URL(Environment.getHub());
			String browser = Environment.getBrowser();
			String machine = Environment.getMachine();
			String os = Environment.getOs();

			System.out.println("Hub Url : " + url.toString());
			System.out.println("Browser : " + browser);
			System.out.println("Os : " + os);

			if (browser.equalsIgnoreCase("CHROME")) {
				System.setProperty("webdriver.chrome.driver", "../CR/drivers/chromedriver.exe");
				capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = (machine.equalsIgnoreCase("LOCAL")) ? new ChromeDriver(capabilities)
						: new RemoteWebDriver(url, capabilities);
				isDriverSet = true;
			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", "../CR/drivers/IEDriverServer.exe");
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				driver = (machine.equalsIgnoreCase("LOCAL")) ? new InternetExplorerDriver(capabilities)
						: new RemoteWebDriver(url, capabilities);
				isDriverSet = true;
			} else if (browser.equalsIgnoreCase("FF")) {
				FirefoxProfile fp = new FirefoxProfile();
				fp.setPreference("browser.download.useDownloadDir", false);
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability(FirefoxDriver.PROFILE, fp);
				driver = (machine.equalsIgnoreCase("LOCAL")) ? new FirefoxDriver(capabilities)
						: new RemoteWebDriver(url, capabilities);
				isDriverSet = true;
			} else if (browser.equalsIgnoreCase("SAFARI") && os.equalsIgnoreCase("MAC")) {
				capabilities = new DesiredCapabilities();
				capabilities.setBrowserName(DesiredCapabilities.safari().getBrowserName());
				capabilities.setPlatform(Platform.MAC);
				try {
					System.out.println("Creating safari instance ....");
					driver = (machine.equalsIgnoreCase("LOCAL")) ? new SafariDriver(capabilities)
							: new RemoteWebDriver(url, capabilities);
					;
					System.out.println("Safari browser instance created");
				} catch (Exception e) {

				}

				isDriverSet = true;
			}

		} catch (Exception e) {

		}

		return isDriverSet;
	}

	public void enterById(String id, String txt) {
		// TODO Auto-generated method stub

	}

	public void enterByName(String name, String txt) {
		// TODO Auto-generated method stub

	}

	public void enterByLinkText(String lnktxt, String txt) {
		// TODO Auto-generated method stub

	}

	public void enterByParLinkText(String parlnktxt, String txt) {
		// TODO Auto-generated method stub

	}

	public void enterByCssSelector(String css, String txt) {
		// TODO Auto-generated method stub

	}

	public void enterByXpath(String xpath, String txt) {
		// TODO Auto-generated method stub

	}

	public void clickById(String id) {
		// TODO Auto-generated method stub

	}

	public void clickByName(String name) {
		// TODO Auto-generated method stub

	}

	public void clickByLinkText(String lnktxt) {
		// TODO Auto-generated method stub

	}

	public void clickByParLinkText(String parlnktxt) {
		// TODO Auto-generated method stub

	}

	public void clickByCssSelector(String css) {
		// TODO Auto-generated method stub

	}

	public void clickByXpath(String xpath) {
		// TODO Auto-generated method stub

	}

	public String getAttributeByXpath(String xpath, String attrname) {
		return driver.findElement(By.xpath(xpath)).getAttribute(attrname);
	}

	public boolean verifyFilePath(String path, String exp, String pmsg, String fmsg) {
		boolean bReturn = false;
		try {
			if ((supporter.getHTTPResponseCode(path)).equalsIgnoreCase(exp)) {
				Reporter.reportStep(pmsg, "PASS", Environment.getAddImgPass());
				bReturn = true;
			} else
				Reporter.reportStep(fmsg, "FAIL", Environment.getAddImgFail());

		} catch (Exception e) {
			Reporter.reportStep(fmsg, "ERROR", Environment.getAddImgError());
		}

		return bReturn;
	}

	public boolean isDisplayedByXpath(String xpath, String pmsg, String fmsg) {
		boolean bReturn = false;
		try {
			if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
				Reporter.reportStep(pmsg, "PASS", Environment.getAddImgPass());
				bReturn = true;
			} else
				Reporter.reportStep(fmsg, "FAIL", Environment.getAddImgFail());

		} catch (Exception e) {
			Reporter.reportStep(fmsg, "ERROR", Environment.getAddImgError());
		}

		return bReturn;

	}

}
