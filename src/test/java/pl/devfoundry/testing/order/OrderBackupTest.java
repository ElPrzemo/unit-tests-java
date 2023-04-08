package pl.devfoundry.testing.order;

import org.junit.jupiter.api.*;
import pl.devfoundry.testing.Meal;

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

    @Tag("fries")
    @Test
    void backupOrderWithOneMeal() throws IOException {
        //given
        Meal meal = new Meal();
        pl.devfoundry.testing.order.Order order = new Order();
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
