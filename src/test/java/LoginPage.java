import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    ////*[@id="userSelect"]
    @FindBy(xpath = "//*[@id='userSelect']")
    private WebElement loginUsername;

    @FindBy(xpath = "//*[contains(text(), 'Login')]")
    private WebElement loginButton;

    public WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void inputLogin(String login){
        loginUsername.sendKeys(login);
    }

    public void onClickLoginButton(){
        loginButton.click();
    }
}
