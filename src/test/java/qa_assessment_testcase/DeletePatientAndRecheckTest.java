package qa_assessment_testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataprovider.GetDataFromJSON;
import pages.FindPatientpage;
import pages.RegisteredPatientDetailPage;

public class DeletePatientAndRecheckTest extends BaseTest{

	@Test(dataProvider = "getInputdata")
	public void deletePatientRecordAndVerifyInFindPatientTest(HashMap<String , String> inputdata) {
		RegisteredPatientDetailPage detailsPage= findpatientAndOpenDetails(inputdata.get("name"));
		boolean ispatientIDsiplayed = detailsPage.checkPatientIDisplayed();
		assertTrue(ispatientIDsiplayed);
		FindPatientpage findPatientpage = detailsPage.deletePatientWithReason(inputdata.get("reason"));
		String toasterMsg = detailsPage.getToasterMessage();
		assertEquals(toasterMsg, "Patient has been deleted successfully");
		String msg = findPatientpage.findDeletedPatient(inputdata.get("name"));
		assertEquals(msg, "No matching records found");
			
	}

	
	@DataProvider
	public static Object[][] getInputdata(){
		 List<HashMap<String, String>> list = null;
		list = GetDataFromJSON.jsonFileToMap("./src/test/java/test_data/inputForDeletePatientWithReason.json");
		return new Object[][] {{list.get(0)}};

	}
}
