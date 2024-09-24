package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003LoginDDT extends BaseClass {

	@Test(dataProvider="loginData",dataProviderClass=DataProviders.class)
	public void verify_loginDDT(String userName,String password,String res) {
	
			logger.info("******* Started TC_003LoginDDT********");
		try {	
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLoginLink();
			logger.info("*******Login Page Started********");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(userName);
			lp.setPassword(password);
			lp.clkloginbutton();
			logger.info("************Account Page****************");
			MyAccountPage myAcc = new MyAccountPage(driver);
			boolean targetPage = myAcc.isMyAccountExists();
			
			if(res.equalsIgnoreCase("valid")) {
				if(targetPage==true) {
					myAcc.clickLogoutButton();
					Assert.assertTrue(true);
				}else {
					Assert.assertTrue(false);
				}
			}
			
			if(res.equalsIgnoreCase("invalid")) {
				if(targetPage==true) {
					myAcc.clickLogoutButton();
					Assert.assertTrue(false);
				}else {
					Assert.assertTrue(true);
				}
			}
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("*********Finshed TC_003LoginDDT ***********");
	}
}
	
