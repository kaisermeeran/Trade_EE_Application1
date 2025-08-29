package com.pomfiletest;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pomtestcases.EE_Login;
import com.pomtestcases.IPLC_Homepage;
import com.pomtestcases.RegisterLC;
import com.utilitypackage.*;
public class Test_Login {
	
	WebDriver driver;
	EE_Login login;
	IPLC_Homepage homepage;
	
	@BeforeClass
	void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("safebrowsing.enabled", false);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--incognito");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-notifications");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("http://106.51.3.36:9080/EximBillWeb/SYS_index.htm");
		login = new EE_Login(driver);
		homepage = new IPLC_Homepage(driver);
	}
	
	@AfterClass
	public void teardown() {
		//if (driver != null) 
			//driver.quit();
	}
	
	@DataProvider(name = "loginData")
	public Object[][] loginData() throws Exception {
		String filepath = System.getProperty("user.dir") + "\\Data\\Testdata.xlsx";
		int rows = excelutils.getRowCount(filepath, "Login");

		Object[][] data = new Object[rows][3];
		for (int i = 1; i <= rows; i++) {
			data[i - 1][0] = excelutils.getCellData(filepath, "Login", i, 0);
			data[i - 1][1] = excelutils.getCellData(filepath, "Login", i, 1);
			data[i - 1][2] = excelutils.getCellData(filepath, "Login", i, 2);
		}
		return data;
	}

	@Test(priority = 1, dataProvider = "loginData")
	void EE_login(String businessUnit, String userId, String password) throws Exception {
		login.clickProceedButton();
		Thread.sleep(2000);
		login.enterUsername(businessUnit);
		login.enteruserid(userId);
		login.enterPassword(password);
		Thread.sleep(2000);
		login.clickLoginButton();
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	void Homepage() {
		homepage.clickImportLC();
		homepage.clickIPLCIssuance();
		homepage.clickNewIPLC();
		//System.out.println("Homepage verification logic goes here.");
	}
	
	@Test(priority = 3)
	void RegisterLC() throws Exception {
		String filepath = System.getProperty("user.dir") + "\\Data\\Testdata.xlsx";
		int rows = excelutils.getRowCount(filepath, "RegisterLC");

		for (int i = 1; i <= rows; i++) 
		{
			  int colIndex = excelutils.getColumnIndexByName(filepath, "RegisterLC","FORM_OF_LC"); 
			  String formOfLC = excelutils.getCellData(filepath,"RegisterLC", i, colIndex);
			  int colIndex1 = excelutils.getColumnIndexByName(filepath, "RegisterLC", "APLB_RULE"); 
			  String aplbRules = excelutils.getCellData(filepath, "RegisterLC", i, colIndex1);                 
			  int colIndex2 = excelutils.getColumnIndexByName(filepath, "RegisterLC","AVAL_BY"); 
			  String avalBy = excelutils.getCellData(filepath, "RegisterLC", i, colIndex2); 
			  int colIndex3 = excelutils.getColumnIndexByName(filepath, "RegisterLC", "LC_AMT"); 
			  String lcAmt = excelutils.getCellData(filepath,"RegisterLC", i, colIndex3); 
			  int colIndex4 = excelutils.getColumnIndexByName(filepath, "RegisterLC", "EXPIRY_DT"); 
			  String expiryDate = excelutils.getCellData(filepath, "RegisterLC", i, colIndex4);
			  int colIndex5 = excelutils.getColumnIndexByName(filepath, "RegisterLC","ADD_AMT_COVRD"); 
			  String addAmtCovered = excelutils.getCellData(filepath, "RegisterLC", i, colIndex5);
			  int colIndex6 = excelutils.getColumnIndexByName(filepath, "RegisterLC","APP_CUST_ID");
			  String appCustId = excelutils.getCellData(filepath, "RegisterLC", i, colIndex6);
			  int colIndex7 = excelutils.getColumnIndexByName(filepath, "RegisterLC","BEN_CUST_ID");
			  String benCustId = excelutils.getCellData(filepath, "RegisterLC", i, colIndex7);
			  int colIndex8 = excelutils.getColumnIndexByName(filepath, "RegisterLC","ACC_OFFICE_CODE");
			  String acccofficecode = excelutils.getCellData(filepath, "RegisterLC", i, colIndex8);
			  int colIndex9 = excelutils.getColumnIndexByName(filepath, "RegisterLC","SAME_AS_APP");
			  String sameasapp = excelutils.getCellData(filepath, "RegisterLC", i, colIndex9);
			  int colIndex10 = excelutils.getColumnIndexByName(filepath, "RegisterLC","ADV_BANK_ID");
			  String advBankId = excelutils.getCellData(filepath, "RegisterLC", i, colIndex10);
			  int colIndex11 = excelutils.getColumnIndexByName(filepath, "RegisterLC","CUST_ACC_NO");
			  String custAccNo = excelutils.getCellData(filepath, "RegisterLC", i, colIndex11);
			  int colIndex12 = excelutils.getColumnIndexByName(filepath, "RegisterLC","BANK_ACC_NO");
			  String bankAccNo = excelutils.getCellData(filepath, "RegisterLC", i, colIndex12);
			  
				/*
				 * String formOfLC = excelutils.getCellData(filepath, "RegisterLC", i, 0); 
				 * String aplbRules = excelutils.getCellData(filepath, "RegisterLC", i, 1);
				 * String avalBy = excelutils.getCellData(filepath, "RegisterLC", i, 2); String
				 * lcAmt = excelutils.getCellData(filepath, "RegisterLC", i, 3); String
				 * expiryDate = excelutils.getCellData(filepath, "RegisterLC", i, 4); String
				 * addAmtCovered = excelutils.getCellData(filepath, "RegisterLC", i, 5);
				 */
			RegisterLC registerLC = new RegisterLC(driver);
			registerLC.enterFormOfLC(formOfLC);	 
			registerLC.enterAPLB_Rules(aplbRules); 
			registerLC.enterAvalBy(avalBy);
			registerLC.enterLC_Amt(lcAmt);
			registerLC.enterExpiryDate(expiryDate);
			registerLC.enterAddAmtCovered(addAmtCovered);
			registerLC.copyrefnum();
			registerLC.partiestab();
			registerLC.clickApplicantIdButton();
			registerLC.enterAppCustId(appCustId);
			registerLC.clickBeneficiaryIdButton();
			registerLC.enterAppCustId(benCustId);
			registerLC.enterAccOfficeCode(acccofficecode);
			registerLC.enterSameAsApp(sameasapp);
			registerLC.advBankIdButton();
			registerLC.enterAppCustId(advBankId);
			registerLC.clickRiskTab();
			registerLC.clickbankliabiiltyButton();
			registerLC.enterAppCustId(custAccNo);
			registerLC.clickcustliabiiltyButton();
			registerLC.enterAppCustId(bankAccNo);
			 		
			System.out.println("Applicable Rules is."+expiryDate);
		}
		System.out.println("Register LC verification logic goes here.");
	}
	
	@Test(priority = 4)
	void Logout() {
		System.out.println("Logout verification logic goes here.");
	}
}
