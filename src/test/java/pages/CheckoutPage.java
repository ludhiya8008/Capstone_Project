package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void enterShippingDetails() {
        try {
            fillField(By.name("firstname"), "Ludhiya");
            fillField(By.name("lastname"), "Payagalla");
            fillField(By.name("street[0]"), "123 Main Street");
            fillField(By.name("city"), "Hyderabad");
            
            selectDropdown(By.name("region_id"), "Telangana");
            
            fillField(By.name("postcode"), "500081");
            fillField(By.name("telephone"), "9876543210");

            selectShippingMethod();
            clickNextButton();
            
            System.out.println("Shipping details entered successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter shipping details: " + e.getMessage());
        }
    }

    public void placeOrder() {
        try {
            WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[title='Place Order']")));
            placeOrderButton.click();
            
            wait.until(ExpectedConditions.urlContains("success"));
            System.out.println("Order placed successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to place order: " + e.getMessage());
        }
    }

    private void fillField(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    private void selectDropdown(By locator, String visibleText) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(visibleText);
    }

    private void selectShippingMethod() {
        WebElement shippingMethod = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("input[name='ko_unique_1']")));
        shippingMethod.click();
    }

    private void clickNextButton() {
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button.continue")));
        nextButton.click();
        wait.until(ExpectedConditions.invisibilityOf(nextButton));
    }
}