package WebPages;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import TestBase.JsonOperation;
import TestBase.TestSetup;
import TestBase.WebServices;

public class youtube {

	WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(name="search_query")
	WebElement searchItem;

	@FindBy(id= "search-icon-legacy")
	WebElement click;
	
	@FindBy(linkText="STeP-IN Forum")
	WebElement channel;
	
	@FindBy(xpath= "//div[(@class='tab-content style-scope paper-tab') and contains(., 'Videos') ]")
	WebElement videos;
	
	@FindAll(value = { @FindBy(xpath="//a[@id='video-title']") })
	List<WebElement> videoTitles;
	
	@FindAll(value = { @FindBy(xpath="//span[@id='video-title']") })
	List<WebElement> upcomingVideos;
	
	
	@FindBy(xpath = "//div[@class='ytp-menuitem-label' and text()='Quality']")
	WebElement qualityButton;
	
	@FindBy(xpath = "//div[@class='ytp-button ytp-settings-button' and text()='360p']")
	WebElement three60;
	
	@FindBy(xpath = "//button[@class='ytp-button ytp-settings-button']" )
	WebElement settingButton;
	
	//wait.until(ExpectedConditions.visibilityOf(searchItem))
	
	
	public youtube() {
		this.driver = TestSetup.testcontext.get().getWebDriver();
		wait= new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}
	
	public youtube enterSearchItem(String search) {
		
		  String expected = "Also known as split testing";
		  wait.until(ExpectedConditions.visibilityOf(searchItem)).sendKeys(search);
		  Reporter.log("Entered Search Item");
		  wait.until(ExpectedConditions.visibilityOf(click)).click();
		  return this;
	}
	
	public youtube clickChannel() {
		
		 try {
			wait.until(ExpectedConditions.visibilityOf(channel)).click();
			 Reporter.log("Clicked on the required Channel");
		} catch (Exception e) {
			Assert.fail("Unable to click on channel",e);
		}
		return this;
	}
	
	public youtube navigateVideosTab() {
		
		 try {
			wait.until(ExpectedConditions.visibilityOf(videos)).click();
			 Reporter.log("Clicked on the videos in channel");
		} catch (Exception e) {
			Assert.fail("Unable to navigate to videos in channel",e);
		}
		return this;
	}


	public youtube getrandomVideofromApi() {
		
		try {
			String video = WebServices.fetchvideo();
			TestSetup.testcontext.get().setRandomVideo(video);
			Reporter.log("Video Name to search is "+ video);
		} catch (Exception e) {
			Assert.fail("Unable to get the random video name from API call",e);
		}
		return this;
		
	}

	public youtube getUpcomingVideos() {
		
		try {
			String actualVideoTitle="";
			//wait.until(ExpectedConditions.visibilityOfAllElements(upcomingVideos));
			Thread.sleep(4000);
			List<WebElement> getVideoTitles=driver.findElements(By.xpath("//span[@id='video-title']"));
			Iterator<WebElement> items = getVideoTitles.iterator();
      
			List<String> list = new ArrayList<>();
      
			 while(items.hasNext()) {
			    WebElement getVideoTitle = items.next();
			    actualVideoTitle =  getVideoTitle.getText();
			   // Reporter.log(actualVideoTitle);
			    JavascriptExecutor js = (JavascriptExecutor) driver;
			  
			    js.executeScript("arguments[0].scrollIntoView();", getVideoTitle);
			 
			    list.add(actualVideoTitle);
			}
			 TestSetup.testcontext.get().setUpcomingVideos(list);
		} catch (Exception e) {
			Assert.fail("Unable tp get upcoming videos name",e);
		}
		return this;
	}
	public youtube prepareJson(String suggestions) {
		
		
		
		return this;
		
		
	}

