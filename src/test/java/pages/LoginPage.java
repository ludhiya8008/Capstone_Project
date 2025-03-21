package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id = "email")
	private WebElement emailField;
	
	@FindBy(id = "pass")
	private WebElement passwordField;
	
	@FindBy(id = "send2")
	private WebElement signInButton;
	
	@FindBy(xpath = "//span[@class='logged-in']")
	private WebElement welcomeMessage;
	
	@FindBy(xpath = "//div[contains(@class,'message-error')]")
	private WebElement errorMessage;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}


	public void navigateToLoginPage() {
		// TODO Auto-generated method stub
		driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
		
	}


	public void enterEmail(String email) {
		// TODO Auto-generated method stub
		wait.until(ExpectedConditions.visibilityOf(emailField));
		emailField.sendKeys(email);
		
	}


	public void enterPassword(String password) {
		// TODO Auto-generated method stub
		passwordField.sendKeys(password);
		
	}


	public void clickSignIn() {
		// TODO Auto-generated method stub
		signInButton.click();
		
	}


	public String getCurrentUrl() {
		// TODO Auto-generated method stub
		
		return driver.getCurrentUrl();
	}


	public String getWelcomeMessage() {
		// TODO Auto-generated method stub
		wait.until(ExpectedConditions.visibilityOf(welcomeMessage));
		return welcomeMessage.getText();
	}
	public String getErrorMessage() {
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		return errorMessage.getText();
	}
	
	
		
	

}
