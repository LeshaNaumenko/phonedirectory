package com.course.phonedirectory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @JsonIgnore
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    private String password;

    private String roles;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private UserAccount account;

    @OneToMany()
    @JoinColumn(name = "user_id")
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    public User(String name, String password, String roles, List<PhoneNumber> phoneNumbers) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.phoneNumbers = phoneNumbers;
    }

    public User(String name, String password, String roles, UserAccount account, List<PhoneNumber> phoneNumbers) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.account = account;
        this.phoneNumbers = phoneNumbers;
    }

    public User() {}

    public void addNumber(PhoneNumber number){
        if (!phoneNumbers.contains(number)){
            phoneNumbers.add(number);
        }
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    //    public List<String> getRoles() {
//        return Arrays.asList( roles.split(","));
//    }
//
//    public void setRoles(List<String> roles) {
//            this.roles = String.join("," , roles);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(phoneNumbers, user.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, roles, phoneNumbers);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", account=" + account +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
