package pl.devfoundry.testing.account;


public class Account {
    private boolean active;

    private Address defaultDeliveryAdress;

    public Account() {
        this.active = false;
    }



    public Account(Address address) {
    }


    public void activate(){
        this.active=true;
    }

    public boolean isActive() {
        return this.active;
    }

    public Address getDefaultDeliveryAddress() { return defaultDeliveryAdress;
    }

    public void setDefaultDeliveryAdress(Address defaultDeliveryAdress) {
        this.defaultDeliveryAdress = defaultDeliveryAdress;
    }


}