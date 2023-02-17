package pages;

import java.time.Duration;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransactionPage {

    @FindBy(xpath = "//*[@id='anchor0']")
    private WebElement debit;
    @FindBy(xpath = "//*[@id='anchor1']")
    private WebElement credit;

    public WebDriver webDriver;
    public WebDriverWait wait;

    public TransactionPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(4));

    }

    public ArrayList<String> getTransactions() {
        webDriver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/listTx");
        webDriver.navigate().refresh();
        ArrayList<String> list = new ArrayList<>();
        list.add(debit.getText());
        list.add(credit.getText());
        return list;
    }

}
