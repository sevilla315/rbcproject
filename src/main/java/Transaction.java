import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {

    private final AtomicInteger transactionIdSeed = new AtomicInteger();

    private final String transactionId;
    private final LocalTime txnDate;
    private final double amount;
    private final String accountId;

    public Transaction(LocalTime txnDate, double amount, String accountId) {
        this.transactionId = "TXN_"+ transactionIdSeed.incrementAndGet();
        this.txnDate = txnDate;
        this.amount = amount;
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalTime getTxnDate() {
        return txnDate;
    }

    public double getAmount() {
        return amount;
    }
}
