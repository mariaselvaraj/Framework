package cars.pages;



import wrappers.GenericWrappers;

public class HomePage extends GenericWrappers {

	public HomePage() {
		super();		
	}
	
	private HomePage invokeCarApps(String url){
		this.invokeApps(url);
		return this;
	}
	
	private HomePage verifyAboveTheFoldImgSource(String model){
		this.verifyFilePath(this.getAttributeByXpath("//*[@id='main']//*[@id='hero-shot']/div/img", "src"), "2100", 
				             this.getGreenHtmlTextToPrint( model  + " : Path Correct [TS-verifyAboveTheFoldImgSource]"), 
				             this.getRedHtmlTextToPrint(model + " : Wrong Path [TS-verifyAboveTheFoldImgSource]"));
		return this;
	}
	
	private HomePage verifyAboveTheFoldImage(String model){
		this.isDisplayedByXpath("//*[@id='main']//*[@id='hero-shot']/div/img", 
								this.getGreenHtmlTextToPrint(model  + " : Image Present [TS-verifyAboveTheFoldImage]"),
								this.getRedHtmlTextToPrint(model  + " : Image Not Present [TS-verifyAboveTheFoldImage]"));
		return this;
	}
	
	public HomePage verifyAboveTheFold(String burl, String model){
		String mdl = model.replace('/', ' ').replace(".htm", " ");
		
		this.invokeCarApps(burl + model)
		.verifyAboveTheFoldImage(mdl)
		.verifyAboveTheFoldImgSource(mdl);
		return this;
	}

	
}
