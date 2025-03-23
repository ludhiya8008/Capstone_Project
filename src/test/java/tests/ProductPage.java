package tests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ProductPage {

		    private WebDriver driver;
		    private WebDriverWait wait;

		    //Constructor to initialise WebDriver and ExplicitlyWait
		    public ProductPage(WebDriver driver) {
		        this.driver = driver;
		        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    }

		    //go to the magento website and select women and uses actions class to perform
		    public void navigateToHoodiesAndSweatshirts() {
		        Actions actions = new Actions(driver);

		        //women menu
		        WebElement womenMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Women']")));
		        actions.moveToElement(womenMenu).perform();

		        //tops sub-menu
		        WebElement topsMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Tops']")));
		        actions.moveToElement(topsMenu).perform();

		        //click on Hoodies & Sweatshirts
		        WebElement hoodiesAndSweatshirts = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Hoodies & Sweatshirts']")));
		        hoodiesAndSweatshirts.click();
		    }

		    //Using xpath(locators) for firsthoodie, size, and then add to cart
		    public void selectFirstHoodie() {
		        WebElement firstHoodie = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(@class, 'product-item-link')])[3]")));
		        firstHoodie.click();
		    }

		    public void selectSize(String size) {
		        WebElement sizeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'swatch-option text') and text()='" + size + "']")));
		        sizeOption.click();
		    }

		    //click on add to cart and add hoodie
		    public void addToCart() {
		        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
		        addToCartButton.click();
		    }
		}
	
