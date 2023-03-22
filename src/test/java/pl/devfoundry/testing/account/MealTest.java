package pl.devfoundry.testing.account;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.devfoundry.testing.account.Meal;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class MealTest {



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
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pizza");

        //then
        assertEquals(meal1, meal2, "Checking if two meals are equal");
        assertThat(meal1, equalTo(meal2));

    }

   @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice (){
        //given

       Meal meal = new Meal(8, "soup");
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
       List<String> cakeNames = Arrays.asList("Chesecake", "FruitCake", "Cupcace");
       return cakeNames.stream();
   }
}
