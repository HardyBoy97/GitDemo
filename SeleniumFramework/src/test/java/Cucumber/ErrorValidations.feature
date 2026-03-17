@tag

Feature: Error Validations

@tag2
Scenario Outline:
Given I landed on the Ecommerce page
When Logged in with username <username> and password <password> 
Then "Incorrect email or password" message is displayed
