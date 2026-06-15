package com.deep.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {
        if (extent == null) {

            // Report location
            String reportPath = System.getProperty("user.dir")
                + "/reports/ExtentReport.html";

            ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter(reportPath);

            // Report configuration
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Selenium Test Report");
            sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // System info shown in report
            extent.setSystemInfo("Tester", "Deepali");
            extent.setSystemInfo("Environment", "Staging");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", "Windows 10");
        }
        return extent;
    }
}