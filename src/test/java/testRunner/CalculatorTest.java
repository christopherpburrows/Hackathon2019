package testRunner;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;

public class CalculatorTest {

	public static WebDriver driver;
	
	static String PROXY = "165.225.104.34:9480";

	
	public static void main(String[] args) {
	
		try {
		//	getWebDriver();
		//	new CalculatorTest().proxy();
		//new CalculatorTest().mobile();
			openCalculator();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public static void openCalculator() throws Exception {
		
		String chromeDriverPath = "C:\\Users\\310276846\\Downloads\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setCapability("deviceName", "Redmi");
		cap.setCapability("udid", "355bcc42");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "7.1.2");
		/*cap.setCapability("appPackage", "com.android.chrome");
		cap.setCapability("appActivity", "com.google.android.apps.chrome.Main");*/
		cap.setCapability("browserName", "Chrome");
		cap.setCapability("autoGrantPermissions", true);
		cap.setCapability("noReset", true);
		
	
        
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		//driver = new AppiumDriver<MobileElement>(url , cap);
		AppiumDriver<MobileElement> driver = new AppiumDriver<MobileElement> (url, cap);
		String context = driver.getContext(); // = "CHROMIUM"
		driver.context(context);
		/*driver.findElement(By.id("com.android.chrome:id/terms_accept")).click();
		driver.findElement(By.id("com.android.chrome:id/negative_button")).click();
		driver.context(context);*/
		
		driver.get("http://www.google.com");
		System.out.println("Application started");
		Thread.sleep(3000);
		driver.context("WEBVIEW_1");
		driver.findElement(By.xpath("//input[@type='search']" )).sendKeys("Testing");
		driver.findElement(By.xpath("//button[@aria-label='Google Search']")).click();
		
	}
	
	public  void mobile() throws MalformedURLException {
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AppiumDriver<MobileElement>(url , getAndroidCapabilities("chrome", "355bcc42", "7.1.2"));
		
	/*	String context = driver.getContext(); // = "CHROMIUM"
		driver.context("NATIVE_APP");
		driver.findElement(By.id("com.android.chrome:id/terms_accept")).click();
		driver.findElement(By.id("com.android.chrome:id/negative_button")).click();
		driver.context(context);*/
		
		driver.get("http://www.google.com");
		System.out.println("Application started");
		
		
		
	}
	public void proxy() throws MalformedURLException {
		
	/*	org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		proxy.setHttpProxy(PROXY);*/
		     
		
		URL url = new URL("http://localhost:4723/wd/hub");
		
		DesiredCapabilities  capabilities = new DesiredCapabilities();
		capabilities.setCapability("udid", "355bcc42");
		capabilities.setCapability("browserName","Chrome"); 
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.2");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi");
		
		 

		AndroidDriver driver = new AndroidDriver(url, capabilities );
		//driver.get("http://www.yahoo.com");
		driver.get("http://www.google.com");
		System.out.println("Application started");
		String context = driver.getContext();
		System.out.println(context);
		
		
		
		/*capabilities.setCapability(CapabilityType.PROXY, proxy);
		//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi");
		capabilities.setCapability(MobileCapabilityType.UDID, "355bcc42");
	//	cap.setCapability("platformName", "Android");
		//cap.setCapability("platformVersion", "7.1.2");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.2");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("androidPackage", "com.android.chrome");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.get("http://www.yahoo.com");
		*/
	}
	
	public  DesiredCapabilities getAndroidCapabilities(String browser, String device, String version) {
        DesiredCapabilities capabilities = new DesiredCapabilities().android();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
        capabilities.setCapability("fastReset", true);
       /* if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("ignore-certificate-errors");
            options.addArguments("disable-translate");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        }*/
        return capabilities;
    }

		
		public static void getWebDriver() {

			try {

				DesiredCapabilities capabilities = new DesiredCapabilities();
			//	capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("deviceName", "nexus_seven");
				capabilities.setCapability("platformVersion", "7.1.2");
				capabilities.setCapability("appPackage", "com.android.chrome");
				capabilities.setCapability("appActivity", ".Main");

				driver = new RemoteWebDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
				
				driver.getCurrentUrl();
				
				driver.get("http://www.google.com");
				System.out.println("Application started");
				String context = ((AppiumDriver) driver).getContext();
				System.out.println(context);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} 
	 

	}

