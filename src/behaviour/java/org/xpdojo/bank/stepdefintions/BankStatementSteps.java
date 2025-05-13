/*
 *
 * Copyright (c) 2019 xp-dojo organisation and committers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.xpdojo.bank.stepdefintions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.xpdojo.pages.LoginPage;
import org.xpdojo.pages.StatementPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class BankStatementSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private StatementPage statementPage;
    
    @Before
    public void setup() {
        driver = WebDriverManager.chromedriver().create();
    }
    
    @After
    public void teardown() {
        WebDriverManager.chromedriver().quit();
    }
    
    @Given("I am on the bank login page")
    public void i_am_on_the_bank_login_page() {
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/webapp/login.html");
        loginPage = new LoginPage(driver);
        assertTrue(loginPage.isDisplayed());
    }
    
    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }
    
    @When("I click the login button")
    public void i_click_the_login_button() {
        loginPage.clickLogin();
    }
    
    /*
     Then("I should be redirected to the bank statement page")
    public void i_should_be_redirected_to_the_bank_statement_page() {
        statementPage = new StatementPage(driver);
        assertTrue(statementPage.isDisplayed());
    }
    
    @Then("I should see {int} transactions in the statement")
    public void i_should_see_transactions_in_the_statement(int expectedCount) {
        assertEquals(expectedCount, statementPage.getTransactionCount());
    }
    */
    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        assertFalse(loginPage.getErrorMessage().isEmpty());
    }
}