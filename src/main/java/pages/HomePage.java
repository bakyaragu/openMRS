package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	By register = By.xpath("//a[contains(@href,'/openmrs/registrationapp/registerPatient')]");
	By logout = By.xpath("//a[contains(text(),'Logout')]");
	By findPatient = By.id("coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension");
	
	public PatientRegisterPage register() {
		 driver.findElement(register).click();
		 PatientRegisterPage registerPatientPage = new PatientRegisterPage(driver);
		 return registerPatientPage;
	}
	
	public FindPatientpage findPatient() {
		driver.findElement(findPatient).click();
		FindPatientpage findPatient = new FindPatientpage(driver);
		return findPatient;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}


}
