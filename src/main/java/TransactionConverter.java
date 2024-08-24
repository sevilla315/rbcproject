import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TransactionConverter implements MsgConverter<String, Transaction> {

    Logger log = Logger.getLogger(TransactionConverter.class);

    public Transaction convert(String csv) {
        if (csv == null) return null;
        // split each line into tokens separated by ","
        String [] tokens = csv.split(",");
        // must have at least 3 tokens
        if (tokens.length < 3) {
            return null;
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime dateTime = LocalTime.parse(tokens[0], formatter);
                double amount = Double.parseDouble(tokens[1]);
                return new Transaction(dateTime, amount, tokens[2]);
            } catch (Exception e) {
                log.error("Failed to parse transaction", e);
                return null;
            }
        }
    }

}
