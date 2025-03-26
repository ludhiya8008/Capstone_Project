package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

public class HomePage {
	
		WebDriver driver;
	    FluentWait<WebDriver> wait;

	    // Locators
	    private By searchBox = By.id("search");
	    private By searchButton = By.xpath("//button[@title='Search']");

	    // Constructor
	    public HomePage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new FluentWait<>(driver)
	                .withTimeout(Duration.ofSeconds(15))
	                .pollingEvery(Duration.ofMillis(500))
	                .ignoring(NoSuchElementException.class);
	    }

	    // Method to search for a product
	    public void searchProduct(String productName) {
	        WebElement search = wait.until(d -> d.findElement(searchBox));
	        search.clear();
	        search.sendKeys(productName);
	        driver.findElement(searchButton).click();
	        System.out.println("Searched for: " + productName);
	    }
	}

