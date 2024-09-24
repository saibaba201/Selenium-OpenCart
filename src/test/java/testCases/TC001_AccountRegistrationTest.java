package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	
	@Test(groups= {"Regression","Masters"})
	public void verify_account_registration() {
		try {
		String generatedPassword= randomPassword();
		logger.info("**********Test Case started -> TC001_AccountRegistrationTest*************");
		HomePage homePage= new HomePage(driver);
		homePage.clickMyAccount();
		logger.info("HomePage Clicked");
		homePage.clickRegister();
		logger.info("Register Link clicked");
		AccountRegistrationPage reg=new AccountRegistrationPage(driver);
		logger.info("Provided Registration Details");
		reg.setFirstName(randomString());
		reg.setLastName(randomString());
		reg.setEmail(randomEmail());
		reg.setTelephone(randomNumber());
		reg.setPassword(generatedPassword);
		reg.setconfirmPassword(generatedPassword);
		reg.clickCheckbox();
		reg.clickSubmit();
		logger.info("Clicked Submit button");
		if(reg.getConfirmMsg().equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			logger.error("*********Test case Failed -> TC001_AccountRegistrationTest********");
			logger.debug("**********Debug logs***********");
			Assert.assertTrue(false);
		}
		
		}catch(Exception e) {
			Assert.fail();
		}
		
	}
}
