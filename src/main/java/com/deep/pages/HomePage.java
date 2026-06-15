package com.deep.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	WebDriver driver;
	
	// Locators
	By pageHeading = By.cssSelector("h2");
	By logoutButton = By.xpath("//a[@href='/logout']");
		
	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Actions
	public String getPageHeading() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeading));
        
		return driver.findElement(pageHeading).getText();
	}
	
	public void clickLogout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		 WebElement logout = wait.until(ExpectedConditions.presenceOfElementLocated(logoutButton));
        
		//driver.findElement(logoutButton).click();	
		// Use JavaScript click instead of normal click
	    ((org.openqa.selenium.JavascriptExecutor) driver)
	        .executeScript("arguments[0].click();", logout);

	    System.out.println("Logout clicked via JavaScript");
		
	 // Wait 3 seconds for redirect
	    try {
	        Thread.sleep(3000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean isLogoutButtonVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        
		return driver.findElement(logoutButton).isDisplayed();
	}

}
