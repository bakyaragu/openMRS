package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.Base;

public class AttachmentsPage extends Base{
	public WebDriver driver;
	

	public AttachmentsPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	By dropFile = By.id("visit-documents-dropzone");
	By caption=By.xpath("//textarea[@placeholder='Enter a caption']");
	By uploadFile = By.xpath("//button[text()='Upload file']");
	
	
	public void uploadFile(String filePath) throws AWTException {
		StringSelection sel = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
		driver.findElement(dropFile).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Robot robot = new Robot();		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);		
	}
	
	public String addAttachment(String filePath) {
		try {
			uploadFile(filePath);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(caption).click();
		driver.findElement(caption).sendKeys("test");
			waitForelementToBeVisible(uploadFile);
			driver.findElement(uploadFile).click();
			return getToasterMessage();
		}
	
	
	
}
