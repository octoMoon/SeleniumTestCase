
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.Assert;

public class TestingStep {

    @Step
    public void balanceCheck(int expected, int balance) {
        Assert.assertEquals(expected, balance);
    }

    @Step
    public void transactionCheck(ArrayList<String> expected, ArrayList<String> actuals) {
        Assert.assertEquals(expected, actuals);
    }

    @Attachment
    public byte[] getReportToAllure() {
        try {
            return Files.readAllBytes(Paths.get("src/test/resources", "report.csv"));
        } catch (IOException e) {
            e.getMessage();
            throw new RuntimeException();
        }
    }

    public void pageLoading() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }
}
