package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactionPage {
    @FindBy(xpath = "//*[@id='anchor0']")
    private WebElement debit;
    @FindBy(xpath = "//*[@id='anchor1']")
    private WebElement credit;

    public WebDriver webDriver;
   //  ReportWriter reportWriter;

    public TransactionPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void test(){
        System.out.println(debit.getText());
        System.out.println(credit.getText());

    }

}
