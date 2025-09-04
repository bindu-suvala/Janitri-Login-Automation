# Janitri Login UI Automation (Java + Selenium + TestNG + POM)

This project automates the **login page UI** of the [Janitri Dashboard](https://dev-dash.janitri.in/)  
using **Java, Selenium WebDriver, TestNG, Maven, and the Page Object Model (POM)** design pattern.

---

## 🚀 Tech Stack
- Java 17
- Selenium 4
- TestNG
- Maven
- WebDriverManager
- Page Object Model (POM)

---

## 📂 Project Structure

JanitriLoginAutomation/
├─ pom.xml # Maven dependencies & build config
├─ testng.xml # TestNG suite runner
├─ README.md # Project documentation
└─ src/
└─ test/
└─ java/
├─ base/
│ └─ BaseTest.java # Browser setup/teardown, ChromeOptions
├─ pages/


---

## ⚙️ Setup & Run

### 1. Clone the Repository
```bash
git clone https://github.com/<your-username>/JanitriLoginAutomation.git
cd JanitriLoginAutomation
mvn clean install
mvn clean test

│ └─ LoginPage.java # Page Object Model (locators + actions)
└─ tests/
└─ LoginTest.java # TestNG test cases
