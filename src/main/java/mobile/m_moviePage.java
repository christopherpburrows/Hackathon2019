package mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.TestRunner;

import TestBase.TestSetup;

public class m_moviePage {

	WebDriver driver;
	private WebDriverWait wait;
	
	public m_moviePage() {
		
		try {
			driver=TestSetup.testcontext.get().getWebDriver();
			wait= new WebDriverWait(driver, 30);
			PageFactory.initElements(driver, this);
		} catch (Exception e) {
			Assert.fail("Unable to set up movie page class "+e);
		}

	}
	
	@FindBy(xpath="//*[@id='titleOverview']//h1")
	WebElement movies;
	
	public m_moviePage verifyMoviename(String movieName) {
		try {
			Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(movies)).getText().split("\\(")[0].trim(),movieName,"Movies Name not matching");
			Reporter.log("Movie Name "+ movieName+ " Matched");
		} catch (Exception e) {			
			Assert.fail("Unable to verify movie name"+e);
		}
		return this;
	}
	
	
	@FindBy(xpath="//a[@itemprop='director']//span")
	WebElement director;
	
	public m_moviePage verifyDirectorname(String directorName) {
		
		try {
			Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(director)).getText(),directorName,"Director Name not matching");
			Reporter.log("Direcotr Name "+ directorName+ " Matched");
		} catch (Exception e) {			
			Assert.fail("Unable to verify director name "+e);
		}
		return this;
	}
	

	
}
