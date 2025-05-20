package org.xpdojo.bank.patterns.prototype;

import org.xpdojo.bank.Account;
import org.xpdojo.bank.Clock;
import org.xpdojo.bank.Money;
import org.xpdojo.bank.Statement;
import org.xpdojo.bank.Transaction;

import java.io.Writer;
import java.time.Instant;
import static org.xpdojo.bank.Money.amountOf;
import static org.xpdojo.bank.Account.accountWithBalance;

public class BankAccountPrototype implements Cloneable {
    private final Account account;

	private static final Clock FIXED_CLOCK = () -> Instant.parse("2019-02-03T10:15:30Z");

    private static final Account baseAccount = accountWithBalance(amountOf(12345), FIXED_CLOCK);

    public static Account cloneBaseAccount() {
        return accountWithBalance(baseAccount.balance(), FIXED_CLOCK);
    }

    public BankAccountPrototype() {
        // Create an account with initial balance of zero
        account = cloneBaseAccount();
        
        // Add three transactions with different timestamps
        account.deposit(Money.amountOf(1000)); // Opening deposit
        account.withdraw(Money.amountOf(250)); // ATM withdrawal
        account.deposit(Money.amountOf(500));  // Salary deposit
        
    }

    public Account getAccount() {
        return account;
    }


    @Override
    public BankAccountPrototype clone() {
        try {
            return (BankAccountPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Could not clone BankAccountPrototype", e);
        }
    }
}
