package com.deep.utils;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class DriverFactory {
	
	/*private static WebDriver driver;
	
	// Create and return driver
	public static WebDriver getDriver() {
		if (driver == null) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));			
		}
		return driver;
	}
	
	// Quit driver
	public static void quitDrvier() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}*/
	
	
	// ThreadLocal — each thread gets its own driver
    private static ThreadLocal<WebDriver> driver
        = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
        	
        	ChromeOptions options = new ChromeOptions();

            // options.addArguments("--headless");	// Local machine Jenkins → No need for headless!
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
                                  
         // ── ADD THESE NEW LINES ──
            // Bypass bot detection
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.setExperimentalOption("excludeSwitches",
                new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);
            options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/149.0.0.0 Safari/537.36");
            
            driver.set(new ChromeDriver(options));
            driver.get().manage().window().maximize();
            driver.get().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
	
	// Take screenshot and return path
	public static String takeScreenshot(String testName) {
	    String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss")
	        .format(new java.util.Date());

	    String screenshotPath = System.getProperty("user.dir")
	        + "/screenshots/" + testName + "_" + timestamp + ".png";

	    try {
	        File src = ((TakesScreenshot) driver)
	            .getScreenshotAs(OutputType.FILE);
	        File dest = new File(screenshotPath);
	        FileUtils.copyFile(src, dest);
	        System.out.println("Screenshot saved: " + screenshotPath);
	    } catch (Exception e) {
	        System.out.println("Screenshot failed: " + e.getMessage());
	    }
	    return screenshotPath;
	}
	

}
