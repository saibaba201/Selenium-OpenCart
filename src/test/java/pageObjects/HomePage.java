package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		//invoking parent class(BasePage) by using super keyword
		super(driver);
	}
	
	@FindBy(xpath="//a[@title='My Account']")
	WebElement myAccount;
	
	@FindBy(xpath="//li/a[text()='Register']")
	WebElement register;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement login;
	
	
	
	public void clickMyAccount() {
		myAccount.click();
		
	}
	
	
	public void clickRegister() {
		register.click();
		
	}
	
	public void clickLoginLink() {
		login.click();
	}
	
//	public AccountRegistrationPage clickRegister() {
//		register.click();
//		return new AccountRegistrationPage(driver);
//		
//	}
	
	
	
}
