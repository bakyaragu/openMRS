package qa_assessment_testcase;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataprovider.GetDataFromJSON;
import pages.CaptureVitalsPage;
import pages.RegisteredPatientDetailPage;
import pages.VisitDetailsPage;

public class CaptureVitalsAndVerifyBMITest extends BaseTest{
	
	@Test(dataProvider = "getInputdata")
	public void captureVitalsAndVerifyBMITest(HashMap<String, String> input) {
		RegisteredPatientDetailPage detailsPage=findpatientAndOpenDetails(input.get("name"));
		boolean isDisplayed = detailsPage.checkPatientIDisplayed();
		assertTrue(isDisplayed);
		VisitDetailsPage visitPage= detailsPage.startAndConfirmVisit();
		CaptureVitalsPage vitalsPage= visitPage.clickCaptureVitals();
		boolean isBMIVerified = vitalsPage.enterVitalsAndVerifyBMI(input.get("height"), input.get("weight"));
		assertTrue(isBMIVerified);
		vitalsPage.saveVitalsForm();
		visitPage.endVisit();
		visitPage.redirectToDetailsPage();
		boolean hasVitalsMatched = detailsPage.hasVitalsEntryMatched(input.get("height"), input.get("weight"));
		assertTrue(hasVitalsMatched);
		boolean hasrecentVisit = detailsPage.hasRecentVisitWithCurrentDatewithtag(input.get("tag2"));
		assertTrue(hasrecentVisit);
		
	}
	
	
	@DataProvider
	public static Object[][] getInputdata(){
		 List<HashMap<String, String>> list = null;
		list = GetDataFromJSON.jsonFileToMap("./src/test/java/test_data/inputForVitals.json");
		return new Object[][] {{list.get(0)}};

	}

}
