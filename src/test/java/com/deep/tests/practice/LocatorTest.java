package com.deep.tests.practice;

import java.lang.management.ThreadInfo;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocatorTest {
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
	}
	
	@Test(priority = 1)
	public void testByName() {
		//Find search box by name
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium Webdriver");
		System.out.println("Found element by name: "+searchBox.getTagName());
	}
	
	@Test(priority = 2)
	public void testByXpath() {
		//Find search box by XPath
		WebElement searchBox = driver.findElement(By.xpath("//textarea[@name='q']"));
		searchBox.clear();
		searchBox.sendKeys("TestNG Framework");
		System.out.println("Found element by XPath: "+searchBox.getTagName());
	}
	
	@Test(priority = 3)
	public void testByCSSSelector() {
		//Find search box by CSS Selector
		WebElement searchBox = driver.findElement(By.cssSelector("textarea[name='q']"));
		searchBox.clear();
		searchBox.sendKeys("Page Object Model");
		System.out.println("Found element by CSS Selector: "+searchBox.getTagName());
	}
	
	// -----3 Methods to click on Google Search autocomplete suggestion 3rd link-----------
	@Test (priority = 4)
	public void clickThirdAutocompleteSuggestion() throws InterruptedException {
		
		// Type in Search box
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.clear();
		searchBox.sendKeys("Selenium");
		//searchBox.sendKeys(Keys.ENTER);	// hit enter
		
		// Wait for suggestions to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//ul[@role='listbox']//li"))); 	//By.cssSelector("div#search a h3")));
		
		//Get all suggestions
		List<WebElement> suggestions = driver.findElements(
				By.xpath("//ul[@role='listbox']//li")); 	//By.cssSelector("div#search a h3"));	
		
		System.out.println("Total suggestions found: "+suggestions.size());
		
		for (int i = 0; i < suggestions.size(); i++) {
			System.out.println(i+1 +" : "+ suggestions.get(i).getText());
		}
		
		// Click third 
			System.out.println("Clicking : "+suggestions.get(2).getText());
			
			suggestions.get(2).click();
			Thread.sleep(2000);
			
			System.out.println("Current URL : "+driver.getCurrentUrl());
		}
		
		
/*	@AfterClass
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
*/
}
