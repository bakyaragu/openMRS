package qa_assessment_testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataprovider.GetDataFromJSON;
import pages.AttachmentsPage;
import pages.RegisteredPatientDetailPage;
import pages.VisitDetailsPage;

public class AddAttachmentInStartVisitTest extends BaseTest{
	
	@Test(dataProvider = "getInputdata")
	public void startVisitAddAttachmentVerifydetailsTest(HashMap<String, String> input) {
		RegisteredPatientDetailPage detailsPage=findpatientAndOpenDetails(input.get("name"));
		boolean isDisplayed = detailsPage.checkPatientIDisplayed();
		assertTrue(isDisplayed);
		VisitDetailsPage visitPage= detailsPage.startAndConfirmVisit();
		AttachmentsPage attachment = visitPage.clickAttachments();
		String toasterMsg = attachment.addAttachment(System.getProperty("user.dir")+input.get("file"));
		assertEquals("The attachment was successfully uploaded.", toasterMsg);
		attachment.redirectToDetailsPage();
		boolean is_Displayed = detailsPage.checkPatientIDisplayed();
		assertTrue(is_Displayed);
		boolean hasattachment = detailsPage.hasAttachment();
		assertTrue(hasattachment);
		boolean hasrecentVisit = detailsPage.hasRecentVisitWithCurrentDatewithtag(input.get("tag1"));	
		assertTrue(hasrecentVisit);
		//////delete it afterwards
		detailsPage.endVisit();
		
	}

	@DataProvider
	public static Object[][] getInputdata(){
		 List<HashMap<String, String>> list = null;
		list = GetDataFromJSON.jsonFileToMap("./src/test/java/test_data/inputForstartVisitAndAttachment.json");
		return new Object[][] {{list.get(0)}};

	}
}
