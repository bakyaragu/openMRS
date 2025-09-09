package pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Base;

public class RegisteredPatientDetailPage extends Base{
	WebDriver driver;

	public RegisteredPatientDetailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By startVisit = By.xpath("//div[contains(text(),'Start Visit')]");
	By confirmStartVisitDialog = By.id("quick-visit-creation-dialog");
	By confirmStartVisit = By.id("start-visit-with-visittype-confirm");
	By endVisit = By.xpath("//ul[@class='float-left d-none d-lg-block']//a[@id='referenceapplication.realTime.endVisit'][1]");
	By confirmEndVisitDialog = By.id("end-visit-dialog");
	By confirmEndVisit = By.xpath("//div[@id='end-visit-dialog']//button[@class='confirm right']");	
	By patientId = By.xpath("//em[text()='Patient ID']/following-sibling::span");
	By attachmentCaption = By.xpath("//div[@class='att_thumbnail-caption-section']");
	By recentVisits = By.xpath("//visitbyencountertype[@class='ng-isolate-scope']/ul/li");
	By recentVisitAttachment = By.xpath("//visitbyencountertype[@class='ng-isolate-scope']/ul/li/div");
	By vitalsHeight = By.xpath("//span[@id='height']//span[1]");
	By vitalsWieght = By.xpath("//span[@id='weight']//span[1]");
	By vitalsBMI = By.xpath("//span[@id='calculated-bmi']");
	By mergeVisit = By.id("org.openmrs.module.coreapps.mergeVisits");
	By addPastVisit = By.id("org.openmrs.module.coreapps.createRetrospectiveVisit");
	By allcalenderDates = By.xpath("//div[@id='body-wrapper']/following-sibling::div[1]/div[@class='datetimepicker-days'][1]//tr/td");
	By deletePatient = By.id("org.openmrs.module.coreapps.deletePatient");
	By deletePatientDialog = By.id("delete-patient-creation-dialog");
	By confirmDeletePatient = By.xpath("//div[@id='delete-patient-creation-dialog']//button[@class='confirm right']");
	By deleteReason = By.id("delete-reason");
	
		
	public boolean checkPatientIDisplayed() {
		return driver.findElement(patientId).isDisplayed();
	}

	public VisitDetailsPage startAndConfirmVisit() {
		driver.findElement(startVisit).click();
		waitForelementToBeVisible(confirmStartVisitDialog);
		driver.findElement(confirmStartVisit).click();
		VisitDetailsPage visitPage=new VisitDetailsPage(driver);
		return visitPage;
	}
	
	public void endVisit() {
		driver.findElement(endVisit).click();
		waitForelementToBeVisible(confirmEndVisitDialog);
		driver.findElement(confirmEndVisit).click();		
	}
	
	public boolean hasAttachment() {
		String txt = driver.findElement(attachmentCaption).getText();
		if(txt!=null) {
			return true;
		}
		return false;
	}
	
	public boolean hasRecentVisitWithCurrentDatewithtag(String tag) {
		List<WebElement> list= driver.findElements(recentVisits);
		for (WebElement ele : list) {
			String date =ele.getText();
			DateFormat dateFormat = new SimpleDateFormat("dd.MMM.yyyy");
			Date d = new Date();
			String currentDate = dateFormat.format(d);
			if(date.contains(currentDate)) {
				if(date.contains(tag))
				{
				System.out.println("Both date are same"+date);
				return true;
				}
			}
		}
		return false;
	}
	
	
	public boolean hasRecentVisitWithCurrentDatewithtags(String tag1,String tag2) {
		List<WebElement> list= driver.findElements(recentVisits);
		for (WebElement ele : list) {
			String date =ele.getText();
			DateFormat dateFormat = new SimpleDateFormat("dd.MMM.yyyy");
			Date d = new Date();
			String currentDate = dateFormat.format(d);
			if(date.contains(currentDate)) {
				if(date.contains(tag1) && date.contains(tag2))
				{
				System.out.println("Both date are same"+date);
				return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasVitalsEntryMatched(String inputheight, String inputweight) {
		String calculatedBMI=calculateBMI(Float.valueOf(inputheight), Float.valueOf(inputweight));
		String height = driver.findElement(vitalsHeight).getText();
		String weight = driver.findElement(vitalsWieght).getText();
		String calcBMI = driver.findElement(vitalsBMI).getText();
		if(inputheight.equals(height) && inputweight.equals(weight) && calculatedBMI.equals(calcBMI)) {
			return true;
		}
		return false;
	}	
	
	public MergeVisitsPage clickMergeVisit() {
		driver.findElement(mergeVisit).click();
		MergeVisitsPage mergeVisit = new MergeVisitsPage(driver);
		return mergeVisit;
		
	}

	public void clickAddPastVisit() {
		driver.findElement(addPastVisit).click();	
	}
	public boolean verifyFutureDataIsNotClickable() {
		List<WebElement> listOfDate = driver.findElements(allcalenderDates);
		for (int i = 0; i < listOfDate.size(); i++) {
			String classAttribute = listOfDate.get(i).getAttribute("class");
			if(classAttribute.equalsIgnoreCase("day active")) {
				String futureDateClassAttr = listOfDate.get(i+1).getAttribute("class");
				if(futureDateClassAttr.equalsIgnoreCase("day disabled")) {
					System.out.println("future date is not clickable");
					return false;
				}
			}
		}
		return true;
	}
	
	public FindPatientpage deletePatientWithReason(String reason) {
		driver.findElement(deletePatient).click();
		waitForelementToBeVisible(deletePatientDialog);
		driver.findElement(deleteReason).sendKeys(reason);
		driver.findElement(confirmDeletePatient).click();
		FindPatientpage findPatientpage = new FindPatientpage(driver);
		return findPatientpage;
	}

}

