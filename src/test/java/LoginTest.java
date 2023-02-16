import io.qameta.allure.Step;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.AccountPage;
import pages.CustomerPage;
import pages.LoginPage;
import pages.TransactionPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class LoginTest {

    public static AccountPage accountPage;
    public static CustomerPage customerPage;
    public static LoginPage loginPage;
    public static TransactionPage transactionPage;
    public static WebDriver webDriver;
    public static ChromeOptions chromeOptions;
    public static ReportWriter reportWriter;
    
    @Step
    public static void balanceCheck(int expected, int balance){
    Assert.assertEquals(expected, balance);
    }

    @BeforeClass
    public static void configure() throws MalformedURLException {
        chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserName", "chrome");
        chromeOptions.setCapability("platformName", "LINUX");
        chromeOptions.setCapability("se:name", "LoginTest");
        chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value");

        webDriver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get(ConfigProperties.getProperties("loginpage"));

        accountPage = new AccountPage(webDriver);
        customerPage = new CustomerPage(webDriver);
        loginPage = new LoginPage(webDriver);
        transactionPage = new TransactionPage(webDriver);

        reportWriter = new ReportWriter();
    }

    @Test
    public void loginTest() throws InterruptedException {
        loginPage.onClickCustomerButton();
        customerPage.inputLogin(ConfigProperties.getProperties("login"));
        customerPage.onClickLoginButton();
        accountPage.commitDeposit();
        accountPage.commitWithdraw();
        balanceCheck(0, accountPage.getBalance());
        accountPage.getTransactions();
        transactionPage.test();
    }

    @AfterClass
    public static void tearDown(){
        webDriver.quit();
    }

}
