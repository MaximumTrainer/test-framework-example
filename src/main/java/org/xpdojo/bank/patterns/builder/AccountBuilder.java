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

package org.xpdojo.bank.patterns.builder;

import  org.xpdojo.bank.*;

public class AccountBuilder {

    private Money initialBalance = Money.ZERO;
    private Clock clock;

    private AccountBuilder() {}

    public static AccountBuilder anAccount() {
        return new AccountBuilder();
    }

    public AccountBuilder withBalance(Money balance) {
        this.initialBalance = balance;
        return this;
    }

    public AccountBuilder withClock(Clock clock) {
        this.clock = clock;
        return this;
    }

    public Account build() {
        if (clock == null) {
            throw new IllegalStateException("Clock must be provided to build an Account.");
        }
        return Account.accountWithBalance(initialBalance, clock);
    }
}
