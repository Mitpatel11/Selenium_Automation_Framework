# Selenium Automation Framework

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-brightgreen?style=for-the-badge)
![TestNG](https://img.shields.io/badge/TestNG-Framework-red?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-blue?style=for-the-badge)
![ExtentReports](https://img.shields.io/badge/ExtentReports-Test%20Reporting-purple?style=for-the-badge)

## Overview

This repository contains a Selenium WebDriver automation framework built with Java, TestNG and Maven. The framework automates an end-to-end e-commerce purchase flow on the Rahul Shetty Academy client application and demonstrates how a basic standalone Selenium script can be refactored into a maintainable, reusable and scalable test automation framework.

The main purpose of this project was to understand framework design from the ground up. It covers Page Object Model, Page Factory, data-driven testing, TestNG suite execution, Maven profile-based execution, cross-browser setup, ExtentReports integration, screenshots on failure and retry handling.

---

## Tech Stack

| Area | Tools / Libraries |
|---|---|
| Programming Language | Java 17 |
| Automation Tool | Selenium WebDriver |
| Test Framework | TestNG |
| Build Tool | Maven |
| Driver Management | WebDriverManager |
| Reporting | ExtentReports |
| Test Data Handling | JSON, Jackson Databind |
| Browsers Supported | Chrome, Firefox, Edge |

---

## What I Implemented

- Built a Selenium Java framework using the Page Object Model design pattern.
- Used Page Factory to initialise page elements and reduce repeated locator code.
- Created reusable page classes for login, product catalogue, cart, checkout, confirmation and order history pages.
- Added a shared `AbstractComponent` class for common actions such as waiting for elements, navigating to cart and navigating to orders.
- Created a reusable `BaseTest` class to manage browser setup, application launch and test teardown.
- Added browser configuration through `GlobalData.properties` and Maven command-line system properties.
- Supported Chrome, Firefox, Edge and Chrome headless execution.
- Implemented TestNG tests for purchase flow, order history validation, login error validation and product error validation.
- Added data-driven testing using JSON test data and Jackson Databind.
- Added TestNG groups and XML suites for regression, purchase and error validation executions.
- Configured Maven profiles to run selected TestNG suites from the command line.
- Integrated ExtentReports to generate readable HTML reports.
- Implemented screenshot capture on test failure and attached screenshots to the Extent report.
- Added a TestNG listener to manage reporting events for pass and fail scenarios.
- Added retry logic using TestNG `IRetryAnalyzer` for unstable or failed tests.
- Kept the original standalone Selenium test as a reference to show how the framework evolved from basic scripting to structured automation.

---

## Test Scenarios Covered

| Test Scenario | Description |
|---|---|
| Submit Order Test | Logs in, selects a product, adds it to the cart, checks out and verifies the order confirmation message. |
| Order History Test | Verifies that the ordered product appears in the order history page. |
| Login Error Validation | Validates the error message shown for incorrect login credentials. |
| Product Error Validation | Verifies negative product/cart validation when an incorrect product name is checked. |

---

## Project Structure

```text
Selenium_Automation_Framework
│
├── TestSuites
│   ├── Error Validation Test.xml
│   ├── Purchases.xml
│   └── testng.xml
│
├── reports
│   └── index.html
│
├── src
│   ├── main
│   │   └── java
│   │       └── MitPate
│   │           ├── AbstractComponents
│   │           │   └── AbstractComponent.java
│   │           ├── pageobjects
│   │           │   ├── CartPage.java
│   │           │   ├── CheckoutPage.java
│   │           │   ├── ConfirmationPage.java
│   │           │   ├── LandingPage.java
│   │           │   ├── OrderPage.java
│   │           │   └── ProductCatelogue.java
│   │           └── resources
│   │               ├── ExtentReportedNG.java
│   │               └── GlobalData.properties
│   │
│   └── test
│       └── java
│           └── MitPatel
│               ├── TestComponents
│               │   ├── BaseTest.java
│               │   ├── Listners.java
│               │   └── Retry.java
│               ├── Tests
│               │   ├── ErrorValidation.java
│               │   ├── PageFactoryActionsTest.java
│               │   └── StandAloneTest.java
│               └── data
│                   ├── DataReader.java
│                   └── PurchaseOrder.json
│
└── pom.xml
```

---

## Framework Design

### Page Object Model

Each page of the application is represented by a separate Java class. This keeps locators and page-specific actions away from the test classes and makes the tests easier to read and maintain.

Examples:

- `LandingPage.java` handles login and login error messages.
- `ProductCatelogue.java` handles product search and add-to-cart actions.
- `CartPage.java` verifies products in the cart and moves to checkout.
- `CheckoutPage.java` handles country selection and order submission.
- `ConfirmationPage.java` validates successful order placement.
- `OrderPage.java` validates product availability in order history.

### Reusable Components

Common actions are placed inside `AbstractComponent.java`. This avoids repeated code across page classes and keeps shared behaviour in one place.

Reusable actions include:

- waiting for elements to appear,
- waiting for elements to disappear,
- navigating to the cart page,
- navigating to the orders page.

### Test Setup and Teardown

`BaseTest.java` controls browser initialisation, test data reading, screenshot capture and application launch. This allows the test classes to focus only on test logic.

---

## How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/Mitpatel11/Selenium_Automation_Framework.git
cd Selenium_Automation_Framework
```

### 2. Run the default TestNG suite

```bash
mvn test
```

### 3. Run specific Maven profiles

```bash
mvn test -PRegression
mvn test -PPurchase
mvn test -PErrorValidation
```

### 4. Run tests on a specific browser

```bash
mvn test -Dbrowser=Chrome
mvn test -Dbrowser=Firefox
mvn test -Dbrowser=Edge
mvn test -Dbrowser=Chromeheadless
```

By default, the browser is configured from `GlobalData.properties`.

---

## Reports and Screenshots

After test execution, the Extent report is generated inside the `reports` folder.

```text
reports/index.html
```

If a test fails, a screenshot is captured and attached to the report. This makes debugging easier because the failure can be reviewed visually along with the test error.

---

## What I Learned

While building this framework, I learned how to move from a simple Selenium script to a cleaner framework structure. The project helped me understand why Page Object Model is important, how reusable components reduce duplicated code and how TestNG can organise tests through groups, XML suites and dependencies.

I also learned how to manage test data using JSON, execute tests through Maven, generate reports with ExtentReports and capture screenshots when failures occur. Most importantly, this project improved my understanding of how automation frameworks are structured in real QA projects rather than just writing individual Selenium scripts.

---

## Challenges Faced

One of the main challenges was converting a long standalone Selenium test into a reusable Page Object Model framework. It required deciding which logic should stay inside the test class, which logic should move into page classes and which actions should become shared reusable components.

Another challenge was handling dynamic web elements such as toast messages, loading spinners and search results. To make the tests more reliable, explicit waits were added for element visibility and invisibility instead of depending only on fixed timing.

Integrating ExtentReports with TestNG listeners was also an important learning point. Capturing screenshots only when a test failed and attaching them to the correct report entry required understanding listener methods, driver access and report lifecycle handling.

A further challenge was separating test data and configuration from the test logic. JSON test data and properties-based browser configuration improved the framework, but a future improvement will be to move sensitive data into environment variables or CI secrets instead of storing it directly in the repository.

---

## Security Note

Real usernames, passwords and sensitive test data should not be committed to a public repository. A better approach is to use environment variables, a local ignored configuration file or CI/CD secret management.

---

## Future Implementations

In future, this framework will be extended with CI/CD execution using GitHub Actions or Jenkins, stronger secret management through environment variables and improved parallel execution support. Additional test coverage can also be added for more negative scenarios, reusable utilities and cross-browser regression runs.

---

## Author

**Mit Patel**

This project was created as part of my Selenium WebDriver learning journey and framework-building practice.
