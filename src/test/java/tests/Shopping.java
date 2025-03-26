package tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Shopping {
    WebDriver driver;
    WebDriverWait wait;
   
    

    //Runs once before any test method in the class
    @BeforeClass
    public void setup() {
    	
        
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        

        // Login
       
        driver.findElement(By.id("email")).sendKeys("ludhiya2002@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Sampath@123");
        driver.findElement(By.id("send2")).click();
       
        
        // Verify login success
        Assert.assertTrue(driver.getCurrentUrl().contains("customer/account"), "Login failed!");
       
    }

    @Test(priority = 1)
    
    public void user_searches_for_women_s_hoodies_and_sweatshirts() {
    	
        driver.get("https://magento.softwaretestingboard.com/");

        // Ensure wait is initialized before using it
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the search bar and enter the updated search term
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("search")));
        searchBox.sendKeys("Hoodies and sweatshirts for women", Keys.ENTER);

        // Wait for the search results page to load
        wait.until(ExpectedConditions.titleContains("Hoodies and sweatshirts for women"));

        // Validate search results page
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Hoodies and sweatshirts for women"), "Search Page Title Validation Failed!");

        // Verify if products are displayed
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-item-info")));
        Assert.assertFalse(products.isEmpty(), "No products found for Women's Hoodies & Sweatshirts!");

        
        System.out.println("Successfully searched and found Women's Hoodies & Sweatshirts.");
    }

    @Test(priority = 2)
    public void user_adds_products_to_cart() {
    	
    	try {
            driver.get("https://magento.softwaretestingboard.com/women/tops-women/hoodies-and-sweatshirts-women.html");
            
            // Get all product elements
            List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//li[contains(@class,'product-item')]//a[contains(@class,'product-item-link')]")));
            
            Assert.assertFalse(products.isEmpty(), "No products found!");

            Random random = new Random();
            int numberOfProductsToAdd = 3; // Change this to add more/fewer products
            
            // Store product URLs to avoid StaleElementReferenceException
            List<String> productUrls = new ArrayList<>();
            for (WebElement product : products) {
                productUrls.add(product.getAttribute("href"));
            }
            
            // Add specified number of random products
            for (int i = 0; i < numberOfProductsToAdd && !productUrls.isEmpty(); i++) {
                // Select random product URL
                String randomProductUrl = productUrls.remove(random.nextInt(productUrls.size()));
                driver.get(randomProductUrl);
                
                // Select random options and add to cart
                selectRandomSize();
                selectRandomColor();
                addToCart();
                System.out.println("Added product " + (i+1) + " of " + numberOfProductsToAdd);
                
                // Go back to category page if not last product
                if (i < numberOfProductsToAdd - 1) {
                    driver.get("https://magento.softwaretestingboard.com/women/tops-women/hoodies-and-sweatshirts-women.html");
                }

            }
            
            // Verify cart count updated
            WebElement cartCounter = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".counter-number")));
            Assert.assertEquals(Integer.parseInt(cartCounter.getText()), 3, 
                "Cart count doesn't match added products");
                
        } catch (Exception e) {
            System.out.println("Failed to add products: " + e.getMessage());
            throw e;
        }
    }
    public void selectRandomSize() {
        List<WebElement> sizes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class, 'swatch-option text')]")));

        if (!sizes.isEmpty()) {
            WebElement randomSize = sizes.get(new Random().nextInt(sizes.size()));
            randomSize.click();
        }
    }

    public void selectRandomColor() {
        List<WebElement> colors = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class, 'swatch-option color')]")));

        if (!colors.isEmpty()) {
            WebElement randomColor = colors.get(new Random().nextInt(colors.size()));
            randomColor.click();
        }
    }

    public void addToCart() {
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
        addToCartButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'message-success')]")));
    }

    @Test(priority = 3)
    public void user_views_and_edits_cart() {
        driver.findElement(By.cssSelector(".showcart")).click();
        driver.findElement(By.linkText("View and Edit Cart")).click();
    }

    @Test(priority = 4)
    public void user_removes_product_from_cart() {
        List<WebElement> removeButtons = driver.findElements(By.cssSelector(".action.action-delete"));
        if (!removeButtons.isEmpty()) {
            removeButtons.get(0).click();
        }
    }

    @Test(priority = 5)
    public void user_proceeds_to_checkout() {
        driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']")).click();
    }

    @Test(priority = 6)
    public void user_enters_shipping_details() {
        try {
            // Wait for shipping form to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#shipping")));

           

//            // 2. Enter Name
//            driver.findElement(By.cssSelector("[name='firstname']"));
//            driver.findElement(By.cssSelector("[name='lastname']"));
//
//            // 3. Enter Address
//            driver.findElement(By.cssSelector("[name='street[0]']")).sendKeys("123 Main St");
//            driver.findElement(By.cssSelector("[name='city']")).sendKeys("New York");

//            // 4. Select State/Region (dynamic dropdown)
//            WebElement regionDropdown = wait.until(ExpectedConditions.elementToBeClickable(
//                By.cssSelector("[name='region_id']")));
//            new Select(regionDropdown).selectByVisibleText("New York");
//
//            // 5. Enter ZIP Code
//            driver.findElement(By.cssSelector("[name='postcode']")).sendKeys("10001");
//
//            // 6. Select Country
//            WebElement countryDropdown = driver.findElement(By.cssSelector("[name='country_id']"));
//            new Select(countryDropdown).selectByVisibleText("United States");
//
//            // 7. Enter Phone Number
//            driver.findElement(By.cssSelector("[name='telephone']")).sendKeys("5551234567");

            // 8. Select Shipping Method (radio button)
            WebElement shippingMethod = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[name='ko_unique_1']")));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", shippingMethod);

            // 9. Click Next (with screenshot)
            screenshot("before_next_click");
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".button.action.continue.primary")));
            nextButton.click();

            // Verify moved to payment
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#payment")));

        } catch (Exception e) {
            screenshot("shipping_details_error");
            Assert.fail("Failed to enter shipping details: " + e.getMessage());
        }
    }
    

    private void screenshot(String name) {
		// TODO Auto-generated method stub
    	try {
	        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(src, new File("screenshots/" + name + "_" + System.currentTimeMillis() + ".png"));
	    } catch (Exception e) {
	        System.out.println("Screenshot failed: " + e.getMessage());
	    }
	}
		

	@Test(priority = 7)
	
    public void user_places_the_order() {
		
        try {
            // Wait for the page to load completely
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask")));

            // Scroll to the place order button
            WebElement placeOrderBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(@class,'checkout') and contains(@title,'Place Order')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", placeOrderBtn);
            
            // Add a small delay to ensure button is clickable
            Thread.sleep(1000);
            
            // Click using JavaScript to avoid interception issues
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderBtn);

            // Wait for success message with longer timeout
            WebElement successMessage = wait.withTimeout(Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(),'Thank you for your purchase!')]")));
            Assert.assertTrue(successMessage.isDisplayed(), "Order placement failed!");

            
        } catch (Exception e) {
            // Take screenshot for debugging
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File("order_failure.png"));
            } catch (IOException io) {
                io.printStackTrace();
            }
            Assert.fail("Error while placing the order: " + e.getMessage());
        }
    
    }

    @Test(priority = 8)
    public void user_logs_out() {
    	
        try {
            // Wait for account dropdown to be clickable
            WebElement accountDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='panel header']//button[@type='button']")));
            accountDropdown.click();
            
            // Wait for logout link and click
            WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@aria-hidden='false']//a[contains(text(),'Sign Out')]")));
            logoutLink.click();
            
            // Verify logout success
            wait.until(ExpectedConditions.urlContains("logoutSuccess"));
           
        } catch (Exception e) {
            Assert.fail("Error while logging out: " + e.getMessage());
        }
        System.out.println("Added multiple products to cart and done proceed to checkout and place order successfully");
        System.out.println("Log out successfully");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
