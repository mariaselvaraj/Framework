package wrappers;

public interface IGenericWrapper {
	
	public void invokeApps(String url);
	
	public void enterById(String id, String txt);
	
	public void enterByName(String name, String txt);
	
	public void enterByLinkText(String lnktxt, String txt);
	
	public void enterByParLinkText(String parlnktxt, String txt);
	
	public void enterByCssSelector(String css, String txt);
	
	public void enterByXpath(String xpath, String txt);
	
		
	public void clickById(String id);
	
	public void clickByName(String name);
	
	public void clickByLinkText(String lnktxt);
	
	public void clickByParLinkText(String parlnktxt);
	
	public void clickByCssSelector(String css);
	
	public void clickByXpath(String xpath);
	
	
	
	public String getAttributeByXpath(String xpath, String attrname);
	
	public boolean isDisplayedByXpath(String xpath, String pmsg, String fmsg);
	
	public boolean verifyText(String exptext);
	
	public void quitBrowser();
	
	public boolean verifyFilePath(String path, String exp, String pmsg, String fmsg);
	
	

}
