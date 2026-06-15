package com.deep.tests.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstTest {
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();		
	}
	
	@Test
	public void openGoogle() {
		driver.get("https://www.google.com");
		System.out.println("Title: "+ driver.getTitle());
		assert driver.getTitle().contains("Google");
		
	}
	
	/*@AfterClass
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}*/

}
