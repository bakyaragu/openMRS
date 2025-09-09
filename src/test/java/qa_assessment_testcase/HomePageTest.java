package qa_assessment_testcase;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import pages.HomePage;

public class HomePageTest extends BaseTest {
@Test
public void homePageverificationTest() {
	HomePage homepage = new HomePage(driver);
	String title = homepage.getTitle();
	System.out.println("Title is "+driver.getTitle());
	assertEquals(title, "Home");
}
}
