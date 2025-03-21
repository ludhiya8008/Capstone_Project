@Login
Feature: Magento User Login Functionality

As an e-commerce user
I want to authenticate myself on Magento
So I can access my account

@Smoke 
Scenario Outline: Successful login with valid credentials

	Given I navigate to Magento login page
	When I enter email "<email>"
	And I enter password "<password>"
	And I click the Sign In button
	Then I should be redirected to my account dashboard
	And I should see the welcome message containing "<welcome_text>"
	
  Examples:
	|  email               | password     | welcome_text                |
	| ludhiya2002@gmail.com| Sampath@123  | Welcome, Sampath Payagalla! |