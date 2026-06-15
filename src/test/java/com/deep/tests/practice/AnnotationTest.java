package com.deep.tests.practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AnnotationTest {

	// Runs once before all suites
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("1. @BeforeSuite - Launch Test Environment");
	}
	
	// Runs before <test> in tesng.xml
	@BeforeTest
	public void beforeTest() {
		System.out.println("2. @BeforeTest - Setup Test Configuration");
	}
	
	// Runs once before first @Test in the class
	@BeforeClass
	public void beforeClass() {
		System.out.println("3. @BeforeClass - Open Browser");
	}
	
	// Runs before EVERY @Test
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("4. @BeforeMethod - Navigate to URL");
	}
	
	// ── Test 1 ──
    @Test(priority = 1)
    public void testLogin() {
        System.out.println("5. @Test — Login Test");
    }

    // ── Test 2 ──
    @Test(priority = 2)
    public void testSearch() {
        System.out.println("5. @Test — Search Test");
    }

    // ── Test 3 ──
    @Test(priority = 3)
    public void testLogout() {
        System.out.println("5. @Test — Logout Test");
    } 
	
	
	// Runs after EVERY @Test
	@AfterMethod
	public void afterMethod() {
		System.out.println("6. @AfterMethod - Take Screenshot");
	}
	
	// Runs once after the last @Test in Class
	@AfterClass
	public void afterClass() {
		System.out.println("7. @AfterClass - Close Browser");
	}
	
	// Runs after <test> in testng.xml
	@AfterTest
	public void afterTest() {
	System.out.println("8. @AfterTest - Cleanup Test Data");	
	}
	
	// Runs once after all suites
	@AfterSuite
	public void afterSuite() {
		System.out.println("9. @AfterSuite - Shutdown Environment");
	}
}
