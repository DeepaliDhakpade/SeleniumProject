package com.deep.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deep.pages.HomePage;
import com.deep.pages.LoginPage;
import com.deep.utils.DriverFactory;

public class LoginTest {
	
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	
	
	@BeforeClass
	public void setUp() {
		driver = DriverFactory.getDriver();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		driver.get("https://the-internet.herokuapp.com/login");
	}
	
	// Test 1 - Valid Login
	@Test(priority = 1)
	public void testValidLogin() throws InterruptedException {
		loginPage.login("tomsmith","SuperSecretPassword!");	
		
		// Wait for URL to change to secure page
	    WebDriverWait wait = new WebDriverWait(driver,
	        Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.urlContains("secure"));

	    Assert.assertTrue(
	        driver.getCurrentUrl().contains("secure"));
	    System.out.println("Valid login passed ✅");
	}
	
	// Test 2 depends on Test 1 passing
	// Test 2 - Verify Home Page
	@Test(priority = 2, dependsOnMethods = {"testValidLogin"})
	public void testHomePage() throws InterruptedException {
		String heading = homePage.getPageHeading();
		System.out.println("Page Heading is : " + heading );
		Assert.assertTrue(homePage.isLogoutButtonVisible());
	}
	
	// Test 3 depends on Test 2
	// Test 3 - Verify Logout
	@Test(priority = 3, dependsOnMethods = {"testHomePage"})
	public void testLogout() {
		homePage.clickLogout();
		// Use URL check instead of flash message	// After logout URL should go back to /login
	    WebDriverWait wait = new WebDriverWait(driver,
	        Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.urlContains("login"));
	   
	 /*   // Wait for URL to change away from secure
	    wait.until(ExpectedConditions.not(
	        ExpectedConditions.urlContains("secure")));
 */
		 // Just verify URL changed after wait
	    String currentUrl = driver.getCurrentUrl();
	    System.out.println("After logout URL: " + currentUrl);
	    
	 // Accept either login page or any non-secure page
	    Assert.assertTrue(
	        currentUrl.contains("login") ||
	        !currentUrl.contains("secure"),
	        "Should redirect to login page after logout! "
	        + "Should have left secure area! URL: " + currentUrl);

	   // Assert.assertTrue(currentUrl.contains("login"),
	   /* // Just verify we left secure page
	    	    Assert.assertFalse(
	    	        currentUrl.contains("secure"),
	        "Should redirect to login page after logout!");
	        */
	    System.out.println("Logout test passed ✅");
	}
		
	// Test 4 - Invalid Login
	@Test(priority = 4)
	public void testInvalidLogin() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/login");
	    loginPage = new LoginPage(driver);
	    loginPage.login("wronguser", "wrongpassword");

	    // URL should stay on login page
	    WebDriverWait wait = new WebDriverWait(driver,
	        Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.urlContains("login"));

	    Assert.assertTrue(
	        driver.getCurrentUrl().contains("login"),
	        "Should stay on login page!");
	    System.out.println("Invalid login test passed ✅");	
	}
	
	
	@AfterClass
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}
