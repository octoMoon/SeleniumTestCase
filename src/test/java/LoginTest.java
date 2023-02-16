
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import java.io.IOException;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    public static AccountPage accountPage;
    public static CustomerPage customerPage;
    public static LoginPage loginPage;
    public static TransactionPage transactionPage;
    public static WebDriver webDriver;
    public static ChromeOptions chromeOptions;
    public static ReportWriter reportWriter;
    public static TransactionWriter transactionWriter;

    private static ArrayList<String> expectedList;
    private static ArrayList<String> transactionList;

    @Step
    public static void balanceCheck(int expected, int balance) {
        Assert.assertEquals(expected, balance);
    }

    @Step
    public static void transactionCheck(ArrayList<String> expected, ArrayList<String> actuals) {
        Assert.assertEquals(expected, actuals);
    }

   @Attachment
    public static byte[] getReportToAllure() throws IOException {
        return Files.readAllBytes(Paths.get("src/test/resources", "report.csv"));
    }

    @BeforeClass
    public static void configure() {
        chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserName", "chrome");
        chromeOptions.setCapability("platformName", "LINUX");
        chromeOptions.setCapability("se:name", "LoginTest");
        chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value");

        try {
            webDriver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.get(ConfigProperties.getProperties("loginpage"));
        } catch (MalformedURLException e) {
            e.getMessage();
        }

        accountPage = new AccountPage(webDriver);
        customerPage = new CustomerPage(webDriver);
        loginPage = new LoginPage(webDriver);
        transactionPage = new TransactionPage(webDriver);

        reportWriter = new ReportWriter();
        transactionWriter = new TransactionWriter();

        expectedList = new ArrayList<>();
    }

    @Test
    public void loginTest() throws InterruptedException, ParseException, IOException {
        loginPage.onClickCustomerButton();
        customerPage.inputLogin(ConfigProperties.getProperties("login"));
        customerPage.onClickLoginButton();
        String amount = accountPage.nowDateToFibonacci();
        accountPage.commitDeposit(amount);
        expectedList.add(transactionWriter.transactionWrite(amount, "Credit"));
        accountPage.commitWithdraw(amount);
        expectedList.add(transactionWriter.transactionWrite(amount, "Debit"));
        balanceCheck(0, accountPage.getBalance());
        accountPage.getTransactions();
        transactionList = reportWriter.refactorTransaction(transactionPage.getTransactions());
        transactionCheck(transactionList, expectedList);
        reportWriter.csvReport(transactionList);
        getReportToAllure();
    }

    @AfterClass
    public static void tearDown() {
        webDriver.quit();
    }

}
