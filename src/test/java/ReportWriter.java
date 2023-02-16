import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ReportWriter {

    public File csvReport(ArrayList<Object> data) {
        File report = new File("report.csv");
        try {
            PrintWriter out = new PrintWriter(report);
            out.println("test case");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return report;
    }

}
