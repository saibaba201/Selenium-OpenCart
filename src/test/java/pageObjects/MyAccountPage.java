package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement myAccountHeading;
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")
	WebElement logout;

	public boolean isMyAccountExists() {
		try {
			return myAccountHeading.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	public void clickLogoutButton() {
		logout.click();
	}
	
}
