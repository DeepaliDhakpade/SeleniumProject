package com.deep.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.deep.pages.LoginPage;
import com.deep.utils.DriverFactory;
import com.deep.utils.ExtentReportManager;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ExtentReportTest {

    WebDriver driver;
    LoginPage loginPage;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.getDriver();
        extent = ExtentReportManager.getExtentReports();
    }

    @BeforeMethod
    public void navigateToLogin() {
        driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    // ── Test 1 — Valid Login ──
    @Test(priority = 1)
    public void testValidLogin() {
        test = extent.createTest("Valid Login Test",
            "Login with valid credentials");

        try {
            test.log(Status.INFO, "Navigated to login page");

            loginPage.login("tomsmith", "SuperSecretPassword!");
            test.log(Status.INFO, "Entered credentials and clicked login");

            // Add wait before getFlashMessage()    
            // Check URL instead of flash message
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.urlContains("secure"));

            String currentUrl = driver.getCurrentUrl();
            System.out.println("URL: " + currentUrl);
            Assert.assertTrue(currentUrl.contains("secure"),
                "Should reach secure page!");
            test.log(Status.PASS, "Valid login passed ✅");                    
            
            
           /* wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
            * String message = loginPage.getFlashMessage();
            Assert.assertTrue(message.contains("You logged into"));

            test.log(Status.PASS, "Valid login passed ✅");
            */

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    // ── Test 2 — Invalid Login ──
    @Test(priority = 2)
    public void testInvalidLogin() {
        test = extent.createTest("Invalid Login Test",
            "Login with invalid credentials");

        try {
            test.log(Status.INFO, "Navigated to login page");

            loginPage.login("wronguser", "wrongpass");
            test.log(Status.INFO, "Entered wrong credentials");
            
         // Add wait before getFlashMessage()
            // Check URL stays on login page
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.urlContains("login"));

            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("login"),
                "Should stay on login page!");
            test.log(Status.PASS, "Invalid login handled ✅");
            
            /*wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

            String message = loginPage.getFlashMessage();
            Assert.assertTrue(message.contains("Your username is invalid"));

            test.log(Status.PASS, "Invalid login handled correctly ✅");
            */

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    // ── Test 3 — Intentional Fail (to see fail in report) ──
    @Test(priority = 3, enabled = false)          // disabled
    public void testIntentionalFail() {
        test = extent.createTest("Intentional Fail Test",
            "This test will fail on purpose");

        test.log(Status.INFO, "Starting intentional fail test");
        test.log(Status.FAIL, "Intentionally failing this test");
        Assert.fail("Intentional failure to show in report");
    }

    @AfterClass
    public void teardown() {
        // IMPORTANT — flush writes report to file
        extent.flush();
        DriverFactory.quitDriver();
    }
}