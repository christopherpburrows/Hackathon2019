package testRunner;

import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Helper.AppConstants;
import Helper.MoviesDetails;
import TestBase.TestContext;
import TestBase.TestSetup;
import TestBase.Webdriver;
import TestBase.Webdriver.BROWSERS;
import WebPages.Google;
import WebPages.moviePage;
import mobile.m_moviePage;


public class TestRunner implements ITest{

	WebDriver driver;String browser;
	private ThreadLocal<String> testName = new ThreadLocal<>();

	@Parameters({"browser"})
	@BeforeClass
	public void beforeclass(String browser) {

		this.browser=browser;
		driver =	Webdriver.setBrowsers(BROWSERS.valueOf(browser.toUpperCase()));

		TestSetup.testcontext.set(new TestContext());
		TestSetup.testcontext.get().setWebDriver(driver);
		Webdriver.maximizeWindow(driver);
		Webdriver.navigatetoUrl(driver,"https://www.yotube.com/");
		Google google = new Google();
		google.enterSearchItem("Top ten movies of all time")
		.clickGoogleSearch()
		.clickLink()
		.addmoviesDetails();

	}
	

	@Override
	public String getTestName() {
		return testName.get();
	}


	@BeforeMethod(alwaysRun=true)
	public void BeforeMethod(Method method, Object[] testData){

		String[] test =   (String[])testData[0];
		testName.set(method.getName()+"-"+test[0]);
		

	}



	@Test(dataProvider = "movies")
	public void test(String[] data) {
		
		if(!data[0].equalsIgnoreCase(AppConstants.movies.get(0))) {
			WebDriver	driver =	Webdriver.setBrowsers(BROWSERS.valueOf(browser.toUpperCase()));

			TestSetup.testcontext.set(new TestContext());
			TestSetup.testcontext.get().setWebDriver(driver);
			Webdriver.maximizeWindow(driver);
			Webdriver.navigatetoUrl(driver,data[2]);

			TestSetup.testcontext.get().setFilmNames(data[0]);
			TestSetup.testcontext.get().setFilemDirectors(data[1]);
			System.out.println(data[0]);

			moviePage mp = new moviePage();
			mp.verifyMoviename(data[0])
			.verifyDirectorname(data[1]);


		}else {

			WebDriver driver = Webdriver.setMobileDriver();
			TestSetup.testcontext.set(new TestContext());
			TestSetup.testcontext.get().setWebDriver(driver);
			Webdriver.navigatetoUrl(driver,data[2]);
			
			TestSetup.testcontext.get().setFilmNames(data[0]);
			TestSetup.testcontext.get().setFilemDirectors(data[1]);
			System.out.println(data[0]);
			
			m_moviePage mp = new m_moviePage();
			mp.verifyMoviename(data[0])
			.verifyDirectorname(data[1]);
			
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

	@DataProvider(name = "movies" , parallel =true)
	public Object[][] movieProvider() {
		try {
			
			Object [][] objArray = new Object[4][3];
			for(int i=0;i< 4;i++){

				objArray[i][0] = AppConstants.movies.get(i);
				objArray[i][1] = AppConstants.directors.get(i);
				objArray[i][2]=AppConstants.links.get(i);
			} 

			return objArray;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	


}
