package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.Base;

public class MergeVisitsPage extends Base {
	
	WebDriver driver;

	public MergeVisitsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	By first_visit = By.xpath("//table[@id='active-visits']/tbody[1]/tr[1]/td[1]/input");
	By second_visit = By.xpath("//table[@id='active-visits']/tbody[1]/tr[2]/td[1]/input");
	By mergeSelectedButton = By.id("mergeVisitsBtn");
	
	
	public void selectRecentTwoVisitsAndMerge() {
		driver.findElement(first_visit).click();
		driver.findElement(second_visit).click();
		waitForelementToBeVisible(mergeSelectedButton);
		driver.findElement(mergeSelectedButton).click();
	}

	
}
