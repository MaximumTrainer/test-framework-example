package org.xpdojo.bank;


import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.*;
import static org.xpdojo.bank.patterns.builder.AccountBuilder.anAccount;
import static org.xpdojo.bank.Money.amountOf;

public class BankAccountSteps {

    private Account account;

    @Given("a user has an existing bank account")
    public void a_user_has_an_existing_bank_account() {        
        // Initialize the account with a default balance of 0
      //        account = BankAccountPrototype.cloneBaseAccount();
  account =  anAccount().withBalance(amountOf(0)).build(); 
    }

    @When("the user deposits ${int}")
    public void the_user_deposits(Integer amount) {
        account.deposit(amountOf(amount));
    }

    @Then("the account balance should be updated to ${int}")
    public void the_account_balance_should_be_updated_to(Integer expectedBalance) {
        assertEquals(amountOf(expectedBalance), account.balance());
    }
}
