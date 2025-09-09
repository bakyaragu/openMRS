package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import common.Base;

public class PatientRegisterPage extends Base{
	public WebDriver driver;

	public PatientRegisterPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	By givenName = By.xpath("//input[@type='text'][@name='givenName']");
	By familyName = By.name("familyName");
	//By navigate = By.xpath("//button[@class='confirm']/following-sibling::button[1]");
	By gender = By.id("gender-field");
	By b_day = By.id("birthdateDay-field");
	By b_month =By.id("birthdateMonth-field");
	By b_year = By.id("birthdateYear-field");
	By address1 = By.id("address1");
	By phoneNumber = By.xpath("//input[@type='text'][@name='phoneNumber']");
	By relationshipType = By.id("relationship_type");
	By personName = By.xpath("//input[@placeholder='Person Name']");
	By registeredname = By.xpath("//span[text()='Name: ']/..");
	By registeredGender = By.xpath("//span[text()='Gender: ']/..");
	By registeredbday = By.xpath("//span[text()='Birthdate: ']/..");
	By registeredAddress = By.xpath("//span[text()='Address: ']/..");
	By registeredPhone = By.xpath("//span[text()='Phone Number: ']/..");
	By registeredRelatives = By.xpath("//span[text()='Relatives: ']/..");
	By confirmButton = By.id("confirmation_label");
	By submit =By.xpath("//input[@id='submit']");

	public void fillPatientdetails(String name, String familyname, String gend,String day,
			String month, String year,String address, String phone) {
		driver.findElement(givenName).sendKeys(name);
		driver.findElement(familyName).sendKeys(familyname);
		clickNextPage();
		selectByText(driver.findElement(gender), gend);
		clickNextPage();
		driver.findElement(b_day).sendKeys(day);
		selectByText(driver.findElement(b_month), month);
		driver.findElement(b_year).sendKeys(year);
		clickNextPage();
		driver.findElement(address1).sendKeys(address);
		clickNextPage();
		driver.findElement(phoneNumber).sendKeys(phone);
		clickConfirm();
	}

	public RegisteredPatientDetailPage submitform() {
		driver.findElement(submit).click();
		RegisteredPatientDetailPage detailpage = new RegisteredPatientDetailPage(driver);
		return detailpage;
	}
	public String getRegisteredName() {
		String registered_name =driver.findElement(registeredname).getText();
		return splitString(registered_name);
	}
	public String getRegisteredGender() {
		String registered_gender = driver.findElement(registeredGender).getText();
		return splitString(registered_gender);
	}
	public String getRegisteredBirthDay() {
		String registered_bday =driver.findElement(registeredbday).getText();
		return splitString(registered_bday);
	}
	public String getRegisteredAddress() {
		String registered_Address =driver.findElement(registeredAddress).getText();
		return splitString(registered_Address);
	}
	public String getRegisteredphone() {
		String registered_phone =driver.findElement(registeredPhone).getText();
		return splitString(registered_phone);
	}


	public String splitString(String registerdString) {
		String[] str = registerdString.split(":");
		return str[1].trim();
	}

	public void clickConfirm() {
		driver.findElement(confirmButton).click();
	}


	public void selectByGivenValue(WebElement element, String value) { Select
		select = new Select(element);
		select.selectByValue(value);
	}

	public void selectByText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}


}
