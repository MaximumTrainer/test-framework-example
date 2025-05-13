# About This Project


# Frameworks & Example Patterns
- Cucumber – For BDD-style test definitions.
- WebdriverIO – For UI automation.
- Builder Pattern – For creating test data objects cleanly.
- Prototype Pattern – For cloning reusable test data.
- DSL (Domain-Specific Language) Driver – To encapsulate actions in a readable way.
- Page Object Pattern – To structure UI interactions.

[Other Unit Test Pattern Examples](testPatterns.md)
[Pattern sumary from the book Growing Object-Oriented Software Guided by Tests](goosPatterns.md)


# Project Structure
```
atdd-bank-test-framework/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── org.xpdojo.bank/
│   │   │   │   ├── patterns/
│   │   │   │   │   ├── builder/            # Builder pattern for test data
│   │   │   │   │   ├── prototype/          # Prototype pattern for cloning test data
│   │   │   │   ├── utilities/              # Common utilities for tests
│   │   │   │   ├── webdrivers/             # WebDriver configuration
│   ├── test/
│   │   ├── java/
│   │   │   ├── org.xpdojo.bank/           # Test scenarios
│   │   ├── resources/
│   ├── behaviour/
│   │   ├── java/
│   │   │   ├── org.xpdojo.bank/            # Cucumber Behaviour Test scenarios
│   │   │   │   ├── dsl/                    # DSL for interacting with the system
│   │   │   │   ├── pages/                  # Page Object Model implementation
│   │   ├── resources/
│   │   │   ├── features/                   # Cucumber feature files
│   ├── webdriverio/                        # WebdriverIO setup for UI automation
│   ├── specifications/
│   │   ├── java/
│   │   │   ├── org.xpdojo.bank/            # Concordion specification scenarios
│   │   ├── resources/
│   │   │   ├── specifications/             # Concordion specification files
│── pom.xml (Maven dependencies)
│── wdio.conf.js (WebdriverIO config)
```

# About Cucumber & Concordion 

Both **Cucumber** and **Concordion** are used for automated acceptance testing, but they serve slightly different purposes and are best suited for different scenarios. Here’s when to use each:

### **Use Cucumber when:**  
✅ **Behavior-Driven Development (BDD)**: You want to write tests in **Gherkin** (Given-When-Then) that are readable by non-technical stakeholders.  
✅ **Collaboration Between Teams**: Business analysts, testers, and developers can contribute to test scenarios.  
✅ **Automating User Stories**: You want to validate business rules and workflows in a structured, example-driven way.  
✅ **Multiple Language Support**: Cucumber supports Java, JavaScript, Ruby, and other languages.  
✅ **Complex UI or API Tests**: Works well with Selenium, REST Assured, etc., for end-to-end and integration testing.  

**Example Cucumber test (Gherkin):**
```gherkin
Feature: Login to the application
  Scenario: Successful login
    Given the user is on the login page
    When they enter valid credentials
    Then they should be redirected to the dashboard
```

### **Use Concordion when:**  
✅ **Specification-Driven Testing**: You want **living documentation** that acts as both a specification and an automated test.  
✅ **Plain HTML-Based Tests**: Test cases are embedded inside HTML with simple commands.  
✅ **Focused on Business Logic**: Best for verifying algorithms, rules, and calculations in backend systems.  
✅ **Less Technical Users Writing Tests**: Business analysts can maintain documentation that doubles as test cases.  
✅ **Java Ecosystem**: Designed mainly for Java projects.  

**Example Concordion test (HTML with Java binding):**
```html
<h2>Login Functionality</h2>
<p>When a user enters "<span concordion:set="#username">validUser</span>" and "<span concordion:set="#password">validPass</span>",</p>
<p>They should be logged in: <span concordion:assertEquals="#loginResult">true</span></p>
```
_(Backed by Java methods that process the inputs and return the expected output.)_

### **Key Differences**  
| Feature         | Cucumber | Concordion |
|---------------|----------|------------|
| **Test Style** | Gherkin (Given-When-Then) | HTML-based documentation |
| **Best For** | User stories & behavior testing | Specifications & business rules |
| **Readability** | Good for business & devs | Great for business & documentation |
| **Integration** | Works with Selenium, APIs | Mostly Java-based backend tests |
| **Stakeholder Involvement** | High (business + QA + devs) | Moderate (QA + business analysts) |

### **Which One to Choose?**  
- If you need **collaborative BDD-style tests** that business users can help write → **Cucumber**  
- If you need **executable specifications** that act as documentation and verify business rules → **Concordion**  


