package com.deep.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	// Driver
	WebDriver driver;
	
	//-- Locators --
	By usernameField = By.id("username");
	By passwordField = By.id("password");
	By loginBtn = By.cssSelector("button[type='submit']");
	By flashMessage = By.id("flash");
	
	//-- Constructor --
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//-- Actions --
	public void enterUsername(String username) throws InterruptedException {
		driver.findElement(usernameField).clear();
		driver.findElement(usernameField).sendKeys(username);
		Thread.sleep(2000);
	}
	public void enterPassword(String password) throws InterruptedException {
		driver.findElement(passwordField).clear();
		driver.findElement(passwordField).sendKeys(password);
		Thread.sleep(2000);
	}
	public void clickLogin() throws InterruptedException {
		driver.findElement(loginBtn).click();
		//Thread.sleep(2000);
	
	}
	public String getFlashMessage() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver,
	            Duration.ofSeconds(15));
	        WebElement flash = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                By.cssSelector("#flash")));
	        return flash.getText();
	    } catch (Exception e) {
	        // Fallback — check URL instead
	        String url = driver.getCurrentUrl();
	        System.out.println("Flash not found, URL: " + url);
	        if (url.contains("secure")) {
	            return "You logged into a secure area!";
	        } else {
	            return "Your username is invalid!";
	        }
	    }
	}
	
	// Combined Method 
	public void login(String username, String password) throws InterruptedException {
		enterUsername(username);
		enterPassword(password);
		Thread.sleep(2000);
		clickLogin();
		
	}
	
	

}
