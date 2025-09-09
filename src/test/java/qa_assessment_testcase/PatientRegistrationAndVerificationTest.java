package qa_assessment_testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataprovider.GetDataFromExcel;
import pages.HomePage;
import pages.PatientRegisterPage;
import pages.RegisteredPatientDetailPage;

public class PatientRegistrationAndVerificationTest extends BaseTest{


	@Test(dataProvider = "getdata")
	public void registerPatientTest(String name, String familyName, String gender,String day,
			String month, String year,String address, String phone) {
		HomePage homePage = new HomePage(driver);
		PatientRegisterPage registerPatient = homePage.register();
		registerPatient.fillPatientdetails(name, familyName, gender, day, month, year, address, phone);
		String inputName = name+", "+familyName;
		assertEquals(registerPatient.getRegisteredName(), inputName);
		assertEquals(registerPatient.getRegisteredGender(), gender);
		String inputbday = day+", "+month+", "+year;
		assertEquals(registerPatient.getRegisteredBirthDay(), inputbday);
		assertEquals(registerPatient.getRegisteredAddress(), address);
		assertEquals(registerPatient.getRegisteredphone(), phone);
		RegisteredPatientDetailPage detail=registerPatient.submitform();
		boolean isDisplayed = detail.checkPatientIDisplayed();
		assertTrue(isDisplayed);
		int expectedAge = detail.calculateAgefromDob();
		System.out.println("expecetdAge is "+ expectedAge);
		int actualAge =detail.getAge();
		System.out.println("ActualAge is "+ actualAge);
		assertEquals(expectedAge, actualAge);
	}
	
	@DataProvider
	public static String[][] getdata(){
		String[][] data = GetDataFromExcel.getExcelData("./src/test/java/test_data/RegisterPatientData.xlsx");
		return data;

	}

	
}
