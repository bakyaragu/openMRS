package qa_assessment_testcase;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataprovider.GetDataFromJSON;
import pages.RegisteredPatientDetailPage;

public class AddPastVisitAndVerifyFutureDateTest extends BaseTest{
	
	@Test(dataProvider = "getInputdata")
	public void clickAddPastVisitAndVerifyFutureDateTest(HashMap<String, String> input) {
		
		RegisteredPatientDetailPage detailsPage=findpatientAndOpenDetails(input.get("name"));
		boolean isDisplayed = detailsPage.checkPatientIDisplayed();
		assertTrue(isDisplayed);
		detailsPage.clickAddPastVisit();
		boolean isFutureDateClickable = detailsPage.verifyFutureDataIsNotClickable();
		assertFalse(isFutureDateClickable);
		detailsPage.redirectToDetailsPage();
	}
	
	
	
	@DataProvider
	public static Object[][] getInputdata(){
		 List<HashMap<String, String>> list = null;
		list = GetDataFromJSON.jsonFileToMap("./src/test/java/test_data/inputForPatientSearch.json");
		return new Object[][] {{list.get(0)}};

	}

}
