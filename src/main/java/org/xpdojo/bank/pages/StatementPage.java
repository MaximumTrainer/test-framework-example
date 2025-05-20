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

package org.xpdojo.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class StatementPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By statementTable = By.id("statementTable");
    private By transactions = By.cssSelector("#statementTable tr");

    public StatementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDisplayed() {
        try {
            wait.until(ExpectedConditions.titleContains("Bank Statement"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(statementTable));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getTransactionCount() {
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(transactions));
        // Subtract 1 to account for the header row
        return Math.max(0, rows.size() - 1);
    }

    public String getTransactionDescription(int index) {
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(transactions));
        if (index < 1 || index >= rows.size()) {
            throw new IndexOutOfBoundsException("Transaction index out of range");
        }
        return rows.get(index).getText();
    }
}