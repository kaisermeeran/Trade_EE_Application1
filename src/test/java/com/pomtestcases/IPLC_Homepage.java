package com.pomtestcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IPLC_Homepage {
	
	WebDriver driver;
	public IPLC_Homepage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[normalize-space(text())='Import Letter of Credit']") WebElement clickImportLC;
	@FindBy(xpath = "//span[normalize-space(text())='IPLC Issuance']") WebElement clickIPLCIssuance;
	@FindBy(xpath = "//div[@id='F05030702010']/div[1]/span[1]") WebElement clickNewIPLC;
	

	// Action methods to interact with the page elements
	public void clickImportLC() 
	{
		clickImportLC.click();
	}
	public void clickIPLCIssuance() 
	{
		clickIPLCIssuance.click();
	}
	public void clickNewIPLC() 
	{
		clickNewIPLC.click();
	}
}
