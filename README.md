# testes-saucedemo

Web test automation project using Selenium WebDriver and Java on the [SauceDemo](https://www.saucedemo.com/) website.

## Technologies
- Java 11
- Selenium 4.20
- JUnit 5
- Maven

## How to run
1. Clone the repository
2. Open the project in IntelliJ
3. Run the tests inside `src/test/java/tests`

## Implemented tests

### Login
- Login with valid credentials
- Login with invalid credentials
- Login with valid username and invalid password
- Login with invalid username and valid password
- Login with empty fields
- Login with empty username and valid password

### Inventory
- Add 1 to 6 products to cart and verify counter (parameterized)
- Remove 1 to 6 products from cart and verify cart is empty (parameterized)

### Cart & Checkout
- Complete checkout flow with valid information

## Patterns & Practices
- Page Object Model
- WebDriverWait (no Thread.sleep)
- BeforeEach / AfterEach
- Parameterized Tests
