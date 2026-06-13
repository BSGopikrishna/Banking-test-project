# Banking Test Project

A demo **QA Automation Framework** for testing core banking workflows using **Playwright + Java** with **Page Object Model (POM)** design.  
This project validates critical financial transactions and ensures reliability, accuracy, and maintainability of automated test suites.

---

## 📌 Project Overview
The **Banking-Test-Project** simulates real-world banking operations and demonstrates how QA engineers can design and execute automation for:
- Fund Transfers
- Bill Payments
- Loan Requests
- Account Management (create, update, delete)
- Transaction History Validation

---

## 🛠️ Tech Stack
- **Language:** Java  
- **Automation Tool:** Playwright  
- **Build Tool:** Maven  
- **Test Runner:** JUnit 5  
- **Design Pattern:** Page Object Model (POM)  

---

## 📂 Project Structure

```text
Banking/
├── pom.xml                                   # Maven dependencies and configuration
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/                        # Page Object classes (POM)
│   │           ├── AccountOverviewPage.java
│   │           ├── BasePage.java
│   │           ├── BillPayPage.java
│   │           ├── LoginPage.java
│   │           ├── OpenAccountPage.java
│   │           ├── RegisterPage.java
│   │           ├── RequestLoanPage.java
│   │           ├── TransactionHistoryPage.java
│   │           ├── TransferFundsPage.java
│   │           └── UpdateProfilePage.java
│   └── test/
│       └── java/
│           ├── base/                         # Base test configuration and setup
│           │   └── BaseTest.java
│           └── bankTest/                     # JUnit 5 test classes
│               ├── AccountCreationTest.java
│               ├── BillPaymentTest.java
│               ├── FundTransferTest.java
│               ├── OpenNewAccountTest.java
│               ├── RequestLoanTest.java
│               ├── TransactionHistoryTest.java
│               └── UpdateContactTest.java
```

## 🚀 How to Run

1. Open the project in your favorite Java IDE (Eclipse, IntelliJ IDEA, etc.).
2. Let Maven download the required dependencies from `pom.xml`.
3. Run the tests using your IDE's built-in JUnit 5 runner, or via the terminal using Maven:
   ```bash
   mvn clean test
   ```

*(Note: Playwright will automatically download the required browser binaries on the first run.)*
