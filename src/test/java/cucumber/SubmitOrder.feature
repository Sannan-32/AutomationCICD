
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting my order
  
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the ConfirmationPage

    Examples: 
      | name  							 |     password 		 |   productName  			|
      | Selenium32@gmail.com |     Selenium@32 	 |   IPHONE 13 PRO 			|
