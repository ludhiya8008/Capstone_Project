package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage3 {
	
	    WebDriver driver;
	    WebDriverWait wait;

	    @FindBy(id = "email")
	    private WebElement emailField;

	    @FindBy(id = "pass")
	    private WebElement passwordField;

	    @FindBy(id = "send2")
	    private WebElement signInButton;

	    public LoginPage3(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        PageFactory.initElements(driver, this);
	    }

	    public void enterEmail(String email) {
	        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
	    }

	    public void enterPassword(String password) {
	        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
	    }

	    public void clickSignIn() {
	        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
	    }

		
	}
