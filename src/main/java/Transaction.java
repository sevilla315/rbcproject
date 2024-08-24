import java.time.LocalTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {

    private static final AtomicInteger transactionIdSeed = new AtomicInteger();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Double.compare(getAmount(), that.getAmount()) == 0 && Objects.equals(getTxnDate(), that.getTxnDate()) && Objects.equals(getAccountId(), that.getAccountId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTxnDate(), getAmount(), getAccountId());
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
