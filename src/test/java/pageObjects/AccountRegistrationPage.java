package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-firstname")
	WebElement txtFirstName;

	@FindBy(id = "input-lastname")
	WebElement txtLastName;

	@FindBy(id = "input-email")
	WebElement txtEmail;

	@FindBy(id = "input-telephone")
	WebElement txtTelephone;

	@FindBy(id = "input-password")
	WebElement txtPassword;

	@FindBy(id = "input-confirm")
	WebElement txtConfirmPassword;

	@FindBy(css = "input[type='checkbox']")
	WebElement txtCheckboxAgree;

	@FindBy(css = "input[type='submit']")
	WebElement submit;

	@FindBy(xpath = "//div[@id='content']/h1")
	WebElement msgConfirmation;

	public void setFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void setconfirmPassword(String confirmPassword) {
		txtConfirmPassword.sendKeys(confirmPassword);
	}

	public void clickCheckbox() {
		txtCheckboxAgree.click();
	}

	public void clickSubmit() {
		submit.click();
	}

	public String getConfirmMsg() {
		try {
			return msgConfirmation.getText();

		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
