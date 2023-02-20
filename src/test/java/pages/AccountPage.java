package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.LocalDate;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {

    @FindBy(css = "button.btn-lg:nth-child(2)")
    private WebElement depositMenuButton;
    @FindBy(css = "button.btn-lg:nth-child(3)")
    private WebElement withdrawMenuButton;
    @FindBy(css = ".btn-default")
    private WebElement enterButton;
    @FindBy(css = "input")
    private WebElement amountField;
    @FindBy(css = "strong.ng-binding:nth-child(2)")
    private WebElement balance;
    @FindBy(css = ".form-group > label:nth-child(1)")
    private WebElement LoadVer;
    public WebDriver webDriver;
    public WebDriverWait wait;

    public AccountPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(4));
    }

    public int getBalance() {
        return Integer.parseInt(balance.getText());
    }

    public int nowDateToFibonacci() {
        int dayOfMonth = LocalDate.now().getDayOfMonth() + 1;
        int result = 1;
        int n1 = 1;
        int n2;
        for (int i = 3; i <= dayOfMonth; i++) {
            n2 = result + n1;
            result = n1;
            n1 = n2;
        }
        return result;
    }

    public void amountEnter(int amount) {
        String sum = String.valueOf(amount);
        amountField.sendKeys(sum);
    }

    public void commitDeposit(int amount) {
        depositMenuButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(LoadVer, "Amount to be Deposited :"));
        amountEnter(amount);
        enterButton.click();    
    }

    public void commitWithdraw(int amount) {
        withdrawMenuButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(LoadVer, "Amount to be Withdrawn :"));
        amountEnter(amount);
        enterButton.click();
    }
}
