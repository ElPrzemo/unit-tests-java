package pl.devfoundry.testing.order;

import pl.devfoundry.testing.Meal;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private OrderStatus orderStatus;

    private List<Meal> meals = new ArrayList<>();

    public void addMealToOOrder(Meal meal){
        this.meals.add(meal);
    }

    public void removeMEealFromOrder (Meal meal){
        this.meals.remove(meal);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void changeOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }

    public List<Meal>getMeals(){
        return meals;
    }

    public void cancel(){
        this.meals.clear();
    }

    int totalPrice(){
        int sum= 0;
        this.meals.stream().mapToInt(meal->meal.getPrice()).sum();
        if(sum <0) {
            throw new IllegalStateException("Price limit excceded");
        }else{
        return sum;
    }}

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
