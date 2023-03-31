package pl.devfoundry.testing;

import org.junit.jupiter.api.Test;
import pl.devfoundry.testing.order.Order;
import pl.devfoundry.testing.order.OrderBackup;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExcecutionTest {

    @Test
    void callingBackupWithoutCreatingFileFirstShouldThrownException() throws IOException {
        //given
        OrderBackup orderBackup = new OrderBackup();

        //then
        assertThrows(IOException.class, orderBackup.backuporder(new Order())
        );
    }
}
