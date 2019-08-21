package mobile;

import java.io.File;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

 

public class mobileRedmi {

 

    public static AppiumDriver<MobileElement> driver;
    
    
    public static void main(String[] args) {
        
        try {
            openCalculator();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            
    }
    
    public static void openCalculator() throws Exception {

    	String chromePath = System.getProperty("user.dir")+File.separator+"Webdrivers"+File.separator+"Mobile"+File.separator+"chromedriver.exe";
    	DesiredCapabilities cap = new DesiredCapabilities();

    	cap.setCapability("browserName", "Chrome");
    	cap.setCapability("deviceName", "Redmi");
    	cap.setCapability("uuid", "355bcc42");
    	cap.setCapability("platformName", "Android");
    	cap.setCapability("platformVersion", "7.1.2");
    	cap.setCapability("noReset", true);


    	System.setProperty("webdriver.chrome.driver",chromePath);
    	URL url = new URL("http://127.0.0.1:4723/wd/hub");
    	driver = new AppiumDriver<MobileElement>(url , cap);
    	driver.get("http://www.google.com");
    	Thread.sleep(4000);
    	String context =        driver.getContext();
    	driver.context(context);
    	boolean found =     driver.findElement(By.xpath("//input[@type='search']")).isDisplayed();
    	driver.findElement(By.name("q")).sendKeys("INdia");
    	System.out.println(found);
    	System.out.println("Application started");

    }
    
}