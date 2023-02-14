import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {
///html/body/div/div/div[2]/div/form/div/select/option[3]
    @FindBy(xpath = "//html/body/div/div/div[2]/div/form/div/select/option[3]")
    private WebElement username;

    public WebDriver webDriver;

    public AccountPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public String getUsername(){
        System.out.println(username.getText());
        return username.getText();
    }
}
