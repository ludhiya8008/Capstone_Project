package tests;


import org.testng.annotations.Parameters;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import pages.LoginPage3;


public class MagentoTest extends BaseClass{

	    private LoginPage3 loginPage;
	    private ProductPage productPage;
	    private ExtentTest test;

	    @Test
	    // Parameters tag will retrieve email and password
	    @Parameters({"email", "password"})
	    public void testLogin(String email, String password) {
	        test = extent.createTest("Login Test");

	        //login page initialise object
	        loginPage = new LoginPage3(driver);
	        test.log(Status.INFO, "Navigating to login page");

	        //Enter email with log action
	        loginPage.enterEmail(email);
	        test.log(Status.INFO, "Entered email: " + email);

	        //Enter password with log action
	        loginPage.enterPassword(password);
	        test.log(Status.INFO, "Entered password");

	        //click on Sign in with log action
	        loginPage.clickSignIn();
	        test.log(Status.INFO, "Clicked on Sign In button");
	    }

	    //Here we are using dependsOnMethods, that the test depends on successful execution of a Login test
	    @Test(dependsOnMethods = "testLogin")
	    public void testAddToCart() {
	        test = extent.createTest("Add to Cart Test");

	        //Initialise Productpage
	        productPage = new ProductPage(driver);
	        
	        //Navigate to Hoodies and Sweatshirts section
	        productPage.navigateToHoodiesAndSweatshirts();
	        test.log(Status.INFO, "Navigated to Hoodies & Sweatshirts");

	        //select first hoodie
	        productPage.selectFirstHoodie();
	        test.log(Status.INFO, "Selected first hoodie");

	        //select the size of an hoodie
	        productPage.selectSize("M");  // Select Medium size
	        test.log(Status.INFO, "Selected size: M");

	        //hoodie will be added to cart
	        productPage.addToCart();
	        test.log(Status.PASS, "Item added to cart successfully");
	    }
	}
