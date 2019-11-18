package com.course.phonedir.consoleclient.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue
    private int id;

    private Integer cash;

    @OneToOne
    @JoinColumn(name = "number_id", referencedColumnName = "id")
    private PhoneNumber phoneNumber;

    // other fields

    public UserAccount() {
    }

    public UserAccount(Integer cash, PhoneNumber phoneNumber) {
        this.cash = cash;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public void addCash(int cash) {
        this.cash = this.cash + cash;
    }

    public void subtractCash(int cash) {
        this.cash = this.cash - cash;
    }

    public Integer getCash() {
        return cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return id == that.id &&
                Objects.equals(cash, that.cash) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cash, phoneNumber);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", cash=" + cash +
                ", number=" + phoneNumber +
                '}';
    }
}
