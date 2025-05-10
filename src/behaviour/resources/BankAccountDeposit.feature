Feature: Bank Account Operations

  Scenario: User deposits money into their account
    Given a user has an existing bank account
    When the user deposits $100
    Then the account balance should be updated to $100