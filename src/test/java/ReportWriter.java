import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportWriter {

    public File csvReport(ArrayList<String> data) throws ParseException {
        File report = new File("report.csv");
        try {
            PrintWriter out = new PrintWriter(report);
            out.println(refactorTransaction(data.get(0).toString()));
            out.println(refactorTransaction(data.get(1).toString()));
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return report;
    }
    
    public String refactorTransaction(String transaction) throws ParseException{
         String str = "Feb 16, 2023 1:10:36 PM 987 Credit";
        int dividend;
        if (str.contains(" PM ")) {
            dividend = str.indexOf(" PM ");
        } else {
            dividend = str.indexOf(" AM ");
        }
        String date = transaction.substring(0, dividend);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
        Date currentdate = sdf.parse(date);
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        return sdf2.format(currentdate) + str.substring(dividend + 3);
    }

}
