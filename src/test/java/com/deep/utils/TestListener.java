package com.deep.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentReportManager.getExtentReports();
    ExtentTest test;

    // ── Test Started ──
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.log(Status.INFO, "Test Started: "
            + result.getMethod().getMethodName());
    }

    // ── Test Passed ──
    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed ✅");
    }

    // ── Test Failed — take screenshot ──
    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed ❌: "
            + result.getThrowable().getMessage());

        // Take screenshot
        Object testClass = result.getInstance();
        WebDriver driver = DriverFactory.getDriver();

        try {
            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new java.util.Date());
            String screenshotPath = System.getProperty("user.dir")
                + "/screenshots/"
                + result.getMethod().getMethodName()
                + "_" + timestamp + ".png";

            File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(screenshotPath));

            // Attach screenshot to report
            test.addScreenCaptureFromPath(screenshotPath,
                "Failure Screenshot");
            test.log(Status.INFO, "Screenshot captured: "
                + screenshotPath);

        } catch (Exception e) {
            test.log(Status.WARNING, "Screenshot failed: "
                + e.getMessage());
        }
    }

    // ── Test Skipped ──
    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped ⚠️");
    }

    // ── All Tests Finished ──
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}