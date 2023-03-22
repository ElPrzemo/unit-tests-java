package pl.devfoundry.testing.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class CartTest {

    @Test
    @DisplayName("Cart is able to process 1000 orders in 100 ms")
    void SimulateLargeOrder(){
        // given
        Cart cart = new Cart();
        //whem
        //then
        assertTimeout(Duration.ofMillis(10), cart::simluateLargeOrder);
    }

    @Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart(){
        //given
        Order order = new Order();
        Cart cart = new Cart();

        //when
        cart.addOrderToCart(order);

        //then
        assertThat(cart.getOrders(), anyOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
                ));

        assertThat(cart.getOrders(), allOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        //jest jedna różnica pomiędzy powyższą i poniższą metodą,
        // w przypadku gdy w przypadku powyższej np nie zostanie spełniony 2 i 3 warunek, zostanie wypisamny tylko ten pierwszy,
        // w przypadku poniższej metody zostaną wypisane wszystkie warunki;
        assertAll(
                ()-> assertThat(cart.getOrders(), notNullValue()),
                ()->assertThat(cart.getOrders(), hasSize(1)),
                ()->assertThat(cart.getOrders(), is(not(empty()))),
                ()->assertThat(cart.getOrders(), is(not(emptyCollectionOf(Order.class)))),
                ()->assertThat(cart.getOrders().get(0).getMeals(), empty())

);

}}