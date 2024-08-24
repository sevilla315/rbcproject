import java.time.LocalTime;

public class AccountService implements TransactionListener {

    private static final double THRESHOLD = 50000D;

    private IAlertService alertService;

    private AccountPersistence accountPersistence;

    private final Object windowLock = new Object();

    // start time of the window
    private volatile LocalTime windowStart;

    public IAlertService getAlertService() {
        return alertService;
    }

    public AccountPersistence getAccountPersistence() {
        return accountPersistence;
    }

    public void setAlertService(IAlertService alertService) {
        this.alertService = alertService;
    }

    public void setAccountPersistence(AccountPersistence accountPersistence) {
        this.accountPersistence = accountPersistence;
    }

    @Override
    public void onTransaction(Transaction txn) {
        synchronized (windowLock) {
            if (windowStart == null || txn.getTxnDate().isAfter(windowStart.plusMinutes(1))) {
                // start new window from txn date
                windowStart = txn.getTxnDate();
                // clear down all running balances for a new window of 1 minute
                accountPersistence.clear();
            }
        }
        // update/create a balance with the txn accountId
        accountPersistence.createOrUpdate(txn.getAccountId(), Math.abs(txn.getAmount()));
        // if balance exceeded threshold, generate an alert
        if (THRESHOLD < accountPersistence.get(txn.getAccountId())) {
            alertService.raiseAlert(IAlertService.SEVERITY_ERROR,
                    String.format("Account %s breached limit of %f with balance %f", txn.getAccountId(), THRESHOLD,
                            accountPersistence.get(txn.getAccountId())));
        }
    }
}
