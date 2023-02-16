
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportWriter {

    public File csvReport(ArrayList<String> data) {
        File report = new File("report.csv");
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

    public ArrayList<String> refactorTransaction(ArrayList<String> transactions) {
        ArrayList<String> strings = new ArrayList<>();

        for (String transaction : transactions) {
            int dividend;
            if (transaction.contains("PM")) {
                dividend = transaction.indexOf("PM");
            } else {
                dividend = transaction.indexOf("AM");
            }
            
            String date = transaction.substring(0, dividend + 2);
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
            try {
                Date currentdate = sdf.parse(date);
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");                
                strings.add(sdf2.format(currentdate) +  transaction.substring(dividend+2));
            } catch (ParseException e) {
                e.getMessage();
            }

        }
        return strings;

    }

}