# Using Builder to create Domain Specific Code

The **Builder** and **Prototype** patterns are useful for creating objects in a controlled way, which helps in **domain-specific testing and code** by making it easier to construct complex test data, maintain test readability, and ensure consistency. Let’s break down how each pattern can be applied:

---

### **1. Builder Pattern for Domain-Specific Tests**
The **Builder pattern** is used when you need to construct complex objects step by step. In domain-specific testing, this helps create **test objects with different configurations** without cluttering test code with long constructor calls.

#### **Use Case in Testing**
Imagine testing a system that deals with user profiles. Instead of repeatedly writing long constructor arguments, a **UserBuilder** class can be used:

#### **Example**
```java
public class Account {
    private String accountNumber;
    private Money balance;
    private Statement statement;

    private Account(AccountBuilder builder) {
        this.accountNumber = builder.accountNumber;
        this.balance = builder.balance;
        this.statement = builder.statement;
    }

    public static class AccountBuilder {
        private String accountNumber;
        private Money balance = Money.amountOf(0); // Default balance
        private Statement statement = new Statement(); // Default empty statement

        public AccountBuilder withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public AccountBuilder withBalance(Money balance) {
            this.balance = balance;
            return this;
        }

        public AccountBuilder withStatement(Statement statement) {
            this.statement = statement;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", statement=" + statement +
                '}';
    }
}
```

#### **Usage in Tests**
```java
User testUser = new User.UserBuilder()
    .withName("Alice")
    .withAge(30)
    .withEmail("alice@example.com")
    .build();
```
This makes test code **cleaner, readable, and flexible**, allowing tests to specify only the relevant attributes.

---

### **2. Prototype Pattern for Domain-Specific Code**
The **Prototype pattern** allows objects to be cloned instead of being manually instantiated every time. This is useful when **creating multiple variations of a base object with slight modifications**.

#### **Use Case in Domain Code**
Consider a **Document** class where each user works on a slightly modified version of a base document template.

#### **Example**
```java
public class Document implements Cloneable {
    private String title;
    private String content;

    public Document(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Document clone() {
        try {
            return (Document) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning failed", e);
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Document{" + "title='" + title + '\'' + ", content='" + content + '\'' + '}';
    }
}
```

#### **Usage in Domain Code**
```java
Document template = new Document("Contract", "Standard contract content...");
Document userDocument = template.clone();
userDocument.setTitle("Alice's Contract");
userDocument.setContent("Modified contract for Alice.");

System.out.println(userDocument);
```
By using **Prototype**, we avoid recreating objects from scratch and **preserve domain-specific configurations** efficiently.

---

### **Combining Both Patterns**
- Use **Builder** when you need to construct complex objects **in tests** with various configurations.
- Use **Prototype** when you need to **duplicate** objects efficiently in domain-specific logic, such as templates or configurable settings.

Sure! Let's combine the **Builder** and **Prototype** patterns in a domain-specific scenario.  

---

## **Scenario: User Profile Templates**
- We need a **UserProfile** object with different configurations for tests.
- Some users will be based on **existing profiles** (Prototype pattern).
- We will use **Builder** to construct and customize user profiles efficiently.

---

### **Step 1: Create the `Account` Class with Prototype**
This class represents a user profile that can be cloned.

```java
public class Account implements Cloneable {
    private String accountNumber;
    private Money balance;
    private Statement statement;

    public Account(String accountNumber, Money balance, Statement statement) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.statement = statement;
    }

    public Account clone() {
        try {
            return (Account) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning failed", e);
        }
    }

    public void deposit(Money amount) {
        this.balance = this.balance.add(amount);
        this.statement.addTransaction(new Transaction("Deposit", amount));
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", statement=" + statement +
                '}';
    }
}
```

---

### **Step 2: Usage in Tests (Builder Pattern)**
We use the **Builder** to construct different test profiles.

```java
Account testAccount1 = new Account.AccountBuilder()
    .withAccountNumber("12345")
    .withBalance(Money.amountOf(1000))
    .build();

Account testAccount2 = new Account.AccountBuilder()
    .withAccountNumber("67890")
    .withBalance(Money.amountOf(2000))
    .build();

System.out.println(testAccount1);
System.out.println(testAccount2);
```

---

### **Step 3: Usage in Domain Code (Prototype Pattern)**
Instead of creating new profiles from scratch, we can **clone** a base profile and modify it.

