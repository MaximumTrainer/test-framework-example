
package org.xpdojo.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// todo - refactor into check account balance page
public class BankAccountBalancePage {
    private WebDriver driver;
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login");

    public BankAccountBalancePage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}