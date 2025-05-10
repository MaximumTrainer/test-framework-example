package org.xpdojo.bank;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.xpdojo.bank.dsl.BankActions;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(ConcordionRunner.class)
public class BankSpecificationTest {
    //TODO - update to example in xpdojo
    private BankActions bankActions;

    public BankSpecificationTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        this.bankActions = new BankActions(driver);
    }

    public boolean loginToBank(String username, String password) {
        try {
            bankActions.loginToBank(username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
