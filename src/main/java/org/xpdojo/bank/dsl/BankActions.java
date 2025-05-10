package org.xpdojo.bank.dsl;

import org.xpdojo.bank.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class BankActions {
    private WebDriver driver;
    private LoginPage loginPage;

    public BankActions(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public void loginToBank(String user, String pass) {
        loginPage.login(user, pass);
    }
}
