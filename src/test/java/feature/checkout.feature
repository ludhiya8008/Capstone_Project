Feature: Login and Purchase Product

Scenario: User logs in and buys a hoodie
	Given User is on the Magento login page
	When User logs in with "ludhiya2002@gmail.com" and "Sampath@123"
	And User selects a hoodie and adds to cart
	Then the product should be displayed in the cart
	