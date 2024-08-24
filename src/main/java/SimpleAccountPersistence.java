import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.DoubleAdder;

public class SimpleAccountPersistence implements AccountPersistence{

    // simple memory based account persistence, can be any medium

    private final int capacity;
    private final Map<String, DoubleAdder> acctWithBalance;

    public SimpleAccountPersistence(int capacity) {
        this.capacity = capacity;
        acctWithBalance = new ConcurrentHashMap<>(capacity);
    }

    @Override
    public void createOrUpdate(String accountId, double amount) {
        acctWithBalance.computeIfAbsent(accountId, k -> new DoubleAdder()).add(Math.abs(amount));
    }

    @Override
    public void clear() {
        acctWithBalance.clear();
    }

    @Override
    public Double get(String accountId) {
        return acctWithBalance.getOrDefault(accountId, new DoubleAdder()).doubleValue();
    }
}
