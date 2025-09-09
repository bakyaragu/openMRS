package qa_assessment_testcase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import constants.Constants;
import pages.FindPatientpage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisteredPatientDetailPage;
import utilities.CommonUtils;

public class BaseTest {
	public WebDriver driver;

	public void initializeDriver() {
		try {
			CommonUtils.loadProperties();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(driver==null) {
			setDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7000));
			driver.manage().window().maximize();
		}
	}

	public void setDriver() {
		switch(Constants.BROWSER) {
		case "chrome":
			System.setProperty(Constants.CHROME_DRIVER, Constants.CHROME_DRIVER_PATH);
			driver = new ChromeDriver();
			break;
		}
	}
	@BeforeMethod
	public HomePage login() {
		try {
			initializeDriver();
			driver.get(Constants.APP_URL);
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(Constants.USERNAME, Constants.PASSWORD, Constants.LOCATION);
			/*
			 * WebDriverWait wait=new WebDriverWait (driver, Duration.ofSeconds(3000));
			 * WebElement register
			 * =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
			 * "//a[contains(@href,'/openmrs/registrationapp/registerPatient')]"))));
			 * Assert.assertTrue(register.isDisplayed());
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HomePage(driver);
	}
	@AfterMethod
	public void closeSession() {
		driver.close();
	}

	public RegisteredPatientDetailPage findpatientAndOpenDetails(String name) {
		HomePage homePage = new HomePage(driver);
		FindPatientpage patientpage= homePage.findPatient();
		RegisteredPatientDetailPage patientdetailsPage = patientpage.patientSearch(name); 
		return patientdetailsPage;
	}
	
	public static String getScreenshot(String testCaseName ,WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File DestFile = new File(System.getProperty("user.dir") + "//reports//" + testCaseName +System.currentTimeMillis()+ ".png");
		String filepath = DestFile.getAbsolutePath();
		FileUtils.copyFile(scrFile, DestFile);
		return filepath;
		} 
}
