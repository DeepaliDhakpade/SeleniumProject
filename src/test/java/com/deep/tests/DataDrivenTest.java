package com.deep.tests;

	import com.deep.pages.LoginPage;
	import com.deep.utils.DriverFactory;
	import com.deep.utils.ExcelUtils;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
	import org.testng.annotations.*;

	public class DataDrivenTest {

	    WebDriver driver;
	    LoginPage loginPage;

	    // Path to excel file
	   /* // OLD — may not work in Jenkins
	    String excelPath = System.getProperty("user.dir")
	        + "/src/test/resources/LoginData.xlsx";
	 */
	    // NEW — works everywhere ✅
	    String excelPath = System.getProperty("user.dir")
	        + File.separator + "src"
	        + File.separator + "test"
	        + File.separator + "resources"
	        + File.separator + "LoginData.xlsx";

	    @BeforeClass
	    public void openBrowser() {
	        driver = DriverFactory.getDriver();
	    }

	    @BeforeMethod
	    public void navigateToLogin() {
	        driver.get("https://the-internet.herokuapp.com/login");
	        loginPage = new LoginPage(driver);
	    }

	    // ── DataProvider reads from Excel ──
	    @DataProvider(name = "loginData")
	    public Object[][] getLoginData() throws Exception {
	        ExcelUtils excel = new ExcelUtils(excelPath, "LoginSheet");
	        String[][] data = excel.getAllData();
	        excel.closeWorkbook();
	        return data;
	    }

	    // ── Test runs for each row in Excel ──
	    @Test(dataProvider = "loginData")
	    public void testLoginWithExcelData(
	            String username,
	            String password,
	            String expectedResult) throws InterruptedException {

	        System.out.println("─────────────────────────────");
	        System.out.println("Username : " + username);
	        System.out.println("Password : " + password);
	        System.out.println("Testing: " + username + " / " + expectedResult);

	        loginPage.login(username, password);

	        try {
	            // Wait for ANY result message
	            WebDriverWait wait = new WebDriverWait(driver,
	                Duration.ofSeconds(15));

	            // Try flash message first
	            WebElement message = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                    By.cssSelector("#flash, .flash, .alert")));

	            String messageText = message.getText();
	            System.out.println("Message: " + messageText);

	            if (expectedResult.equalsIgnoreCase("success")) {
	                Assert.assertTrue(
	                    messageText.contains("You logged into"),
	                    "Expected success! Actual: " + messageText);
	            } else {
	                Assert.assertTrue(
	                    messageText.contains("invalid") ||
	                    messageText.contains("password") ||
	                    messageText.contains("username"),
	                    "Expected failure! Actual: " + messageText);
	            }

	        } catch (Exception e) {
	            // If no flash message — check URL instead
	            String currentUrl = driver.getCurrentUrl();
	            System.out.println("URL after login: " + currentUrl);

	            if (expectedResult.equalsIgnoreCase("success")) {
	                Assert.assertTrue(
	                    currentUrl.contains("secure"),
	                    "Expected to reach secure page!");
	            } else {
	                Assert.assertTrue(
	                    currentUrl.contains("login"),
	                    "Expected to stay on login page!");
	            }
	        }
	    }

	    @AfterClass
	    public void closeBrowser() {
	        DriverFactory.quitDriver();
	    }
	}

	// Without Excel — Using @DataProvider directly
	/*// Inline data — no Excel needed
@DataProvider(name = "loginDataInline")
public Object[][] getLoginDataInline() {
    return new Object[][] {
        {"tomsmith", "SuperSecretPassword!", "success"},
        {"wronguser", "wrongpass",           "failure"},
        {"tomsmith",  "wrongpass",           "failure"}
    };
}

@Test(dataProvider = "loginDataInline")
public void testLoginInline(
        String username,
        String password,
        String expectedResult) {

    System.out.println("Testing: " + username + " / " + password);
    loginPage.login(username, password);
    String message = loginPage.getFlashMessage();

    if (expectedResult.equals("success")) {
        Assert.assertTrue(message.contains("You logged into"));
    } else {
        Assert.assertTrue(message.contains("invalid")
            || message.contains("password"));
    }
    System.out.println("Result: PASS ✅");
}*/
	