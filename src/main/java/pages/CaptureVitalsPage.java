package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.Base;

public class CaptureVitalsPage extends Base {
	WebDriver driver;

	public CaptureVitalsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By height = By.xpath("//span[@id='height']//input");
	By weight = By.xpath("//span[@id='weight']//input");
	By calculatedBMI = By.xpath("//span[@id='calculated-bmi']");
	By saveForm = By.id("save-form");
	By saveButton = By.xpath("//button[normalize-space(text())='Save']");

	public boolean enterVitalsAndVerifyBMI(String hght, String wght) {
		String expectedBMI = calculateBMI(Float.valueOf(hght),Float.valueOf(wght));
		driver.findElement(height).sendKeys(hght);
		clickNextPage();
		driver.findElement(weight).sendKeys(wght);
		clickNextPage();
		String actualBMI = driver.findElement(calculatedBMI).getText();
		//Float actualBMI= Float.valueOf(actual);		
		System.out.println("Actual BMI is "+actualBMI);
		if(actualBMI.equals(expectedBMI))
		{
			return true;
		}
		return false;
	}

	public void saveVitalsForm() {
		driver.findElement(saveForm).click();
		driver.findElement(saveButton).click();
	}



}
