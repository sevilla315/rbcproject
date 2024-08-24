import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    AccountService accountService;
    AccountPersistence accountPersistence;
    TransactionConverter transactionConverter;
    TransactionReader transactionReader;

    @Mock
    IAlertService alertService;

    @Before
    public void setup() {
        accountService = new AccountService();
        accountPersistence = new SimpleAccountPersistence(50000);
        transactionConverter = new TransactionConverter();
        transactionReader = new TransactionReader();
        accountService.setAccountPersistence(accountPersistence);
        accountService.setAlertService(alertService);
        transactionReader.setAccountService(accountService);
        transactionReader.setTransactionConverter(transactionConverter);
    }


    @Test
    public void testAlertsGenerated() throws Exception {
        doNothing().when(alertService).raiseAlert(anyInt(), anyString());
        transactionReader.readFile("txn.csv");
        verify(alertService, times(1)).raiseAlert(eq(2), anyString());
    }

    @Test
    public void testNoAlertsGenerated() throws Exception {
        transactionReader.readFile("txn2.csv");
        verify(alertService, times(0)).raiseAlert(eq(2), anyString());
    }

    @Test
    public void testMultipleAlertsGenerated() throws Exception {
        doNothing().when(alertService).raiseAlert(anyInt(), anyString());
        transactionReader.readFile("txn3.csv");
        verify(alertService, times(2)).raiseAlert(eq(2), anyString());
    }



}
