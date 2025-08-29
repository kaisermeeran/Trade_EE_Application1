package com.pomtestcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterLC
{

	WebDriver driver;
	public RegisterLC(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "FORM_OF_LC") WebElement formOfLC;
	@FindBy(id = "APLB_RULE") WebElement aplbRules;
	@FindBy(id="AVAL_BY") WebElement avalBy;
	@FindBy(id = "LC_AMT") WebElement lcAmt;
	@FindBy(id="EXPIRY_DT") WebElement expiryDate;
	@FindBy(id = "ADD_AMT_COVRD") WebElement addAmtCovered;
	@FindBy(css = "span#referenceNumber") WebElement referencenumber;
	@FindBy(id = "C") WebElement partiesTab;
	@FindBy(name="APPL_ID_BTN") WebElement applicantIdButton;
	//@FindBy(xpath = "//iframe[contains(@id,'confirmDialogFrame')]") WebElement confirmDialogFrame;
	@FindBy(id="CataListTab") WebElement table;
	@FindBy(name="BENE_ID_BTN") WebElement beneficiaryIdButton;
	@FindBy(id="AC_OFFICER_CODE")WebElement acccofficecode;
	@FindBy(id="SAME_AS_APPL_FLG") WebElement sameAsAppflag;
	@FindBy(name="FORACOF_ID_BTN") WebElement forAccofficerIdButton;
	@FindBy(name="ADV_BK_ID_BTN") WebElement advBankIdButton;
	@FindBy(id="B") WebElement risktab;
	@FindBy(name="ASSET_ACNO_BTN") WebElement banklibbtn;
	@FindBy(name="APPL_AC_MRGN_BTN") WebElement custAccNoButton;
	
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	
	public void switchToWorkFrame() 
	{
	    driver.switchTo().defaultContent();
	    new WebDriverWait(driver, Duration.ofSeconds(10))
	        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("work"));
	}
	
	public void waitmethod() throws InterruptedException 
	{
		Thread.sleep(500);
	}

	public void enterFormOfLC(String form) throws InterruptedException 
	{
		
		switchToWorkFrame();
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("work")); //
		 * Switch to the iframe named "work" if not already switched
		 * 
		 * Thread.sleep(500);
		 */
		/*
		 * driver.switchTo().frame("work"); Thread.sleep(500);
		 */
		Select formSelect = new Select(formOfLC);
		formSelect.selectByVisibleText(form);
	}
	
	public void enterAPLB_Rules(String rules) throws InterruptedException 
	 { 
		
		Select aplbSelect = new Select(aplbRules);
		aplbSelect.selectByVisibleText(rules);
	 }
	public void enterAvalBy(String aval) 
	{ 
		
		Select avalSelect = new Select(avalBy);
		avalSelect.selectByVisibleText(aval);
	} 
	public void enterLC_Amt(String amt) throws InterruptedException 
	{ 
		Thread.sleep(500);
		lcAmt.click();
		lcAmt.sendKeys(amt); 
	}
	public void enterExpiryDate(String date) throws InterruptedException 
	{ 
		Thread.sleep(500);
		expiryDate.sendKeys(date); 
		Thread.sleep(500);
	}
	public void enterAddAmtCovered(String amt) throws InterruptedException 
	{ 
		Thread.sleep(500);
		addAmtCovered.sendKeys(amt); 
		Thread.sleep(500);
	}
	
	public void switchToDefaultContent() 
	{
	    driver.switchTo().defaultContent();
	}
	
	public void switchtopathinfo() 
	{
	    driver.switchTo().defaultContent();
	    new WebDriverWait(driver, Duration.ofSeconds(10))
	        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("eeToolbar"));
	    new WebDriverWait(driver, Duration.ofSeconds(10))
	        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("pathInfo"));
	}
	
	public void copyrefnum()
	{
		switchtopathinfo();
		String refNumber = null;
        try 
        {
        	refNumber = referencenumber.getText();
        	System.out.println("Reference Number is: " + refNumber);
        } 
        catch (Exception e)
        	{
				System.err.println("❌ Could not find or read span#referenceNumber: " + e.getMessage());
        					
        	}
			switchToDefaultContent();
			switchToWorkFrame();
	}
	
	public void partiestab() throws InterruptedException
	{
		partiesTab.click();
		waitmethod();
	}
	
	public void clickApplicantIdButton() throws InterruptedException
	{
		applicantIdButton.click();
		//Thread.sleep(3000);
	}
	
	public void enterAppCustId(String cubkId) throws InterruptedException 
	{ 
		switchToDefaultContent();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframeElement = wait1.until(
        	    ExpectedConditions.presenceOfElementLocated(
        	        By.xpath("//iframe[contains(@id,'confirmDialogFrame')]")
        	    )
        	);
        //System.out.println("Switched to iframe: confirmDialogFrame"+ iframeElement);
		driver.switchTo().frame(iframeElement);
		//WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CataListTab")));
		//System.out.println("Table found."+table);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		String targetRef = cubkId;
		boolean found = false;
		
		for (WebElement row : rows) 
        {
            try {
                // ✅ Get the first <td> with the <a> link inside it
                WebElement firstCellLink = row.findElement(By.xpath("./td[1]/a"));

                String refValue = firstCellLink.getText().trim();
                System.out.println("Row REF: " + refValue);

                if (refValue.equals(targetRef)) {
                    firstCellLink.click();
                    System.out.println("✅ Clicked: " + refValue);
                    found = true;
                    break;
                }
            } catch (Exception e) {
                // Some rows might not have <td[1]/a>, safely skip
                continue;
            }
        }
        if (!found) {
            throw new RuntimeException("❌ Reference not found: " + targetRef);
        }
		
	}
	
	public void clickBeneficiaryIdButton() throws InterruptedException
	{
		switchToDefaultContent();
		switchToWorkFrame();
		beneficiaryIdButton.click();
		//Thread.sleep(3000);
	}
	
	public void enterAccOfficeCode(String acccode) throws InterruptedException 
	{ 
		switchToDefaultContent();
		switchToWorkFrame();
		acccofficecode.sendKeys(acccode);
	}
	
	public void enterSameAsApp(String sameasapp1) throws InterruptedException 
	{ 
		Select sameas = new Select(sameAsAppflag);
		if( sameasapp1.equals("Yes"))
		{
		sameas.selectByVisibleText(sameasapp1);
		}
		else if(sameasapp1.equals("No"))
		{
			sameas.selectByVisibleText(sameasapp1);
			forAccofficerIdButton.click();
			handle_alert();
			enterAppCustId("C006761"); // Example ID, replace with actual logic
		}
	}
	public void handle_alert()
	{
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert says: " + alertText);
		alert.accept(); // Accept the alert
	}
	
	public void advBankIdButton() throws InterruptedException
	{
		switchToDefaultContent();
		switchToWorkFrame();
		advBankIdButton.click();
		waitmethod();
		handle_alert();
	}
	
	public void clickRiskTab() throws InterruptedException
	{
		switchToDefaultContent();
		switchToWorkFrame();
		risktab.click();
		waitmethod();
		
	}
	
	public void clickbankliabiiltyButton() throws InterruptedException
	{
		switchToDefaultContent();
		switchToWorkFrame();
		banklibbtn.click();
		waitmethod();
		//handle_alert();
	}
	
	public void clickcustliabiiltyButton() throws InterruptedException
	{
		switchToDefaultContent();
		switchToWorkFrame();
		custAccNoButton.click();
		waitmethod();
		//handle_alert();
	}
	
	
	
	
}