```java
// Prototype: Clone a base account and modify it
Account baseAccount = new Account("12345", Money.amountOf(1000), new Statement());

// Clone the base account and customize for a new user
Account clonedAccount = baseAccount.clone();
clonedAccount.deposit(Money.amountOf(500));

System.out.println("Base Account: " + baseAccount);
System.out.println("Cloned Account: " + clonedAccount);
```

---

### **Why This Works Well**
1. **Builder Pattern**  
   - Makes it easy to create **well-structured test objects** without cluttering test cases.  
   - Allows **optional parameters** without multiple constructors.  
   - Improves **test readability** and **flexibility**.  

2. **Prototype Pattern**  
   - Helps create **similar objects** from an existing one without duplicating setup logic.  
   - Useful when you have **predefined templates** (e.g., user roles, configurations).  
   - **Reduces boilerplate** when making slight modifications to existing objects.  

---

### **Final Output**
```
UserProfile{name='Admin User', age=40, email='admin@example.com', role='Admin'}
UserProfile{name='John Doe', age=25, email='john@example.com', role='User'}
UserProfile{name='Alice Clone', age=30, email='alice@example.com', role='Premium User'}
```

## **Deep Cloning**

Extending this  example by adding **deep cloning** for nested objects. This is useful when your object contains **references to other objects** (e.g., an address inside a user profile) and you want to ensure that cloned objects don’t share the same reference.

---

## **Step 1: Add a Nested Class (`Address`)**
We'll add an **`Address`** class, which will be part of `UserProfile`. This will help us demonstrate **shallow vs. deep cloning**.

```java
public class Address implements Cloneable {
    private String street;
    private String city;
    
    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    // Deep clone implementation
    @Override
    public Address clone() {
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning Address failed", e);
        }
    }

    @Override
    public String toString() {
        return "Address{" + "street='" + street + "', city='" + city + "'}";
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
```

---

## **Step 2: Modify `UserProfile` for Deep Cloning**
Instead of relying on `super.clone()`, we **manually clone the nested objects**.

```java
public class UserProfile implements Cloneable {
    private String name;
    private int age;
    private String email;
    private String role;
    private Address address; // Nested object

    private UserProfile(UserProfileBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.role = builder.role;
        this.address = builder.address;
    }

    public static class UserProfileBuilder {
        private String name;
        private int age;
        private String email;
        private String role;
        private Address address;

        public UserProfileBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserProfileBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public UserProfileBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserProfileBuilder withRole(String role) {
            this.role = role;
            return this;
        }

        public UserProfileBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }

    // Deep cloning: we manually clone the nested Address object
    @Override
    public UserProfile clone() {
        try {
            UserProfile cloned = (UserProfile) super.clone();
            cloned.address = (address != null) ? address.clone() : null; // Deep copy
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning UserProfile failed", e);
        }
    }

    @Override
    public String toString() {
        return "UserProfile{" + "name='" + name + "', age=" + age + ", email='" + email + "', role='" + role + "', address=" + address + '}';
    }
}
```

---

## **Step 3: Demonstrate Deep Cloning**
```java
public class Main {
    public static void main(String[] args) {
        // Create a user with an address
        Address address = new Address("123 Main St", "New York");
        UserProfile originalUser = new UserProfile.UserProfileBuilder()
            .withName("John Doe")
            .withAge(30)
            .withEmail("john@example.com")
            .withRole("User")
            .withAddress(address)
            .build();

        // Clone the user
        UserProfile clonedUser = originalUser.clone();

        // Modify the cloned user's address
        clonedUser.address.setStreet("456 Elm St");

        // Print both objects
        System.out.println("Original User: " + originalUser);
        System.out.println("Cloned User: " + clonedUser);
    }
}
```

---

### **Expected Output**
```
Original User: UserProfile{name='John Doe', age=30, email='john@example.com', role='User', address=Address{street='123 Main St', city='New York'}}
Cloned User: UserProfile{name='John Doe', age=30, email='john@example.com', role='User', address=Address{street='456 Elm St', city='New York'}}
```

### **Why Deep Cloning Is Important?**
Without deep cloning, **both `originalUser` and `clonedUser` would share the same `Address` object**. Changing `clonedUser.address` would also modify `originalUser.address`. By using **`address.clone()`**, we ensure each user has a separate copy of the address.

---

## **Final Takeaways**
- **Builder Pattern:** Creates structured and readable test data.
- **Prototype Pattern:** Enables efficient object cloning.
- **Deep Cloning:** Ensures nested objects don’t share references, preventing unintended modifications.
