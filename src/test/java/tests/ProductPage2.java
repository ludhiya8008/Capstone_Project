package tests;

import java.io.IOException;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ProductPage2 {
    WebDriver driver;
    FluentWait<WebDriver> wait;

    public ProductPage2(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(StaleElementReferenceException.class);
    }

    // Search for Product
    public void searchProduct(String searchQuery) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("search")));
        searchBox.sendKeys(searchQuery, Keys.ENTER);
    }

    // Select a Hoodie from Search Results
    public void selectHoodie() {
        WebElement hoodieElement = driver.findElement(By.xpath("//a[contains(text(),'Hoodie')]"));
        hoodieElement.click();
    }
    // Select Size
    public void selectSize(String size) {
        WebElement sizeOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'swatch-option text') and text()='" + size + "']")));
        sizeOption.click();
    }

    // Select Colour
    public void selectColour() {
        WebElement colourOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'swatch-option color')]")));
        colourOption.click();
    }

    // Add to Cart
    public void addToCart() {
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("product-addtocart-button")));
        addToCartButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'message-success')]")));
        System.out.println("Product added to cart!");
    }

    // Open Cart
    public void openCart() {
        WebElement myCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='action showcart']")));
        myCart.click();
    }

    // Get Cart Product Name
    public String getCartProductName() {
        WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='product-item-details']//a")));
        return productName.getText();
    }

//    
    public void viewAndEditCart() {
        WebElement viewEditCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("View and Edit Cart")));  //Locate "View and Edit Cart"
        viewEditCartBtn.click();
    }

    public void checkoutButton() {
        try {
            Thread.sleep(2000); // Small wait

            WebElement checkoutBtn = driver.findElement(By.xpath("//button[@id='proceed-to-checkout']"));

            // Scroll to button
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);

            // Wait and click
            wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();

            System.out.println("Clicked 'Proceed to Checkout'");
        } catch (Exception e) {
            System.out.println("ERROR: Cannot click 'Proceed to Checkout'");
        }
    }

}
//}
