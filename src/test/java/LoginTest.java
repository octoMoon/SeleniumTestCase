import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class LoginTest {

    public static AccountPage accountPage;
    public static LoginPage loginPage;
    public static WebDriver webDriver;

    @BeforeClass
    public static void configure(){
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getProperties("chromedriver"));
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get(ConfigProperties.getProperties("loginpage"));

        accountPage = new AccountPage(webDriver);
        loginPage = new LoginPage(webDriver);
    }

    @Test
    public void loginTest(){
        loginPage.inputLogin(ConfigProperties.getProperties("login"));
        loginPage.onClickLoginButton();
        String user = accountPage.getUsername();
        Assert.assertEquals(ConfigProperties.getProperties("login"), user);
    }

    @AfterClass
    public static void tearDown(){
        webDriver.quit();
    }

}
