package pl.devfoundry.testing;

import java.util.Objects;

public class Meal {

    private int price;
    private int quantity;
    private String name;

    public Meal() {
        this.price = price;
        this.name = name;
    }

    public Meal(int price, int quantity, String name) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
    }


    public int getQuantity() {
        return quantity;
    }

    public Meal(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscountedPrice (int discount) {

        if(discount > this.price) {
            throw new IllegalArgumentException("Discount cannot be higher than the price");
        }
        return this.price - discount;
    }

    //żeby działała asercja porównywująca czy obiekty o tej wartości są te same trzeba nadpisać metody equals i hashcode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return price == meal.price && Objects.equals(name, meal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    int sumPrice(){
        return price * quantity;
    }
}


