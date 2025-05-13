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

import org.xpdojo.bank.Statement;
import org.xpdojo.bank.Transaction;
import org.openqa.selenium.WebDriver;

public class StatementPage {
    private WebDriver driver;
    private Statement statement;

    public StatementPage(WebDriver driver, Statement statement) {
        this.driver = driver;
        this.statement = statement;
    }

    public boolean isDisplayed() {
        return driver.getTitle().equals("Bank Statement");
    }
/* 
    public int getTransactionCount() {
        return statement.getTransactions().size();
    }

    public String getTransactionDescription(int index) {
        Transaction transaction = statement.getTransactions().get(index - 1); // Adjusting for 0-based index
        return transaction.getType() + ": " + transaction.getAmount();
    }
    */
}