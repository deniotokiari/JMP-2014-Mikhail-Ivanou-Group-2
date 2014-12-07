package com.epam.realm.realm.model;

import io.realm.RealmObject;

/**
 * Created by sergey on 06.12.2014.
 */
public class Address extends RealmObject {
    private String country;
    private String city;
    private String street;
    private int postalCode;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
