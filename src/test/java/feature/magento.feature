Feature: Magento Customer Login Functionality


Scenario: Successful login with valid credentials

			Given I am on the login page
			Given I am on the "Create an Account"
			When I enter first name in the First Name field.
			And I enter last name in the Last Name field.
			And I enter valid email address in the Email field.
			And I enter valid password in the Password field.
			And I enter password in the Confirm Password field.
			And I click on the "Create an Account" button
			Then I should be redirected to the customer dashboard
			Then I close the browser in Google Search.