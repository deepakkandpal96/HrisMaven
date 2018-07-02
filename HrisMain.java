package Hrismaven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HrisMain {
	WebDriver web;
	HrisMaven loginform;
	
	
	 @BeforeClass
	 
	    public void launchBrowser()
	    {
	    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\deepakkandpal\\Desktop\\chromedriver_win32\\chromedriver.exe");
	 	    web = new ChromeDriver();
	        web.get("https://hris.qainfotech.com/login.php");
	        web.findElement(By.className("active")).click();
	        loginform = new HrisMaven(web);
	        
	    }
	
	 @Test(priority=1)
       public void attemptLoginWithIncorrectCredentialsShouldRenderErrorMessage()
	  {
            Assert.assertTrue(loginform.loginWithIncorrectCredentials("gfgtythtsyt", "trfrtttrage").contains("Invalid Login"));
           
      }
	 
	 @Test(priority=2)
	    public void attemptLoginWithNoPasswordShouldAnnotateBlankPasswordField()
	    {
	        loginform.login("deepakkandpal", "");
	        Assert.assertTrue(loginform.isPasswordEntryAnnotated());  
	    }
	    
    
    @Test(priority=3)
    public void attemptLoginWithCorrectCredentialsShouldTakeYouToTimesheetPage()
    {
        Assert.assertTrue(loginform.loginWithCorrectCredentials("deepakkandpal", "Deepak@321#").getDisplay());
    }
    
    @AfterClass
    public void closeBrowser() throws InterruptedException
    {
    	
    	TimeUnit.SECONDS.sleep(4);
        web.quit();
    }
}
