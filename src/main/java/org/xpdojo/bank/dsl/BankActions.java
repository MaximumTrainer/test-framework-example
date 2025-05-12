package org.xpdojo.bank.dsl;

import org.xpdojo.bank.pages.BankAccountBalancePage;
import org.xpdojo.bank.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class BankActions {
    private WebDriver driver;
    private LoginPage loginPage;
    private BankAccountBalancePage accountBalancePage;

    public BankActions(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.accountBalancePage = new BankAccountBalancePage(driver);
    }

    public void loginToBank(String user, String pass) {
        loginPage.login(user, pass);
    }

    public void checkBalance() {
        accountBalancePage.login(user, pass);
    }

    

}
