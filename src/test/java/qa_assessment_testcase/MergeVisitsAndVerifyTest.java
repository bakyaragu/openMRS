package qa_assessment_testcase;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataprovider.GetDataFromJSON;
import pages.MergeVisitsPage;
import pages.RegisteredPatientDetailPage;

public class MergeVisitsAndVerifyTest extends BaseTest {

	@Test(dataProvider = "getInputdata")
	public void mergeVisitsAndVerifywithTagsTest(HashMap<String, String> input) {
		RegisteredPatientDetailPage detailsPage = findpatientAndOpenDetails(input.get("name"));
		MergeVisitsPage mergeVisit =  detailsPage.clickMergeVisit();
		mergeVisit.selectRecentTwoVisitsAndMerge();
		detailsPage.redirectToDetailsPage();
		boolean hasrecentVisit = detailsPage.hasRecentVisitWithCurrentDatewithtags(input.get("tag1"),input.get("tag2"));
		assertTrue(hasrecentVisit);
	}
	
	
	
	
	@DataProvider
	public static Object[][] getInputdata(){
		 List<HashMap<String, String>> list = null;
		list = GetDataFromJSON.jsonFileToMap("./src/test/java/test_data/inputForVitals.json");
		return new Object[][] {{list.get(0)}};

	}
}
