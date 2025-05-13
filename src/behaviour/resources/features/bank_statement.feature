Feature: Bank Statement Access
  As a bank customer
  I want to login and view my bank statement
  So that I can review my transactions

  Scenario: Successful login and statement view
    Given I am on the bank login page
    When I enter username "testuser" and password "password123"
    And I click the login button
    Then I should be redirected to the bank statement page
    And I should see 3 transactions in the statement

  Scenario: Failed login with invalid credentials
    Given I am on the bank login page
    When I enter username "wronguser" and password "wrongpass"
    And I click the login button
    Then I should see an error message