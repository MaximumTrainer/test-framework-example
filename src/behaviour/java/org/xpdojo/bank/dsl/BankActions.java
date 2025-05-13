package org.xpdojo.bank.dsl;

import org.openqa.selenium.WebDriver;
import org.xpdojo.bank.pages.LoginPage;
import org.xpdojo.bank.pages.StatementPage;

public class BankActions {
    private WebDriver driver;
    private LoginPage loginPage;
    private StatementPage statementPage;

    public BankActions(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    //    this.statementPage = new StatementPage(driver);
    }

    public void loginToBank(String user, String pass) {
        loginPage.login(user, pass);
    }

    /*
    public void checkBalance() {
        statementPage.login(user, pass);
    }
*/
    

}
