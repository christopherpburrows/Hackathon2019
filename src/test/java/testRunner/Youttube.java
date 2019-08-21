package testRunner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestBase.TestContext;
import TestBase.TestSetup;
import TestBase.Webdriver;
import TestBase.Webdriver.BROWSERS;
import WebPages.youtube;
import mobile.Emulator;

public class Youttube {

	
	WebDriver driver;String browser;
	private ThreadLocal<String> testName = new ThreadLocal<>();

	@Parameters({"browser"})
	@BeforeClass
	public void beforeclass(String browser) {

		
		driver =	Webdriver.setBrowsers(BROWSERS.valueOf(browser.toUpperCase()));

		TestSetup.testcontext.set(new TestContext());
		TestSetup.testcontext.get().setWebDriver(driver);
		Webdriver.maximizeWindow(driver);
		Webdriver.navigatetoUrl(driver,"https://www.youtube.com/");
		

	}
	



	@Test
	public void test() {
		
		youtube youtube = new youtube();
		youtube.enterSearchItem("step-in-forum")
		.clickChannel()
		.navigateVideosTab()
		.getrandomVideofromApi()
		.scrolltoTheVideo()
		.captureScreenShot()
		.clickonVideoInGrid()
		.changeVideoQuality()
		.getUpcomingVideos()
		.uploadFile()
		.validateUploadedFile();
		

	}
	
	@Test
	public void mobileTest() {
		
	try {
		new	Emulator().openCalculator();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
	}

	@AfterMethod
	public void tearing() {

		TestSetup.testcontext.get().getWebDriver().quit();
	}


	@AfterClass
	public void tearup() {

		TestSetup.testcontext.get().getWebDriver().quit();
	}
}
