package pl.devfoundry.testing.CART;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import pl.devfoundry.testing.order.Order;
import pl.devfoundry.testing.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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
        verify(cartHandler).sendToPrepapare(cart);
         // then(cartHandler).should().sendToPrepapare(cart);

         // verify(cartHandler, times(1)).sendToPrepapare(cart);
        //verify(cartHandler, atLeastOnce()).sendToPrepapare(cart);

        //InOrder inOrder = inOrder(cartHandler);
        //inOrder.verify(cartHandler).canHandleCart(cart);
       // inOrder.verify(cartHandler).sendToPrepapare(cart);

        // Dwie asercie teraz będą sprawdzać czy to nigdy nie zostało wysłane
        verify(cartHandler, never()).sendToPrepapare(cart);
        then(cartHandler).should(never().sendToPrepare(cart));

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(),equalTo(OrderStatus.PREPARING));

    }
}