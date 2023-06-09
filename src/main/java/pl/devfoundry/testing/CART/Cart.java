package pl.devfoundry.testing.CART;

import pl.devfoundry.testing.Meal;
import pl.devfoundry.testing.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    void addOrderToCart(Order order) {
        this.orders.add(order);
    }

    void clearCart(){
        this.orders.clear();
    }

    void simluateLargeOrder(){
        for (int i = 0; i< 1000; i++){
            Meal meal = new Meal();
            Order order = new Order();
            order.addMealToOOrder(meal);
            addOrderToCart(order);
        }
        System.out.println("Cart size: " + orders.size());
        clearCart();
    }
}
