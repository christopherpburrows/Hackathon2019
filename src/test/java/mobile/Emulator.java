package mobile;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Emulator {

 

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
        
        DesiredCapabilities cap = new DesiredCapabilities();
        

 

        cap.setCapability("browserName", "Chrome");
        cap.setCapability("deviceName", "Redmi");
        cap.setCapability("uuid", "355bcc42");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "7.1.2");
        cap.setCapability("noReset", true);
        /*cap.setCapability("appPackage", "com.android.chrome");
        cap.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        //cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        cap.setCapability("autoGrantPermissions", true);
        cap.setCapability("noReset", true);
        cap.setCapability("android:exported", true); */
        
        System.setProperty("webdriver.chrome.driver","C:\\Users\\310143895\\Downloads\\chromedriver.exe");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver<MobileElement>(url , cap);
        driver.get("http://www.youtube.com");
        Thread.sleep(4000);
        String frc = driver.getPageSource();
        String context =        driver.getContext();
        driver.context(context);

 

        driver.findElement(By.xpath("//button[@class='icon-button topbar-menu-button-avatar-button']")).click();
        driver.findElement(By.xpath("//input[@class='searchbox-input title']")).sendKeys("step-in forum\n");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//a[@href='/user/stepinforum']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@href='/user/stepinforum/videos']")).click();
        
        Thread.sleep(5000);
        String actualVideoTitle="";
         List<MobileElement> getVideoTitles=driver.findElements(By.xpath("//h4[@class='compact-media-item-headline']"));
            Iterator<MobileElement> items = getVideoTitles.iterator();
           
            List<String> list = new ArrayList<>();
           
             while(items.hasNext()) {
                WebElement getVideoTitle = items.next();
                actualVideoTitle=getVideoTitle.getText();
                System.out.println(actualVideoTitle);
                JavascriptExecutor js = (JavascriptExecutor) driver;
              
                js.executeScript("arguments[0].scrollIntoView();", getVideoTitle);
             
                list.add(actualVideoTitle);
            }
        
        
        

 

        System.out.println("Application started");
        
    }
    
}
 