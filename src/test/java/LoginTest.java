import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AccountPage;
import pages.CustomerPage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;


public class LoginTest {

    public static AccountPage accountPage;
    public static CustomerPage customerPage;
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
        customerPage = new CustomerPage(webDriver);
        loginPage = new LoginPage(webDriver);
    }

    @Test
    public void loginTest() throws InterruptedException {
        loginPage.onClickCustomerButton();
        customerPage.inputLogin(ConfigProperties.getProperties("login"));
        customerPage.onClickLoginButton();
        String user = accountPage.getUsername();
        accountPage.getBalance();
        accountPage.commitDeposit();
        accountPage.getBalance();
        accountPage.commitWithdraw();
        accountPage.getBalance();
        accountPage.getTransactions();
        Assert.assertEquals(ConfigProperties.getProperties("login"), user);
    }

    @AfterClass
    public static void tearDown(){
        webDriver.quit();
    }

}
