package pl.devfoundry.testing.account;


import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;




class AccountTest {



    @Test
    void newlyCreatedAccountShouldNotBeActive() {

//given
// when
Account newAccount = new Account();

//then
assertFalse(newAccount.isActive());
//then
assertThat(newAccount.isActive(), equalTo(false));
    }

    @Test

    void newlyCreatedAccountShouldNotHaveDefasultDeliveryAddressSet(){

        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        assertThat(address, nullValue());
    }


    @Test void shouldReturnDiscountedPrice()
    {
        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28, discountedPrice);
        assertThat(discountedPrice, equalTo(28));
    }

    @Test
    void referencesToDifferentObjectShouldNotBeEqual (){
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

        //then
        assertSame(meal1, meal2);
        assertThat(meal1,sameInstance(meal2));
    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame(){
        //given

        Meal meal1 = new Meal(10, "pizza");
        Meal meal2 = new Meal(10, "pizza");

        //then
        assertEquals(meal1, meal2, "checking if two meals are equal");
        assertThat(meal1, not(sameInstance(meal2)));
    }

    @Test
    void defaultDeliveryAdressShouldNotNullAfterBeingSet (){
        //given
        Address address = new Address("Krakowska", "67c");
        Account account = new Account();
        account.setDefaultDeliveryAdress(address);

        //when
        Address defaultAddress = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(defaultAddress);
        assertThat(defaultAddress, is(notNullValue()));
    }

    void newAccountWithNotNullAdressShouldBeActive () {
        //given
        Address address = new Address("Puławska", "46/6");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address != null, ()->{    //asumpcja przyjmuje dwa argumenty, bollen któy musi zostać spełniony i implementacje interfejsu excecutable
            assertTrue(account.isActive());
        });


    }







}