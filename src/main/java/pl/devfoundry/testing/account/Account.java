package pl.devfoundry.testing.account;


import pl.devfoundry.testing.account.Address;

public class Account {
    private boolean active;

    private Address defaultDeliveryAdress;
    private String email;

    public Account() {
        this.active = false;
    }

    public Account(boolean active, Address defaultDeliveryAdress) {
        this.defaultDeliveryAdress = defaultDeliveryAdress;
        if (defaultDeliveryAdress != null) {
            activate();
        } else {
            this.active = false;
        }
    }

    public Account(Address address) {
    }


    public void activate() {
        this.active = true;
    }

    public boolean isActive() {
        return this.active;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAdress;
    }

    public void setDefaultDeliveryAdress(Address defaultDeliveryAdress) {
        this.defaultDeliveryAdress = defaultDeliveryAdress;
    }

    public void setEmail(String email) {

        if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Wrong email format");
        }

    }

    public String getEmail() {
        return email;
    }
}