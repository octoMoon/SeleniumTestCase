
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ReportWriter {

    public File csvReport(ArrayList<String> data) {
        File report = new File("src/test/resources/report.csv");
        try {
            PrintWriter out = new PrintWriter(report);
            out.println(data.get(0).toString());
            out.println(data.get(1).toString());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return report;
    }
}
