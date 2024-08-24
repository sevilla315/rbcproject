import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class TransactionReader {

    private AccountService accountService;
    private TransactionConverter transactionConverter;

    public AccountService getAccountService() {
        return accountService;
    }

    public TransactionConverter getTransactionConverter() {
        return transactionConverter;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setTransactionConverter(TransactionConverter transactionConverter) {
        this.transactionConverter = transactionConverter;
    }

    public void readFile(String fileName) throws IOException, URISyntaxException {
        // convert each line from the transaction file into a Transaction, ignoring failures,
        // and send them to the account service
        Files.readAllLines(
                Paths.get(this.getClass().getResource(fileName).toURI()), Charset.defaultCharset())
                .stream()
                .map(l -> transactionConverter.convert(l))
                .filter(Objects::nonNull)
                .forEach(t -> accountService.onTransaction(t));
    }
}
