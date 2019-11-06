package com.course.phonedirectory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue
    private int id;

    private int cash = 0;

    // other fields

    public UserAccount(int cash) {
        this.cash = cash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void addCash(int cash) {
        this.cash = this.cash + cash;
    }

    public void subtractCash(int cash) {
        this.cash = this.cash - cash;
    }

    public int getCash() {
        return cash;
    }
}
