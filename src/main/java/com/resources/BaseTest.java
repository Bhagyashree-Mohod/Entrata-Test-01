package com.resources;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.config.Config;

public class BaseTest {

	public static ExtentHtmlReporter htmlReport;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest Node1;

	public BaseTest() {

		PageFactory.initElements((Config.driver), this);

	}

	@BeforeSuite
	public void beforesuite() throws Exception {
		Config.driver = new ChromeDriver();

	}

	@BeforeClass
	public void SuiteMethod() {

		System.out.println("WEL-COME TO AUTOMATION-FRAMEWORK");

		htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Automation_Report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);
		extent.setSystemInfo("ProjectName", "India");
		extent.setSystemInfo("Host Name", ".com");
		extent.setSystemInfo("Environment", "Live");
		extent.setSystemInfo("User Name", "Security_Audit");
		htmlReport.config().setDocumentTitle("Security_Audit");

	}

	//@BeforeTest


	public void click(WebElement e) {

		//waitForVisibilityOfElement(e);
		e.click();

	}

	@AfterSuite
	public void aftersuite() throws Exception {

		extent.flush();

		try {

			Config.driver.quit();

		} catch (Exception e) {

		}

	}
}
