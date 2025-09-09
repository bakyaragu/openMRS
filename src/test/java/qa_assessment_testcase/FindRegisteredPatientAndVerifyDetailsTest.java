package qa_assessment_testcase;

import static org.testng.Assert.assertTrue;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import dataprovider.GetDataFromJSON;
import pages.RegisteredPatientDetailPage;

public class FindRegisteredPatientAndVerifyDetailsTest extends BaseTest{

	@Test(dataProvider = "getInputdata")
	public void findAndverifyPatientDetailsTest(HashMap<String , String> inputdata) {
	
		RegisteredPatientDetailPage detailsPage= findpatientAndOpenDetails(inputdata.get("name"));
		boolean ispatientIDsiplayed = detailsPage.checkPatientIDisplayed();
		assertTrue(ispatientIDsiplayed);			
	}
	
	@DataProvider
	public static Object[][] getInputdata(){
		 List<HashMap<String, String>> list = null;
		list = GetDataFromJSON.jsonFileToMap("./src/test/java/test_data/inputForPatientSearch.json");
		return new Object[][] {{list.get(0)}};

	}
}