	public youtube uploadFile() {
		
		try {
			String json = JsonOperation.createJsonString(TestSetup.testcontext.get().getRandomVideo(), TestSetup.testcontext.get().getUpcomingVideos());
			String path = JsonOperation.writetofile(json);
			String response = WebServices.post_Multipart("http://54.169.34.162:5252/upload",path).
			then().assertThat().statusCode(200).extract().body().asString();
			TestSetup.testcontext.get().setResponse(response);
		} catch (Exception e) {
			Assert.fail("Unable to upload file",e);
		}
		return this;
	}
	
	public void validateUploadedFile() {
		
		try {
			String response = WebServices.get_Response("http://54.169.34.162:5252/result/"+TestSetup.testcontext.get().getResponse())
					.then().assertThat().statusCode(200).extract().body().asString();
			Reporter.log(" Validated the File");
		} catch (Exception e) {
			Assert.fail("Unable to validate the file",e);
		}
		
	}

	public youtube scrolltoTheVideo() {

		try {
			String expectedVideoTitle=TestSetup.testcontext.get().getRandomVideo();	
			String xpaths = "//a[@id='video-title' and contains(normalize-space(.),'{video}')]".replace("{video}", expectedVideoTitle);
			String actualVideoTitle="";
			Thread.sleep(2000);
			//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(videoTitles));		
			List<WebElement> getVideoTitles=driver.findElements(By.xpath("//a[@id='video-title']"));
			Iterator<WebElement> i = getVideoTitles.iterator();
			
			while(i.hasNext()) {
				WebElement getVideoTitle = i.next();
				actualVideoTitle=getVideoTitle.getText();
			//	Reporter.log(actualVideoTitle);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				
				if(expectedVideoTitle.equalsIgnoreCase(actualVideoTitle))
				{
					js.executeScript("arguments[0].scrollIntoView();", getVideoTitle);
					break;
				}else {
					js.executeScript("arguments[0].scrollIntoView();", getVideoTitle);
					Thread.sleep(2000);
				}
			}
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath(xpaths)));
			Reporter.log("Successfully scrolled to Video");
		} catch (Exception e) {
			Assert.fail("Unable to scroll to video",e);
		}
		return this;
	}

	public youtube captureScreenShot() {
		
		WebDriver webDriverAttribute = (WebDriver)TestSetup.testcontext.get().getWebDriver();
		
		
		File scrFile = ((TakesScreenshot)webDriverAttribute).getScreenshotAs(OutputType.FILE);
		try {
		
	//		String reportDirectory = new File(System.getProperty("user.dir"))+File.separator+"src"+File.separator+"test"+File.separator+"resources";
			
			File destFile = new File(System.getProperty("user.dir")+File.separator+"capture.png");
			FileUtils.copyFile(scrFile, destFile);
			Reporter.log("Captured the screenshot in path"+ destFile.getAbsolutePath() );

		} catch (Exception e) {
			Assert.fail("Unable to capture screenShot",e);
		}
		return this;
		
	}
	
	public youtube clickonVideoInGrid() {
		
		
		try {
		//	Thread.sleep(5000);
			String video = TestSetup.testcontext.get().getRandomVideo();
			String xpaths = "//a[@id='video-title' and contains(normalize-space(.),'{video}')]".replace("{video}", video);
			
			
			Actions act = new Actions(driver);
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpaths))));
			act.moveToElement(driver.findElement(By.xpath(xpaths))).click().build().perform();
			
			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpaths)))).click();
			Reporter.log("clicked on the video ");
		} catch (Exception e) {
			Assert.fail("Unable to click the video",e);
		}
		
		return this;
		
	}

	public youtube changeVideoQuality() {
		
		
		try {
			
			wait.until(ExpectedConditions.visibilityOf(settingButton)).click();
			
			wait.until(ExpectedConditions.visibilityOf(qualityButton)).click();

			wait.until(ExpectedConditions.visibilityOf(three60)).click();
			
			Reporter.log(" Changed the Qaulity of the video");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail("Unable to change the quality of the video ",e);
		}
		
		return this;
	}
}
