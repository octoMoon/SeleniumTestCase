import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class LoginTest {

    private void configure(){
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "/home/vladmir/ChromeDriver");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login;");
    }
    
}
