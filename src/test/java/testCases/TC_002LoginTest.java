package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002LoginTest extends BaseClass {

	@Test(groups={"Sanity","Masters"})
	public void verify_loginTest() {
		try {
			logger.info("*******verify_loginTest Started********");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLoginLink();
			logger.info("*******Login Page Started********");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(prop.getProperty("email"));
			lp.setPassword(prop.getProperty("password"));
			lp.clkloginbutton();
			logger.info("************Account Page****************");
			MyAccountPage map = new MyAccountPage(driver);
			boolean result = map.isMyAccountExists();
			if (result == true) {
				logger.info("*******verify_loginTest Success********");
				Assert.assertTrue(result);

			} else {

				logger.info("*******verify_loginTest Failed********");
				logger.debug("***debug logs Strated for verify_loginTest***");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.fail();

		}
	}
}
