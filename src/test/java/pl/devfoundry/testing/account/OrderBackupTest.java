package pl.devfoundry.testing.account;

import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class OrderBackupTest {

    private static OrderBackup orderBackup;

    @BeforeAll
    static void setup () throws FileNotFoundException{
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @BeforeEach
    void appendAtStartOfTheLine() throws IOException{
        orderBackup.getWriter().append("New order: ");
    }

    @AfterEach
    void appentAtTheEndOfTheLine () throws IOException{
        orderBackup.getWriter().append(" backed up. ");
    }

    @Test
    void backupOrderWithOneMeal() throws IOException {
        //given
        Meal meal = new Meal(7, "Fries");
        Order order = new Order();
        order.addMealToOOrder(meal);

        //when
        orderBackup.backuporder(order);

        //then
        System.out.println("Order: " + order.toString() + " backed up. ");


    }


    @AfterAll
    static void tearDown() throws IOException{
        orderBackup.closeFile();
    }
}
