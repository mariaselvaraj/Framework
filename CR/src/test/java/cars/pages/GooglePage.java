package cars.pages;

import wrappers.GenericWrappers;

public class GooglePage extends GenericWrappers {
	
	public GooglePage() {
		super();		
	}
	
	private GooglePage invokeGoogleApps(String url){
		this.invokeApps(url);
		return this;
	}
	
	private GooglePage enterSearchText(){
		System.out.println("Entering text in google serach box..");
		this.enterById("lst-ib", "cognizant");
		return this;
	}
	
	public void doSearch(String url){
		this.invokeGoogleApps(url); //.enterSearchText();
	}

}
