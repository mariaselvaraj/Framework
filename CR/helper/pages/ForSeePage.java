package pages;

import wrappers.GenericWrappers;

public class ForSeePage extends GenericWrappers {

	private String val = null;
	
	public ForSeePage(boolean tosuppress){
		this.val = (tosuppress) ? "0" : "1";
	}
	
	private ForSeePage doEnterTablet() {
		this.enterByName("tablet_web", this.val);
		return this;
	}

	private ForSeePage doEnterPhone() {
		this.enterByName("phone_web", this.val);
		return this;
	}

	private ForSeePage doEnterBrowse() {
		this.enterByName("browse", this.val);
		return this;
	}

	private void doClickSet() {
		this.clickByXpath("//input[@value='Set']");
	}
	
	public void doSuppressPopUp(){
		this.doEnterTablet().doEnterPhone().doEnterBrowse().doClickSet();
	}

}
