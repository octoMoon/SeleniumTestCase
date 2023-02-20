package pages;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

public class TransactionPage {

    @FindBy(xpath = "//*[@id='anchor0']")
    private WebElement debit;
    @FindBy(xpath = "//*[@id='anchor1']")
    private WebElement credit;
    @FindBy(css = "button.btn-lg:nth-child(1)")
    private WebElement transactionMenuButton;

    public WebDriver webDriver;
    public FluentWait wait;

    public TransactionPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public ArrayList<String> getTransactions() {
        transactionMenuButton.click();
        ArrayList<String> list = new ArrayList<>();
        list.add(debit.getText());
        list.add(credit.getText());
        return list;
    }

}
