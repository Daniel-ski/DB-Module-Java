package entities;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.time.LocalDate;
@Entity(name = "user")
public class User {
    @Id
    private long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "age")
    private int age;
    @Column(name = "registration_date")
    private LocalDate registration;

    public User(String userName, int age, LocalDate registration) {
        this.userName = userName;
        this.age = age;
        this.registration = registration;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDate registration) {
        this.registration = registration;
    }
}
