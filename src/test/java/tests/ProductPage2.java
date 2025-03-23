package tests;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
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
                .ignoring(NoSuchElementException.class);
    }

    public void selectProductAndAddToCart() {
        Actions actions = new Actions(driver);

        // Hover on Women menu
        WebElement womenMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Women']")));
        actions.moveToElement(womenMenu).perform();

        // Hover on Tops sub-menu
        WebElement topsMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Tops']")));
        actions.moveToElement(topsMenu).perform();

        // Click on Hoodies & Sweatshirts
        WebElement hoodiesAndSweatshirts = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Hoodies & Sweatshirts']")));
        hoodiesAndSweatshirts.click();
    }

    public void selectHoodie() {
        WebElement hoodie = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(@class, 'product-item-link')])[3]")));
        hoodie.click();
    }

    public void selectSize(String size) {
        WebElement sizeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'swatch-option text') and text()='" + size + "']")));
        sizeOption.click();
    }

    public void selectColour(String colour) {
        WebElement colourOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("option-label-color-93-item-52")));
        colourOption.click();
    }

    public void addToCart() {
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
        addToCartButton.click();
    }

    public void openCart() {
        WebElement myCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='action showcart']")));
        myCart.click();
    }

    public String getCartProductName() {
        WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#minicart-content-wrapper .product-item-name")));
        return productName.getText();
    }
}
