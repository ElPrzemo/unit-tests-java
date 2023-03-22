package pl.devfoundry.testing.account;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Meal> meals = new ArrayList<>();

    public void addMealToOOrder(Meal meal){
        this.meals.add(meal);
    }

    public void removeMEealFromOrder (Meal meal){
        this.meals.remove(meal);
    }

    public List<Meal>getMeals(){
        return meals;
    }

    void cancel(){
        this.meals.clear();
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
