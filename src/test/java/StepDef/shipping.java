package StepDef;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;




public class shipping {
	
	WebDriver driver;
	WebDriverWait wait;

	@When("User logs in with valid credentials")
	public void user_logs_in_with_valid_credentials() {
		driver=new ChromeDriver();
		driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        driver.findElement(By.id("email")).sendKeys("ludhiya2002@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Sampath@123");
        driver.findElement(By.id("send2")).click();
	}

	@When("User searches for {string} in Women's category")
	public void user_searches_for_in_women_s_category(String searchTerm) {
		
		    driver.get("https://magento.softwaretestingboard.com/");

		    // Ensure wait is initialized before using it
		    wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    // Locate the search bar and enter the search term
		    WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("search")));
		    searchBox.sendKeys(searchTerm, Keys.ENTER);

		    // Wait for the search results page to load
		    wait.until(ExpectedConditions.titleContains("Hoodies & Sweatshirts"));

		    // Validate search results page
		    String pageTitle = driver.getTitle();
		    Assert.assertTrue(pageTitle.contains("Hoodies & Sweatshirts"), "Search Page Title Validation Failed!");

		    // Verify if products are displayed
		    List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-item-info")));
		    Assert.assertFalse(products.isEmpty(), "No products found for Hoodies & Sweatshirts!");

		    System.out.println("Successfully searched and found Hoodies & Sweatshirts for Women.");
		}

	
	

	@When("User selects random hoodies with random size and color")
	public void user_selects_random_hoodies_with_random_size_and_color() {
		selectRandomWomenHoodieAndAddToCart();
	}

	
	

	private void selectRandomWomenHoodieAndAddToCart() {
		// TODO Auto-generated method stub
		driver.get("https://magento.softwaretestingboard.com/women/tops-women/hoodies-and-sweatshirts-women.html");

	    List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	            By.xpath("//li[contains(@class,'product-item')]//a[contains(@class,'product-item-link')]")));

	    if (products.isEmpty()) {
	        System.out.println("Error: No Women's Hoodies & Sweatshirts found!");
	        return;
	    }

	    Random random = new Random();
	    WebElement randomProduct = products.get(random.nextInt(products.size()));

	    System.out.println("Selecting product: " + randomProduct.getText());
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomProduct);
	    randomProduct.click();

	    selectRandomSize();
	    selectRandomColor();
	    addToCart();
	}
	public void selectRandomSize() {
	    try {
	        List<WebElement> sizes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	                By.xpath("//div[contains(@class, 'swatch-option text')]")));

	        if (!sizes.isEmpty()) {
	            WebElement randomSize = sizes.get(new Random().nextInt(sizes.size()));
	            randomSize.click();
	            System.out.println("Selected Size: " + randomSize.getText());
	        } else {
	            System.out.println("No size options available.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error: Unable to select size.");
	    }
	}

	public void selectRandomColor() {
	    try {
	        List<WebElement> colors = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	                By.xpath("//div[contains(@class, 'swatch-option color')]")));

	        if (!colors.isEmpty()) {
	            WebElement randomColor = colors.get(new Random().nextInt(colors.size()));
	            randomColor.click();
	            System.out.println("Selected Color: " + randomColor.getAttribute("aria-label"));
	        } else {
	            System.out.println("No color options available.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error: Unable to select color.");
	    }
	}

	public void addToCart() {
	    try {
	        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
	        addToCartButton.click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'message-success')]")));
	        System.out.println("Product added to cart!");
	    } catch (TimeoutException e) {
	        System.out.println("Error: Unable to add product to cart");
	    }
		
	}

	@When("User adds multiple products to the cart")
	public void user_adds_multiple_products_to_the_cart() {
		int numberOfProductsToAdd = 3; // You can change this number if needed

	    for (int i = 0; i < numberOfProductsToAdd; i++) {
	        selectRandomWomenHoodieAndAddToCart();
	    }
	    
	    driver.findElement(By.cssSelector(".showcart")).click();
	
	}

	@When("User views and edits the cart")
	public void user_views_and_edits_the_cart() {
	    driver.findElement(By.linkText("View and Edit Cart")).click();
	}

	@When("User removes one product from the cart")
	public void user_removes_one_product_from_the_cart() {
	    List<WebElement> removeButtons = driver.findElements(By.cssSelector(".action.action-delete"));
	    if (!removeButtons.isEmpty()) {
	        removeButtons.get(0).click(); // Remove the first product
	    }
	}

	@When("User proceeds to checkout")
	public void user_proceeds_to_checkout() {
	    driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']")).click();
	}

	@When("User enters shipping details and selects a shipping method")
	public void user_enters_shipping_details_and_selects_a_shipping_method() {
		
		    try {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Wait until the shipping form is loaded
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout-step-shipping")));

		        // Enter First Name
		        WebElement firstName = driver.findElement(By.name("firstname"));
		        firstName.clear();
		        firstName.sendKeys("Sampath");
		        

		        // Enter Last Name
		        WebElement lastName = driver.findElement(By.name("lastname"));
		        lastName.clear();
		        lastName.sendKeys("Payagalla");
		      

		        // Enter Street Address
		        WebElement streetAddress = driver.findElement(By.name("street[0]"));
		        streetAddress.clear();
		        streetAddress.sendKeys("123, MG Road");

		        // Enter City
		        WebElement city = driver.findElement(By.name("city"));
		        city.clear();
		        city.sendKeys("Hyderabad");

		        

		        // State
		        WebElement stateDropdown = driver.findElement(By.name("region_id"));
	            Select statenameDropdown = new Select(stateDropdown);
	            statenameDropdown.selectByIndex(0);
	            
		        // Enter Postcode
		        WebElement postcode = driver.findElement(By.name("postcode"));
		        postcode.clear();
		        postcode.sendKeys("500007");
		        
		        // Select Country (Dropdown)
		        WebElement countrySelect = wait.until(ExpectedConditions.elementToBeClickable(By.name("country_id")));
		        Select countryNameDropdown = new Select(countrySelect);
				countryNameDropdown.selectByVisibleText("India");
				
		        // Enter Phone Number
		        WebElement phone = driver.findElement(By.name("telephone"));
		        phone.clear();
		        phone.sendKeys("9876543210");

		        // Select Shipping Method
		        List<WebElement> shippingMethods = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='ko_unique_1']")));
		        if (!shippingMethods.isEmpty()) {
		            js.executeScript("arguments[0].click();", shippingMethods.get(0)); // Click first available shipping method
		        }

		        // Click 'Next' button to proceed
		        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button.action.continue.primary")));
		        js.executeScript("arguments[0].click();", continueButton);

		        System.out.println("Shipping details entered successfully!");

		    } catch (Exception e) {
		        System.out.println("Error while entering shipping details: " + e.getMessage());
		    }
		    
	}
	
	@When("User places the order")
	public void user_places_the_order() {
		
		}


	@Then("User logs out successfully")
	public void user_logs_out_successfully() {
	    driver.findElement(By.cssSelector(".customer-menu .authorization-link")).click();
	    driver.quit();
	}
}
