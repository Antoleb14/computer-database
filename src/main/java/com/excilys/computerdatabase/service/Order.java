package com.excilys.computerdatabase.service;

public class Order {

    private String champ = "";
    private String order = "";

    public Order(String champ, String order) {
        this.champ = champ;
        this.order = order;
    }

    public String getChamp() {
        return champ;
    }

    public void setChamp(String champ) {
        this.champ = champ;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return champ + " " + order.toUpperCase();
    }

}
