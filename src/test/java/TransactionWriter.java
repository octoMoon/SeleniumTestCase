
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionWriter {

    public String transactionWrite(String amount, String debit) throws ParseException {
        String time = new Date().toString();
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");
        Date d = format.parse(time);
        SimpleDateFormat newFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        String formatedDate = newFormat.format(d);
        return formatedDate +" "+ amount +" "+ debit;
    }

}
