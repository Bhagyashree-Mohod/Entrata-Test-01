package com.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.resources.BaseTest;

public class Pageobjects extends BaseTest {

	@FindBy(xpath = "//a[@class='main-header-logo']")
	private WebElement logo_Entrata;

	public  Pageobjects LOGO() {
		logo_Entrata.click();
		//click(logo_Entrata);
		return this;
	}
	
	public  String gettext() {
		String logo_title = logo_Entrata.getText();
		//System.out.println(logo_title);
		return logo_title;
	}
	
	

	public  WebElement LOGO_1() {
		return logo_Entrata;
	}
	
	@FindBy(xpath = "//a[@class='main-nav-link']")
	private WebElement Base_camp;

	public  Pageobjects Base_camp() {
		Base_camp.click();
		
		return this;
	}
	
}


