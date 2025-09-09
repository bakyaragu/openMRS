package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.Base;

public class VisitDetailsPage extends Base{
public WebDriver driver;
	

	public VisitDetailsPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	By endVisitButton = By.xpath("//div[@id='visit-details']/div[2]/a[contains(@href,'EndVisit')]");
	By endVisitDialog = By.id("end-visit-dialog");
	By confirmEndVisit = By.xpath("//div[@id='end-visit-dialog']//button[text()='Yes']");
	By visitNoteButton = By.xpath("//i[@class='icon-stethoscope']");
	By attachments = By.xpath("//a[@id='attachments.attachments.visitActions.default']");
	By captureVitals = By.xpath("//a[@id='referenceapplication.realTime.vitals']");
	By admitToInPatient = By.xpath("//a[@id='referenceapplication.realTime.simpleAdmission']");
	
	public void endVisit() {
		driver.findElement(endVisitButton).click();
		waitForelementToBeVisible(endVisitDialog);
		driver.findElement(confirmEndVisit).click();
	}
	
	public AttachmentsPage clickAttachments() {
		driver.findElement(attachments).click();
		AttachmentsPage attach = new AttachmentsPage(driver);
		return attach;
	}
	
	public CaptureVitalsPage clickCaptureVitals() {
		driver.findElement(captureVitals).click();
		CaptureVitalsPage captureVitals = new CaptureVitalsPage(driver);
		return captureVitals;
	}
}
