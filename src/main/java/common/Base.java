package common;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.RegisteredPatientDetailPage;

public class Base {
	WebDriver driver;

	public Base(WebDriver driver) {
		this.driver = driver;
	}

	By age_dob =By.xpath("//span[contains(text(),'year(s)')]");
	By navigate = By.xpath("//button[@class='confirm']/following-sibling::button[1]");
	By givenName = By.xpath("//span[@class='PersonName-givenName']");
	By givenFamName = By.xpath("//span[@class='PersonName-familyName']");
	By toaster = By.xpath("//div[@class='toast-item-wrapper']");
	
	public RegisteredPatientDetailPage redirectToDetailsPage() {
		driver.navigate().refresh();
		waitForelementToBeVisible(givenFamName);
		driver.findElement(givenName).click();
		RegisteredPatientDetailPage detailPage = new RegisteredPatientDetailPage(driver);
		return detailPage;
	}
	
	public void clickNextPage() {
		driver.findElement(navigate).click();
	}
	
	public int getAge() {
		String agedob=driver.findElement(age_dob).getText().trim();
		String[] arr=agedob.split("year\\(s\\) \\( ");
		String str = arr[0].trim();
		int age= Integer.valueOf(str);
		return age;
	}

	public String getDOB() {
		String agedob=driver.findElement(age_dob).getText().trim();
		String[] arr=agedob.split("year\\(s\\) \\( ");
		String dob= arr[1];
		String[] dateofbirth=dob.split("\\)");
		return dateofbirth[0];
	}

	public Map<String,String> dob_inSeparatevalue() {
		String dob=getDOB();
		String[] str=dob.split("\\.");
		Map<String,String> map=new HashMap<>();
		map.put("date", str[0]);
		map.put("month", str[1]);
		map.put("year", str[2]);
		String month = getMonthNumber(map.get("month"));
		map.replace("month", month);
		return map;
	}

	public String getMonthNumber(String month) {
		Date date = null;
		try {
			date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int monthNumber = calendar.get(Calendar.MONTH) + 1;
		return String.valueOf(monthNumber);
	}

	public int calculateAgefromDob() {
		Map<String , String> dateofbirth= dob_inSeparatevalue();
		int year=Integer.parseInt(dateofbirth.get("year"));
		int month=Integer.parseInt(dateofbirth.get("month"));
		int date=Integer.parseInt(dateofbirth.get("date"));
		LocalDate birthdate = LocalDate.of(year, month, date);
		// Current Date
		LocalDate currentDate = LocalDate.now();
		int age = calculateAge(birthdate, currentDate);

		System.out.println("Age: " + age + " years");
		return age;
	}

	public static int calculateAge(LocalDate birthdate, LocalDate currentDate) {
		// Calculate period between birthdate and current date
		Period period = Period.between(birthdate, currentDate); 
		return period.getYears();
	}
	/*
	 * public List<WebElement> waitForelementsToBeVisisble( By locator) {
	 * WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5000)); return
	 * wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	 * 
	 * }
	 */
	
	public void waitForelementToBeVisible( By locator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
	}
	
	public void waitForelementToBeInVisible( By locator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));			
	}
	
	public String calculateBMI(Float h, Float w) {
		Float bmi=(w*100*100)/(h*h);
		DecimalFormat df_obj = new DecimalFormat("#.#");
        System.out.println("expected BMI is "+df_obj.format(bmi));
		return df_obj.format(bmi);
	}
	
	public String getToasterMessage() {
		waitForelementToBeVisible(toaster);
		return driver.findElement(toaster).getText();
		
	}
}
