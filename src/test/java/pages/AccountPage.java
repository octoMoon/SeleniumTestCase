package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;


public class AccountPage {
    @FindBy(xpath = "//html/body/div/div/div[2]/div/div[3]/button[2]")
    private WebElement depositMenuButton;
    @FindBy(xpath = "//html/body/div/div/div[2]/div/div[3]/button[3]")
    private WebElement withdrawMenuButton;
    @FindBy(xpath = "//html/body/div/div/div[2]/div/div[3]/button[1]")
    private WebElement transactionMenuButton;
    @FindBy(xpath = "//html/body/div/div/div[2]/div/div[4]/div/form/button")
    private WebElement enterButton;
    @FindBy(xpath = "//html/body/div/div/div[2]/div/div[4]/div/form/div/input")
    private WebElement amountField;
    @FindBy(xpath = "//html/body/div/div/div[2]/div/div[2]/strong[2]")
    private WebElement balance;
    @FindBy(xpath = "//html/body/div/div/div[2]/div/div[2]/table")
    private WebElement transactions;

    public WebDriver webDriver;

    public AccountPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public int getBalance(){
        return Integer.parseInt(balance.getText());
    }

    public String nowDateToFibonacci() {
        int dayOfMonth = LocalDate.now().getDayOfMonth() + 1;
        int result = 1;
        int n1 = 1;
        int n2;
        for (int i = 3; i <= dayOfMonth; i++) {
            n2 = result + n1;
            result = n1;
            n1 = n2;
        }
        return Integer.toString(result);
    }

    public void amountEnter (String amount){
        amountField.sendKeys(amount);
    }

    public void onClickWithdrawMenu(){
        withdrawMenuButton.click();
    }

    public void onClickTransactionMenu(){
        transactionMenuButton.click();
    }

    public void commitDeposit(String amount){
        depositMenuButton.click();
        amountEnter(amount);
        enterButton.click();
    }

    public void commitWithdraw(String amount) throws InterruptedException {
        withdrawMenuButton.click();
        Thread.sleep(5000);
        amountEnter(amount);
        enterButton.click();
    }

    public void getTransactions()throws InterruptedException{
                Thread.sleep(5000);

        transactionMenuButton.click();
    }








}
