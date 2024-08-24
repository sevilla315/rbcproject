public interface AccountPersistence {

    void createOrUpdate(String accountId, double amount);

    void clear();

    Double get(String accountId);
}
