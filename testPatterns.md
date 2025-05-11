Common unit & behavior test patterns with Java examples, referencing popular Java test frameworks (JUnit 5, Mockito, AssertJ, and TestNG):

---

### **1. Arrange-Act-Assert (AAA)**
**Pattern**: Structure tests into three clear sections.  
**Frameworks**: JUnit, TestNG  
```java
@Test
void shouldAddTwoNumbers() {
    // Arrange
    Calculator calculator = new Calculator();
    
    // Act
    int result = calculator.add(2, 3);
    
    // Assert
    assertEquals(5, result); // JUnit 5
}
```

---

### **2. Parameterized Tests**
**Pattern**: Run the same test with different inputs.  
**Frameworks**: JUnit 5 (`@ParameterizedTest`), TestNG (`@DataProvider`)  
```java
@ParameterizedTest
@ValueSource(ints = {1, 2, 3})
void isPositive(int number) {
    assertTrue(number > 0); // JUnit 5
}
```

---

### **3. Mocking Dependencies**
**Pattern**: Replace real dependencies with mocks.  
**Frameworks**: Mockito  
```java
@Test
void shouldProcessOrder() {
    // Arrange
    PaymentService mockPayment = Mockito.mock(PaymentService.class);
    OrderService orderService = new OrderService(mockPayment);
    
    // Act
    orderService.processOrder(100.0);
    
    // Assert
    verify(mockPayment).charge(100.0); // Mockito
}
```

---

### **4. Behavior Verification (BDD Style)**
**Pattern**: Test behavior rather than implementation.  
**Frameworks**: Mockito (BDDMockito), AssertJ  
```java
@Test
void shouldSendEmailOnRegistration() {
    // Given (BDD setup)
    EmailService mockEmail = Mockito.mock(EmailService.class);
    UserService userService = new UserService(mockEmail);
    
    // When
    userService.register("test@example.com");
    
    // Then (AssertJ fluent assertions)
    then(mockEmail).should().sendEmail("test@example.com"); // BDDMockito
}
```

---

### **5. Test Fixtures (Setup/Teardown)**
**Pattern**: Reuse setup/cleanup code.  
**Frameworks**: JUnit 5 (`@BeforeEach`, `@AfterEach`), TestNG (`@BeforeMethod`)  
```java
class DatabaseTest {
    private Database db;
    
    @BeforeEach
    void setUp() {
        db = new Database();
        db.connect();
    }
    
    @AfterEach
    void tearDown() {
        db.disconnect();
    }
    
    @Test
    void shouldInsertData() {
        assertDoesNotThrow(() -> db.insert("data")); // JUnit 5
    }
}
```

---

### **6. Matchers & Fluent Assertions**
**Pattern**: Improve readability with expressive assertions.  
**Frameworks**: AssertJ, Hamcrest  
```java
@Test
void shouldContainItems() {
    List<String> items = List.of("apple", "banana");
    
    // AssertJ example
    assertThat(items)
        .hasSize(2)
        .contains("apple")
        .doesNotContain("cherry");
}
```

---

### **7. Test Doubles (Stub/Fake/Spy)**
**Pattern**: Replace dependencies with lightweight test doubles.  
**Frameworks**: Mockito  
```java
@Test
void shouldUseStubForDiscount() {
    // Stub example
    DiscountService stubDiscount = Mockito.mock(DiscountService.class);
    when(stubDiscount.getDiscount()).thenReturn(10); // Stub behavior
    
    OrderService orderService = new OrderService(stubDiscount);
    assertEquals(90, orderService.applyDiscount(100)); // JUnit 5
}
```

---

### **8. Exception Testing**
**Pattern**: Verify exceptions are thrown.  
**Frameworks**: JUnit 5 (`assertThrows`), TestNG (`expectedExceptions`)  
```java
@Test
void shouldThrowOnInvalidInput() {
    Validator validator = new Validator();
    
    // JUnit 5
    assertThrows(IllegalArgumentException.class, 
        () -> validator.validate(null));
}
```

---

### **9. Parallel Test Execution**
**Pattern**: Run tests in parallel.  
**Frameworks**: TestNG (`parallel="methods"`), JUnit 5 (via configuration)  
```xml
<!-- TestNG XML config -->
<suite name="Suite" parallel="methods" thread-count="3">
```

---

### **10. Tagging & Filtering Tests**
**Pattern**: Group tests by categories.  
**Frameworks**: JUnit 5 (`@Tag`), TestNG (`@Groups`)  
```java
@Tag("integration") // JUnit 5
@Test
void databaseIntegrationTest() { ... }
```

---

### **11. Contract Testing (Consumer-Driven)**
**Pattern**: Verify API contracts.  
**Frameworks**: Pact (for HTTP services)  
```java
// Example using Pact (simplified)
@PactTest
void shouldHonorUserContract(PactBuilder builder) {
    builder.given("user exists")
           .uponReceiving("get user request")
           .willRespondWith(200);
}
```

---

### **12. Property-Based Testing**
**Pattern**: Generate random inputs to test invariants.  
**Frameworks**: JQuickCheck, jqwik (for JUnit 5)  
```java
@Property // jqwik
boolean absoluteValueAlwaysPositive(@ForAll int number) {
    return Math.abs(number) >= 0;
}
```