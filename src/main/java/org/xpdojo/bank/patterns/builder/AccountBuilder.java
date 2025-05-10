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
