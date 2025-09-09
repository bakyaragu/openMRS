package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	By username = By.xpath("//input[@placeholder='Enter your username' or @id='username']");
	By password = By.xpath("//input[@placeholder='Enter your password' or @id='password']");
	By location = By.id("Laboratory");
	By login = By.id("loginButton");
	
	public WebElement enterUserName() {
		return driver.findElement(username);
	}
	
	public WebElement enterPassword() {
		return driver.findElement(password);
	}
	
	public WebElement selectLoc() {
		return driver.findElement(location);
	}
	
	public void clickLoginButton() {
		 driver.findElement(login).click();;
	}
	
	public void login(String username, String pwd, String loc) {
		enterUserName().sendKeys(username);
		enterPassword().sendKeys(pwd);
		selectLoc().click();
		clickLoginButton();
	}
}
