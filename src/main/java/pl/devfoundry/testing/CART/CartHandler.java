package pl.devfoundry.testing.CART;

public interface CartHandler {
    boolean canHandleCart(Cart cart);
    void sendToPrepapare(Cart cart);
}
