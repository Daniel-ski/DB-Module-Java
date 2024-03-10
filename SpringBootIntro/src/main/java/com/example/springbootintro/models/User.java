package com.example.springbootintro.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name",unique = true)
    private String userName;
    @Column
    private Integer age;

   @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts;

    public User() {
        this.accounts = new ArrayList<>();
    }

    public User(String userName, Integer age,Account account) {
        this();

        this.userName = userName;
        this.age = age;
        this.accounts.add(account);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
