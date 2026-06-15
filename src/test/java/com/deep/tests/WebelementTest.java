package com.deep.tests;

import java.sql.Driver;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebelementTest {
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	// ─────────────────────────────────────
	// Test 1 - Type and click (Login)	
	// ─────────────────────────────────────
	@Test (priority = 1)
	public void testLoginForm() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/login");
		
		//Find user name field and type
		WebElement userName = driver.findElement(By.name("username"));
		userName.clear();
		userName.sendKeys("tomsmith");
		
		//Find password field and type
		WebElement password = driver.findElement(By.name("password"));
		password.clear();
		password.sendKeys("SuperSecretPassword!");
				
		//Click Login Button
		WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
		loginButton.click();
		
		//Verify login success message
		WebElement message = driver.findElement(By.id("flash"));
        System.out.println("Login message: " + message.getText());	
        Thread.sleep(2000);				
	}	
	
	@AfterClass
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
	
	// ─────────────────────────────────────
	// Test 2 - Dropdown (Select Class)
	// ─────────────────────────────────────
	@Test(priority = 2)
	public void testDropdown() {
		 driver.get("https://the-internet.herokuapp.com/dropdown");

		 // Find dropdown element
		 WebElement dropdownElement = driver.findElement(By.id("dropdown"));
		 
		 // Create Select Object
		 Select dropdown = new Select(dropdownElement);
		 
		 // 3 ways to Select
		 
		 // 1. Select by visible text
		    dropdown.selectByVisibleText("Option 1");
		    System.out.println("Selected: " + dropdown.getFirstSelectedOption().getText());

		    // 2. Select by value
		    dropdown.selectByValue("2");
		    System.out.println("Selected: " + dropdown.getFirstSelectedOption().getText());

		    // 3. Select by index
		    dropdown.selectByIndex(1);
		    System.out.println("Selected: " + dropdown.getFirstSelectedOption().getText());
	}

	// ─────────────────────────────────────
	// TEST 3 — Checkbox
	// ─────────────────────────────────────
	@Test(priority = 3)
	public void testCheckbox() {
	    driver.get("https://the-internet.herokuapp.com/checkboxes");

	    // Get all checkboxes on page
	    java.util.List<WebElement> checkboxes = driver.findElements(
	        By.xpath("//input[@type='checkbox']"));

	    System.out.println("Total checkboxes: " + checkboxes.size());

	    // Checkbox 1
	    WebElement checkbox1 = checkboxes.get(0);
	    if (!checkbox1.isSelected()) {
	        checkbox1.click();  // check it
	        System.out.println("Checkbox 1: Checked");
	    } else {
	        System.out.println("Checkbox 1: Already checked");
	    }

	    // Checkbox 2
	    WebElement checkbox2 = checkboxes.get(1);
	    System.out.println("Checkbox 2 selected: " + checkbox2.isSelected());

	    // Uncheck checkbox 2
	    if (checkbox2.isSelected()) {
	        checkbox2.click();  // uncheck it
	        System.out.println("Checkbox 2: Unchecked");
	    }
	}
	    
	 // ─────────────────────────────────────
	 // TEST 4 — Alerts
	 // ─────────────────────────────────────
	// Add groups = "headless-skip"
	@Test(priority = 4, groups = {"headless-skip"})
	public void testAlerts() {
	    driver.get(
	        "https://the-internet.herokuapp.com/javascript_alerts");

	    WebDriverWait wait = new WebDriverWait(driver,
	        Duration.ofSeconds(10));

	    // Simple Alert
	    driver.findElement(
	        By.xpath("//button[text()='Click for JS Alert']"))
	        .click();
	    wait.until(ExpectedConditions.alertIsPresent());
	    driver.switchTo().alert().accept();
	    System.out.println("Simple alert accepted ✅");

	    // Confirm Alert
	    driver.findElement(
	        By.xpath("//button[text()='Click for JS Confirm']"))
	        .click();
	    wait.until(ExpectedConditions.alertIsPresent());
	    driver.switchTo().alert().dismiss();
	    System.out.println("Confirm alert dismissed ✅");

	    // Prompt Alert
	    driver.findElement(
	        By.xpath("//button[text()='Click for JS Prompt']"))
	        .click();
	    wait.until(ExpectedConditions.alertIsPresent());
	    driver.switchTo().alert().sendKeys("Deepali");
	    driver.switchTo().alert().accept();
	    System.out.println("Prompt alert filled ✅");
	}
}
