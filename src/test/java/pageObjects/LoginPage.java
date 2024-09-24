package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="input-email")
	WebElement EmailTextBox;
	
	@FindBy(id="input-password")
	WebElement PasswordTextBox; 
	
	@FindBy(xpath="//input[contains(@value,'Login')]")
	WebElement clickloginbutton;
	
	public void setEmail(String email) {
		EmailTextBox.sendKeys(email);
	}
	
	public void setPassword(String password) {
		PasswordTextBox.sendKeys(password);
	}
	
	public void clkloginbutton() {
		clickloginbutton.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
