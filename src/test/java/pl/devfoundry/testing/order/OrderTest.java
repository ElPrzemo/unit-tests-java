package pl.devfoundry.testing.order;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.devfoundry.testing.Meal;
import pl.devfoundry.testing.extensions.BeforeAfterExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(BeforeAfterExtension.class)
public class OrderTest {

    private Order order;

    @BeforeEach
    void initializeOrder(){
        System.out.println(" @BeforeEach ");
        order = new Order();
    }

    @AfterEach
    void cleanUp (){
        System.out.println(" @AfterEach ");
        order.cancel();
    }


    @Test
    void testAssertArrayEquals(){
        //given
        int [] ints1 = {1,2,3};
        int [] ints2 = {1,2,3};

        //then
        assertArrayEquals(ints1, ints2);

    }

    @Test
    void mealListShouldBeEmtyAfterCreationOrder(){
        //given


        //then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        MatcherAssert.assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }


    @Test
    void addingMealToOrderShouldIncreaseOrederSize(){
        //given
        Meal meal = new Meal();
        Meal meal2 = new Meal();


        //when
        order.addMealToOOrder(meal);


        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));

        assertThat(order.getMeals().get(0).getPrice(), equalTo(15));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){

        //given
        Meal meal = new Meal();



        //when
        order.addMealToOOrder(meal);
        order.removeMEealFromOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder(){

        //given

        Meal meal1 = new Meal();
        Meal meal2 = new Meal();



        //when

        order.addMealToOOrder(meal1);
        order.addMealToOOrder(meal2);

        //then
        assertThat(order.getMeals(), contains(meal1, meal2));
        assertThat(order.getMeals(), containsInAnyOrder(meal2, meal1));
    }

    @Test
    void testIfTwoMealListAreTheSame (){

        Meal meal1 = new Meal();
        Meal meal2 = new Meal();
        Meal meal3 = new Meal();

        List<Meal> meals1 = Arrays.asList(meal1, meal2);
        List<Meal> meals2 = Arrays.asList(meal1, meal2);

        //then
        assertThat(meals1, is(meals2));

    }

    @Test
    void orderTotalPriceShouldNotExceedsMaxIntValue() {
        //given
        Meal meal1 = new Meal();
        Meal meal2 = new Meal();

        //when
        order.addMealToOOrder(meal1);
        order.addMealToOOrder(meal2);

        //then
        assertThrows(IllegalStateException.class, () -> order.totalPrice());
    }

    @Test
    void emptyOrderTotalPriceShouldEqualZero(){
        //given
        //order is created in BeforeEach
        //then
        assertThat(order.totalPrice(), is(0));
    }
}
