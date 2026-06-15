# 🚀 Selenium Java Automation Framework

![Java](https://img.shields.io/badge/Java-21-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.32-green)
![TestNG](https://img.shields.io/badge/TestNG-7.10-blue)
![Maven](https://img.shields.io/badge/Maven-3.9-red)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-yellow)

A robust, scalable, and maintainable **Selenium Java Automation Framework** built from scratch using industry best practices.

---

## 📋 Table of Contents

- [About the Framework](#about-the-framework)
- [Tech Stack](#tech-stack)
- [Framework Architecture](#framework-architecture)
- [Project Structure](#project-structure)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [How to Run](#how-to-run)
- [Test Reports](#test-reports)
- [CI/CD Pipeline](#cicd-pipeline)
- [Author](#author)

---

## 🎯 About the Framework

This framework automates web application testing using **Page Object Model (POM)** design pattern. It supports data-driven testing, parallel execution, detailed HTML reporting with screenshots, and is integrated with Jenkins for continuous testing.

**Application Under Test:** Cloud Manager SaaS Platform (Gym, Spa, Salon modules)

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 21 | Programming language |
| Selenium WebDriver | 4.32 | Browser automation |
| TestNG | 7.10 | Test execution framework |
| Maven | 3.9 | Build & dependency management |
| Apache POI | 5.2.5 | Excel data-driven testing |
| Extent Reports | 5.1.1 | HTML test reporting |
| Jenkins | 2.555.2 LTS | CI/CD automation |
| Git / GitHub | - | Version control |
| Chrome | 149 | Browser |

---

## 🏗️ Framework Architecture

```
┌─────────────────────────────────────────────────────┐
│                   TEST LAYER                         │
│     LoginTest | DataDrivenTest | ExtentReportTest    │
└────────────────────┬────────────────────────────────┘
                     │ uses
┌────────────────────▼────────────────────────────────┐
│                  PAGE LAYER (POM)                    │
│          LoginPage | HomePage                        │
└────────────────────┬────────────────────────────────┘
                     │ uses
┌────────────────────▼────────────────────────────────┐
│                 UTILITY LAYER                        │
│   DriverFactory | ExcelUtils | ExtentReportManager  │
│   TestListener                                       │
└─────────────────────────────────────────────────────┘
```

---

## 📁 Project Structure

```
SeleniumProject/
│
├── src/
│   ├── main/java/com/deep/
│   │   ├── pages/
│   │   │   ├── LoginPage.java          ← Login page locators & actions
│   │   │   └── HomePage.java           ← Home page locators & actions
│   │   └── utils/
│   │       ├── DriverFactory.java      ← WebDriver setup (ThreadLocal)
│   │       ├── ExcelUtils.java         ← Excel read utility (Apache POI)
│   │       └── ExtentReportManager.java ← Extent Report configuration
│   │
│   └── test/java/com/deep/
│       ├── tests/
│       │   ├── LoginTest.java          ← Login test scenarios
│       │   ├── DataDrivenTest.java     ← Excel data-driven tests
│       │   ├── ExtentReportTest.java   ← Report integration tests
│       │   └── WebelementTest.java     ← WebElement interaction tests
│       ├── utils/
│       │   └── TestListener.java       ← ITestListener for screenshots
│       └── resources/
│           └── LoginData.xlsx          ← Test data
│
├── reports/                            ← Extent HTML reports (generated)
├── screenshots/                        ← Failure screenshots (generated)
├── testng.xml                          ← Test suite configuration
└── pom.xml                             ← Maven dependencies
```

---

## ✨ Features

✅ **Page Object Model (POM)** — Clean separation of locators and test logic

✅ **Data Driven Testing** — Excel integration using Apache POI

✅ **Extent Reports** — Beautiful HTML reports with charts and logs

✅ **Auto Screenshots** — Captures screenshot on every test failure

✅ **Parallel Execution** — ThreadLocal WebDriver for thread-safe parallel runs

✅ **Explicit Waits** — WebDriverWait for dynamic element handling

✅ **TestNG Listeners** — ITestListener for automatic reporting hooks

✅ **CI/CD Integration** — Jenkins pipeline for automated test execution

✅ **Cross-browser Ready** — ChromeOptions configured for local and headless

---

## ⚙️ Prerequisites

- Java JDK 21+
- Maven 3.9+
- Chrome Browser (latest)
- Eclipse IDE / IntelliJ IDEA
- Git

---

## ▶️ How to Run

### Run via Maven (Command Line):
```bash
# Clone the repository
git clone https://github.com/DeepaliDhakpade/SeleniumProject.git

# Navigate to project
cd SeleniumProject

# Run all tests
mvn clean test

# Run specific group
mvn clean test -Dgroups=smoke
```

### Run via Eclipse:
```
Right click testng.xml → Run As → TestNG Suite
```

### Run via Jenkins:
```
Jenkins Job → Build Now
Goals: clean test
```

---

## 📊 Test Reports

After execution, open the HTML report:
```
reports/ExtentReport.html
```

**Report includes:**
- 📈 Dashboard with Pass/Fail/Skip pie chart
- 📝 Step-by-step test logs
- 📸 Screenshots attached to failed tests
- ⏱️ Execution time per test
- 💻 System information (OS, Browser, Tester)

---

## 🔄 CI/CD Pipeline

```
Code Push to GitHub
        ↓
Jenkins detects change (Poll SCM)
        ↓
Maven build triggered (mvn clean test)
        ↓
TestNG executes test suite
        ↓
Extent Report generated
        ↓
Email notification sent
        ↓
✅ Pass or ❌ Fail result
```

**Jenkins Configuration:**
- Build Tool: Maven
- Goals: `clean test`
- Schedule: Daily at 8 PM
- Reporting: Extent HTML Report

---

## 📌 Test Scenarios Covered

| Module | Test Cases |
|---|---|
| Login — Valid credentials | ✅ |
| Login — Invalid credentials | ✅ |
| Login — Data driven (Excel) | ✅ |
| Homepage — Logout | ✅ |
| Dropdowns | ✅ |
| Checkboxes | ✅ |
| Alerts (JS Alert, Confirm, Prompt) | ✅ |
| Extent Report integration | ✅ |

---

## 👩‍💻 Author

**Deepali Dhakpade**
Senior QA Engineer | 9 Years Experience

- 🔗 GitHub: [DeepaliDhakpade](https://github.com/DeepaliDhakpade)
- 💼 LinkedIn: [Add your LinkedIn URL]
- 🏢 Domain: SaaS | Fintech | Cloud Applications
- 🛠️ Skills: Selenium | Java | TestNG | API Testing | Agile | Jira

---

## 📜 License

This project is for portfolio and learning purposes.

---

⭐ If you find this framework useful, please give it a star!
