# 🧪 Janitri Login UI Automation (Java + Selenium + TestNG + POM)

This project automates the **login page UI** of the [Janitri Dashboard](https://dev-dash.janitri.in/)  
using **Java, Selenium WebDriver, TestNG, Maven, and the Page Object Model (POM)** design pattern.

---

## 🚀 Tech Stack

- ✅ Java 17  
- ✅ Selenium WebDriver 4  
- ✅ TestNG  
- ✅ Maven (Build & Dependency Management)  
- ✅ WebDriverManager (for ChromeDriver)  
- ✅ Page Object Model (POM) Design Pattern  

---

## 📁 Project Structure

JanitriLoginAutomation/
│
├── pom.xml # Maven dependencies & configuration
├── testng.xml # TestNG suite configuration
├── README.md # Project documentation
├── TestCases_JanitriLogin.xlsx # Manual test cases
│
└── src/
└── test/
└── java/
├── base/
│ └── BaseTest.java # WebDriver setup/teardown logic
│
├── pages/
│ └── LoginPage.java # Page Object Model for Login Page
│
└── tests/
└── LoginTest.java # TestNG test cases


---
🧪 Test Coverage

Automated Test Scenarios:

Test Case ID	Description
TC01	Verify login button is disabled when fields are empty
TC02	Toggle password visibility (mask/unmask)
TC03	Show error message on invalid credentials
TC04	Validate presence of login page UI elements
TC05	Boundary test with long email input (manual)

👉 Check the full list of test cases in the file: TestCases_JanitriLogin.xlsx


🧑‍💻 Author

Task 2 - Janitri QA Assignment
Created with ❤️ using pure Java + Selenium + TestNG (No Cucumber, BDD, etc.)

✅ Final Deliverables

 Automation framework with Java, Selenium, TestNG, POM

 Manual test cases in Excel format

 Uploaded to GitHub

 Shared public GitHub link
