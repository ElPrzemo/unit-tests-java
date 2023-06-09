package pl.devfoundry.testing;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.function.Executable;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.devfoundry.testing.extensions.IAExceptionIgnoreExtension;
import pl.devfoundry.testing.order.Order;

class MealTest {

    @Spy
    private Meal mealSpy;

    @Test
    void shouldReturnDiscountedPrice() {

        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28, discountedPrice);
        assertThat(discountedPrice, equalTo(28));

    }

    @Test
    void referencesToTheSameObjectShouldBeEqual() {

        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

        //then
        assertSame(meal1, meal2);
        assertThat(meal1, sameInstance(meal2));

    }

    @Test
    void referencesToDifferentObjectsShouldNotBeEqual() {

        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);

        //then
        assertNotSame(meal1, meal2);
        assertThat(meal1, not(sameInstance(meal2)));

    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {

        //given
        Meal meal1 = new Meal();
        Meal meal2 = new Meal();

        //then
        assertEquals(meal1, meal2, "Checking if two meals are equal");
        assertThat(meal1, equalTo(meal2));

    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToTheOrder(){
        //given
        Meal meal1 = new Meal();
        Meal meal2= new Meal();

        //when
        Order order = new Order();
        order.addMealToOOrder(meal1);
        order.addMealToOOrder(meal2);

        //then
        assertThat(order.getMeals().get(0), is(meal1));
        assertThat(order.getMeals().get(1), is(meal2));
    }

   @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice (){
        //given

       Meal meal = new Meal();
       //when
       //then
       assertThrows(IllegalArgumentException.class, ()->meal.getDiscountedPrice(9));

   }


   @ParameterizedTest
    @ValueSource(ints = {5,10,15,18})
    void mealPricesShouldBeLowerThan20 (int price){
        assertThat(price, lessThan(20));
   };


   @ParameterizedTest
   @MethodSource("createMealsWithNameAndPrice")
   void burgerShouldHaveCorrectNameAndPrice (String name, int price) {
       assertThat(name, containsString("burger"));
       assertThat(price, greaterThanOrEqualTo(10));

   }

   private static Stream<Arguments> createMealsWithNameAndPrice(){
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Chesburger", 12)
        );
   }

   @ParameterizedTest
   @MethodSource("createCakeNames")
   void cakeNamesShouldEndWithCake (String name) {
       assertThat(name, notNullValue());
       assertThat(name, endsWith("cake"));
   }

   private static Stream <String> createCakeNames(){
       List<String> cakeNames = Arrays.asList("Chesecake", "Fruitcake", "Cupcake");
       return cakeNames.stream();
   }

    @ExtendWith(IAExceptionIgnoreExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {1,3,7,8})
    void mealPricesShouldBeLowerThan10 (int price){
        if (price>5){
            throw new IllegalArgumentException();
        }

        assertThat(price, lessThan(20));
    };

   @TestFactory
    Collection<DynamicTest> dynamicTestCollection(){
       return Arrays.asList(
               dynamicTest("Dynamic test 2", ()->assertThat(5, lessThan(6))),
               dynamicTest("Dynamic test 2", ()->assertEquals(4, 2*2))
       );
   }

   @Tag("fries")
   @TestFactory
   Collection<DynamicTest> calculateMealPrices() {
       Order order = new Order();
       order.addMealToOOrder(new Meal());
       order.addMealToOOrder(new Meal());
       order.addMealToOOrder(new Meal());

       Collection<DynamicTest> dynamicTests = new ArrayList<>();

       for(int i = 0; i < order.getMeals().size(); i++) {

           int price = order.getMeals().get(i).getPrice();
           int quantity = order.getMeals().get(i).getQuantity();

           Executable executable = () -> {
               assertThat(calculatePrice(price, quantity), lessThan(67));
           };

           String name = "Test name: " + i;
           DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
           dynamicTests.add(dynamicTest);
       }

       return dynamicTests;
   }
    private int calculatePrice (int price, int quantity){
    return price *quantity;
   }

   @Test
    void cancelingOrderShouldRemoveAllItemsFromMealList() {
       //given
       Meal meal1 = new Meal();
       Meal meal2 = new Meal();

       //when
       Order order = new Order();
       order.addMealToOOrder(meal1);
       order.addMealToOOrder(meal2);
       order.cancel();

       //then
       assertThat(order.getMeals().size(), is(0));
   }

   @Test
    void testMealSumPrice(){
       //given

       Meal meal = mock(Meal.class);

       given(meal.getPrice()).willReturn(15);
       given(meal.getQuantity()).willReturn(3);
       given(meal.sumPrice()).willCallRealMethod();

       //when
       int result = meal.sumPrice();

       //then
       assertThat(result, equalTo(45));

   }

    @Test
    @ExtendWith(MockitoExtension.class)
    void testMealSumPriceWithSpy(){
        //given

        Meal meal = spy(Meal.class);

        given(mealSpy.getPrice()).willReturn(15);
        given(mealSpy.getQuantity()).willReturn(3);


        //when
        int result = mealSpy.sumPrice();

        //then
        then(mealSpy).should().getPrice();
        then(mealSpy).should().getQuantity();
        assertThat(result, equalTo(45));

    }








}
