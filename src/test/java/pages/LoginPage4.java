package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

public class LoginPage4 {
	WebDriver driver;
    FluentWait<WebDriver> wait;

    By emailField = By.id("email");
    By passwordField = By.id("pass");
    By loginButton = By.id("send2");

    public LoginPage4(WebDriver driver, FluentWait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void login(String email, String password) {
        WebElement emailInput = wait.until(d -> d.findElement(emailField));
        emailInput.sendKeys(email);
        
        WebElement passwordInput = wait.until(d -> d.findElement(passwordField));
        passwordInput.sendKeys(password);
        
        WebElement loginBtn = wait.until(d -> d.findElement(loginButton));
        loginBtn.click();
    }

	
}



