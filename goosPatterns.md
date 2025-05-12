In *Growing Object-Oriented Software, Guided by Tests* (GOOS) by Steve Freeman and Nat Pryce, the authors emphasize building software through test-driven development (TDD), focusing on object collaboration and responsibility. While the book doesn't present design patterns in the traditional sense, it introduces several principles and practices that guide object-oriented design. Below is a summary of these key patterns and concepts, along with illustrative examples in Java:([Medium][1])

---

### 1. **Tell, Don't Ask**

**Concept:** Encourage objects to perform tasks themselves rather than exposing their internal state for others to make decisions.

**Java Example:**

```java
// Instead of this:
if (order.getTotal() > 100) {
    order.applyDiscount();
}

// Prefer this:
order.applyDiscountIfEligible();
```



---

### 2. **Object Peer Stereotypes**

**Concept:** Classify objects based on their roles in the system, such as:

* **Controllers**: Handle user input and system events.
* **Coordinators**: Manage interactions between other objects.
* **Entities**: Represent domain data and logic.([Wikipedia][2])

**Java Example:**

```java
public class OrderController {
    private final OrderService orderService;

    public void processOrder(OrderRequest request) {
        orderService.placeOrder(request);
    }
}
```



---

### 3. **Humane Interface**

**Concept:** Design APIs that are intuitive and reduce the likelihood of misuse.

**Java Example:**

```java
// Instead of requiring multiple parameters:
orderService.placeOrder(customerId, productId, quantity);

// Provide a more intuitive interface:
orderService.placeOrder(new OrderRequest(customerId, productId, quantity));
```



---

### 4. **No And's, Or's, or But's**

**Concept:** Methods should perform a single, well-defined task without conditional logic that alters behavior.

**Java Example:**

```java
// Avoid methods like this:
public void process(boolean isNewCustomer) {
    if (isNewCustomer) {
        // handle new customer
    } else {
        // handle existing customer
    }
}

// Prefer separate methods:
public void processNewCustomer() { /* ... */ }
public void processExistingCustomer() { /* ... */ }
```



---

### 5. **Composite Simpler Than the Sum of Its Parts**

**Concept:** Compose objects to achieve complex behavior, ensuring that the composition is simpler and more understandable than managing individual parts separately.

**Java Example:**

```java
public class OrderProcessor {
    private final PaymentService paymentService;
    private final ShippingService shippingService;

    public void processOrder(Order order) {
        paymentService.processPayment(order);
        shippingService.shipOrder(order);
    }
}
```



---

### 6. **Ports and Adapters (Hexagonal Architecture)**

**Concept:** Separate the core logic of the application from external concerns like databases or user interfaces by using interfaces (ports) and implementations (adapters).

**Java Example:**

```java
// Port
public interface PaymentGateway {
    void processPayment(Payment payment);
}

// Adapter
public class StripePaymentGateway implements PaymentGateway {
    public void processPayment(Payment payment) {
        // Integration with Stripe API
    }
}
```



---

### 7. **Mock Roles, Not Objects**

**Concept:** When writing tests, mock the roles or interfaces that objects play, not the concrete classes themselves.

**Java Example with Mockito:**

```java
@Test
public void shouldProcessOrder() {
    PaymentGateway paymentGateway = mock(PaymentGateway.class);
    OrderProcessor processor = new OrderProcessor(paymentGateway);

    processor.processOrder(new Order());

    verify(paymentGateway).processPayment(any(Payment.class));
}
```



---

### 8. **Interface Discovery**

**Concept:** Define interfaces during testing to guide the design of collaborators, allowing for better decoupling and flexibility.

**Java Example:**

```java
// During testing, define the interface:
public interface NotificationService {
    void sendNotification(String message);
}

// Later, implement the interface:
public class EmailNotificationService implements NotificationService {
    public void sendNotification(String message) {
        // Send email
    }
}
```



---

### 9. **Test-Driven Development (TDD)**

**Concept:** Write tests before implementing functionality to drive design and ensure testability.

**Java Example:**

```java
@Test
public void shouldCalculateTotalPrice() {
    Order order = new Order();
    order.addItem(new Item("Book", 10.0));
    order.addItem(new Item("Pen", 2.0));

    assertEquals(12.0, order.getTotalPrice(), 0.01);
}
```



---

These principles and practices from GOOS guide developers in creating well-structured, maintainable, and testable object-oriented software. By focusing on object responsibilities, interactions, and designing through tests, developers can build systems that are both robust and adaptable.([Google Groups][3])

For more detailed examples and discussions, you can refer to the official [GOOS code examples](http://www.growing-object-oriented-software.com/code.html).

[1]: https://medium.com/wix-engineering/growing-object-oriented-software-guided-by-tests-tldr-a1d59cadbeae?utm_source=chatgpt.com "Growing Object Oriented Software Guided by Tests — TLDR - Medium"
[2]: https://en.wikipedia.org/wiki/GRASP_%28object-oriented_design%29?utm_source=chatgpt.com "GRASP (object-oriented design)"
[3]: https://groups.google.com/g/growing-object-oriented-software/c/3NSrEggO8T8?utm_source=chatgpt.com "Growing Object-Oriented Software, Guided by Tests Without Mocks"
