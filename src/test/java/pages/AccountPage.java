package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;


public class AccountPage {
    @FindBy(xpath = "//html/body/div/div/div[2]/div/form/div/select/option[3]")
    private WebElement username;

    private WebElement depositMenuButton;
    private WebElement withdrawMenuButton;
    private WebElement transactionMenuButton;
    private WebElement pushDepositButton;
    private WebElement pushWithdrawButton;
    private WebElement amount;
    private WebElement balance;
    private WebElement transactions;

    public WebDriver webDriver;

    public AccountPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public String getUsername(){
        return username.getText();
    }

    public int nowDateToFibonacci(){
        int dayOfMonth = LocalDate.now().getDayOfMonth()+1;
        int result = 1;
        int n1 = 1;
        int n2;
        for (int i = 3; i <=dayOfMonth; i++) {
            n2 = result + n1;
            result = n1;
            n1 = n2;
        }
        return result;
    }
}
