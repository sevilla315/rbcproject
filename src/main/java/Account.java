import java.util.Objects;
import java.util.concurrent.atomic.DoubleAdder;

public class Account {
    private String accountId;
    private DoubleAdder balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountId);
    }

    public Account(String accountId, DoubleAdder balance) {
        this.accountId = accountId;
        this.balance = balance;
    }
}
