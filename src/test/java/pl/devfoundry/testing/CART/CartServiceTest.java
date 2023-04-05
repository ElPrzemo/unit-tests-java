package pl.devfoundry.testing.CART;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import pl.devfoundry.testing.order.Order;
import pl.devfoundry.testing.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;


class CartServiceTest {

    private Cart resultCart;

    @Test
    void processCartShouldSendToPrepare(){

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

          //then
        verify(cartHandler).sendToPrepare(cart);
         // then(cartHandler).should().sendToPrepapare(cart);

         // verify(cartHandler, times(1)).sendToPrepapare(cart);
        //verify(cartHandler, atLeastOnce()).sendToPrepapare(cart);

        //InOrder inOrder = inOrder(cartHandler);
        //inOrder.verify(cartHandler).canHandleCart(cart);
       // inOrder.verify(cartHandler).sendToPrepapare(cart);

        // Dwie asercie teraz będą sprawdzać czy to nigdy nie zostało wysłane
        verify(cartHandler, never()).sendToPrepare(cart);
        then(cartHandler).should(never()).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(),equalTo(OrderStatus.PREPARING));

    }

    @Test
    void processCartShouldNotSendToPrepareWithArgumentMachers(){

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(any())).willReturn(true);  // matchera any() zezwoli na zwrócenie metody z dowolną wartością//
                                                //konwencja mówi żeby używać matcherów z bliskich danego typu
                                                //nie wolno mieszać matcherów z prawdziwymi wartościami, albo jedno albo drugie
        //then

        verify(cartHandler).sendToPrepare(cart);

        verify(cartHandler, never()).sendToPrepare(any(Cart.class));   // ten matcher oznacza wszystko z tej klasy
        then(cartHandler).should(never()).sendToPrepare(any(Cart.class));

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(),equalTo(OrderStatus.PREPARING));

    }

    @Test
    void canHandlerCartShouldReturnMultipleValues(){

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);


        given(cartHandler.canHandleCart(cart)).willReturn(true, false, false, true);

        //then

        assertThat(cartHandler.canHandleCart(cart),equalTo(true));
        assertThat(cartHandler.canHandleCart(cart),equalTo(false));
        assertThat(cartHandler.canHandleCart(cart),equalTo(false));
        assertThat(cartHandler.canHandleCart(cart),equalTo(true));
    }

    @Test
    void processCartShouldSendToPrepareWithLambdas(){

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(argThat(cart1 -> cart1.getOrders().size()>02))).willReturn(true);
        // powyższy handler argThat  podstawia lambde
        //when
        Cart resultCart = cartService.processCart(cart);

        //then

        then(cartHandler).should(never()).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(),equalTo(OrderStatus.PREPARING));

    }

    @Test
    void canHandlerCartShouldThrowException (){

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willThrow(IllegalStateException.class);

        //when
        //then

        assertThrows(IllegalStateException.class, ()->cartService.processCart(cart));

    }

    @Test
    void processCartShouldSendToPrepareWithArgumentCaptor(){

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        ArgumentCaptor<Cart> argumentCaptor = ArgumentCaptor.forClass(Cart.class);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //then
      //  verify(cartHandler).sendToPrepare(argumentCaptor.capture());
         then(cartHandler).should().sendToPrepare(argumentCaptor.capture());

         assertThat(argumentCaptor.getValue().getOrders().size(), equalTo(1));
        assertThat(argumentCaptor.getAllValues().get(0).getOrders().size(), equalTo(1));

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(),equalTo(OrderStatus.PREPARING));

    }

    @Test
    void shouldDoNothingWhenProcessCart(){

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        doNothing().when(cartHandler).sendToPrepare(cart);
        willDoNothing().given(cartHandler).sendToPrepare(cart);
        willDoNothing().willThrow(IllegalStateException.class).given(cartHandler).sendToPrepare(cart);
        //then
        verify(cartHandler).sendToPrepare(cart);


        then(cartHandler).should(never()).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(),equalTo(OrderStatus.PREPARING));

    }

    @Test
    void shouldAnswerWhenProcessCart(){

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        doAnswer(invocationOnMock -> {
            Cart argumentCart = invocationOnMock.getArgument(0);
            argumentCart.clearCart();
            return  true;
            }
        ).when(cartHandler).canHandleCart(cart);

        when(cartHandler.canHandleCart(cart)).then(i->{
            Cart argumentCart = i.getArgument(0);
            argumentCart.clearCart();
            return  true;
        });

        willAnswer(invocationOnMock -> {
                    Cart argumentCart = invocationOnMock.getArgument(0);
                    argumentCart.clearCart();
                    return  true;
                }
        ).given(cartHandler).canHandleCart(cart);

        given(cartHandler.canHandleCart(cart)).will(i->{
            Cart argumentCart = i.getArgument(0);
            argumentCart.clearCart();
            return  true;
        });



        //then


        then(cartHandler).should(never()).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().size(),equalTo(0));

    }


    @Test
    void deliveryShouldBeFree(){
        //given
        Cart cart = new Cart();
        cart.addOrderToCart(new Order());
        cart.addOrderToCart(new Order());
        cart.addOrderToCart(new Order());

        CartHandler cartHandler = mock(CartHandler.class);
        doCallRealMethod().when(cartHandler).isDeliveryFree(cart);
        //given(csrtHandler.isDeliveryFree(cart)).willCallRealMethod();

        //when
        boolean isDeliveryFree = cartHandler.isDeliveryFree(cart);

        //then
        assertTrue(isDeliveryFree);


    }

}