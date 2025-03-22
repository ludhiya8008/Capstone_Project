Feature: User Registration

  @InvalidSignUp
  Scenario Outline: User attempts to create an account with invalid details
    Given User is on the Magento Sign Up page
    When User enters invalid first name "<firstName>", last name "<lastName>", email "<email>", password "<password>", and confirm password "<confirmPassword>"
    And Clicks on Create Account button
    Then User should see an error message

    Examples:
      | firstName | lastName    | email            | password | confirmPassword |
      | ludhiya   | ludh!@      | invalid.com      | pass     | pass            |
      
