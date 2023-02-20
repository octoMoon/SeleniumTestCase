
import org.junit.AfterClass;
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
import java.time.Duration;
import java.util.ArrayList;

public class LoginTest {

    public static AccountPage accountPage;
    public static CustomerPage customerPage;
    public static LoginPage loginPage;
    public static TransactionPage transactionPage;
    public static WebDriver webDriver;
    public static ChromeOptions chromeOptions;
    public static ReportWriter reportWriter;
    public static Transaction transaction;
    public static TestingStep testingStep;
    private static ArrayList<String> expectedList;
    private static ArrayList<String> transactionList;

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
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10))
                    .scriptTimeout(Duration.ofSeconds(10))
                    .implicitlyWait(Duration.ofSeconds(10));
            webDriver.get(ConfigProperties.getProperties("loginpage"));
        } catch (MalformedURLException e) {
            e.getMessage();
        }

        accountPage = new AccountPage(webDriver);
        customerPage = new CustomerPage(webDriver);
        loginPage = new LoginPage(webDriver);
        transactionPage = new TransactionPage(webDriver);
        reportWriter = new ReportWriter();
        testingStep = new TestingStep();
        transaction = new Transaction();
        expectedList = new ArrayList<>();
    }

    @Test
    public void loginTest() {
        int amount = accountPage.nowDateToFibonacci();
        loginPage.onClickCustomerButton();
        customerPage.inputLogin(ConfigProperties.getProperties("login"));
        customerPage.onClickLoginButton();
        accountPage.commitDeposit(amount);
        expectedList.add(transaction.refactor(amount, "Credit"));
        testingStep.pageLoading();
        accountPage.commitWithdraw(amount);
        expectedList.add(transaction.refactor(amount, "Debit"));
        testingStep.balanceCheck(0, accountPage.getBalance());
        testingStep.pageLoading();
        transactionList = transaction.refactor(transactionPage.getTransactions());
        testingStep.transactionCheck(transactionList, expectedList);
        reportWriter.csvReport(transactionList);
        testingStep.getReportToAllure();
    }

    @AfterClass
    public static void tearDown() {
        webDriver.quit();
    }
}
