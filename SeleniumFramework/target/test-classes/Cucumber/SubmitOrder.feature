@tag
Feature: Purchase the order from ECommerce site

  Background:
    Given I landed on the Ecommerce page

  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with the username <username> and password <password>
    When I add the product <productName> to the cart
    And Checkout the <productName> and select the <desiredCountryabbr> and <desiredCountry> and Submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page

    Examples:
      | username                 | password | productName |desiredCountryabbr|desiredCountry|
      | hardyboy959595@gmail.com | Test1234 | ZARA COAT 3 |ind 				 | India		|
