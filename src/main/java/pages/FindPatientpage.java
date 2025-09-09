package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Base;

public class FindPatientpage extends Base{
	public WebDriver driver;
	

	public FindPatientpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	By patientSearch = By.xpath("//input[@id='patient-search']");
	By listOfPatient = By.xpath("//table[@id='patient-search-results-table']/tbody/tr/td");
	By listOfPatientLocator = By.xpath("//td[normalize-space(text())='No matching records found']");
	By patientNamecolumn = By.xpath("//tbody[@role='alert']/tr/td[2]");
	
	public RegisteredPatientDetailPage patientSearch(String nameFamilyName) {
		waitForelementToBeVisible(patientSearch);
		driver.findElement(patientSearch).sendKeys(nameFamilyName);
		waitForelementToBeVisible(listOfPatient);
		List<WebElement> listofPatient = driver.findElements(patientNamecolumn);
		for (WebElement webElement : listofPatient) {
			String name = webElement.getText();
			if (name.equalsIgnoreCase(nameFamilyName)) {
				webElement.click();
				break;
			}
		}
		RegisteredPatientDetailPage detailpage = new RegisteredPatientDetailPage(driver);
		return detailpage;
	}
	
	public String findDeletedPatient(String name) {
		waitForelementToBeVisible(patientSearch);
		driver.findElement(patientSearch).sendKeys(name);
		waitForelementToBeVisible(listOfPatientLocator);
		String text= driver.findElement(listOfPatient).getText();
		return text;
		
		
		
	}


}
