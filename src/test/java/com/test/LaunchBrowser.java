package com.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.config.Config;
import com.pageobject.Pageobjects;
import com.resources.BaseTest;

public class LaunchBrowser extends BaseTest {

	Pageobjects obj;

	@BeforeTest
	public void beforeTest() {
		obj = new Pageobjects();
	}

	@Test(priority = 1)
	public void launch_Test() {
		Node1 = extent.createTest("Lunch Entrata");

		Config.driver.get("https://www.entrata.com/");
		Config.driver.manage().window().maximize();

		try {
			Config.driver.findElement(By.xpath("//button[@id='rcc-confirm-button']")).click();
			System.out.println("cookies are accepted");
		} catch (Exception e) {

		}
		obj.LOGO();
		obj.gettext();

		String Actual_logo_title = Config.driver.getTitle();
		String Expected_logo_title = "Property Management Software | Entrata";

		Assert.assertEquals(Actual_logo_title, Expected_logo_title);
	}

	// @Test(priority = 2)
	public void Base_camp_URL() {
		obj.Base_camp();
		ArrayList<String> tabs = new ArrayList<>(Config.driver.getWindowHandles());
		Config.driver.switchTo().window(tabs.get(1));

		String Actual_Base_camp_URL = Config.driver.getCurrentUrl();
		String Expected_Base_camp_URL = "https://basecamp.entrata.com/";
		Assert.assertEquals(Actual_Base_camp_URL, Expected_Base_camp_URL);
		Config.driver.close();
		Config.driver.switchTo().window(tabs.get(0));
	}

	 @Test(priority = 3)
	public void Footer_all_Links_click() throws InterruptedException {
		Config.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		JavascriptExecutor js = (JavascriptExecutor) Config.driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		List<WebElement> Footer = Config.driver.findElements(By.xpath("//div[@class='footer-nav-column']//a"));
		int i = 0;
		for (WebElement ele : Footer) {

			List<WebElement> Footer1 = Config.driver.findElements(By.xpath("//div[@class='footer-nav-column']//a"));

			WebElement footer_submenu = Footer1.get(i);

			System.out.println(i);

			JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) Config.driver;
			javaScriptExecutor.executeScript("arguments[0].click()", footer_submenu);

			Thread.sleep(6000);
			System.out.println(Config.driver.getTitle());
			Config.driver.navigate().back();

			i++;
		}

		System.out.println(i);

	}

	@Test(priority = 4)
	public void Image_on_Page() throws InterruptedException {

		List<WebElement> imgElements = Config.driver.findElements(By.tagName("img"));

		for (WebElement imgElement : imgElements) {
			String imageUrl = imgElement.getAttribute("src");

			System.out.println("Image URL: " + imageUrl);
			if (imageUrl != null) {
				checkImageCorruption(imageUrl);
			}
		}
	}

	private void checkImageCorruption(String imageUrl) {
		try {

			URL url = new URL(imageUrl);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("HEAD");

			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				System.out.println("Image at " + imageUrl + " is not corrupted.");

			}
		} catch (Exception e) {

			System.out.println("Error occurred while checking the image at " + imageUrl + ": " + e.getMessage());

		}
	}
}
