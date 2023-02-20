
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {

    public String refactor(int amount, String debit) {
        String time = new Date().toString();
        SimpleDateFormat oldFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");
        try {
            Date oldFormatTime = oldFormat.parse(time);
            SimpleDateFormat newFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
            String formatedDate = newFormat.format(oldFormatTime);
            return formatedDate + " " + amount + " " + debit;
        } catch (ParseException e) {
            e.getMessage();
            throw new RuntimeException();
        }
    }

    public ArrayList<String> refactor(ArrayList<String> transactions) {
        ArrayList<String> strings = new ArrayList<>();
        for (String transaction : transactions) {
            int dividend;
            if (transaction.contains("PM")) {
                dividend = transaction.indexOf("PM");
            } else {
                dividend = transaction.indexOf("AM");
            }
            String time = transaction.substring(0, dividend + 2);
            SimpleDateFormat oldFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
            try {
                Date oldFormatTime = oldFormat.parse(time);
                SimpleDateFormat newFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
                strings.add(newFormat.format(oldFormatTime) + transaction.substring(dividend + 2));
            } catch (ParseException e) {
                e.getMessage();
                throw new RuntimeException();
            }
        }
        return strings;
    }
}
