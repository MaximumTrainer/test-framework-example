Feature: Bank Account Operations

  Scenario: Deposit funds into an account
    Given I have an account with a balance of £100
    When I deposit £50 into the account
    Then the balance should be £150

  Scenario: Withdraw funds from an account
    Given I have an account with a balance of £100
    When I withdraw £30 from the account
    Then the balance should be £70

  Scenario: Check the account balance
    Given I have an account with a balance of £100
    When I check the account balance
    Then the balance should be £100
