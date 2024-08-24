import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionConverterTest extends TestCase {

    TransactionConverter transactionConverter = new TransactionConverter();

    @Test
    public void testInvalidDateReturnsNull() {
        assertNull(transactionConverter.convert("AA-BB-CC,100,1"));
    }

    @Test
    public void testInsufficientFieldsReturnsNull() {
        assertNull(transactionConverter.convert("10:10:00,100"));
    }

    @Test
    public void testInvalidAmountReturnsNull() {
        assertNull(transactionConverter.convert("10:10:00,ABC,1"));
    }

    @Test
    public void testNullStringReturnsNull() {
        assertNull(transactionConverter.convert(null));
    }


}