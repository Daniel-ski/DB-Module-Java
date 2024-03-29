package domain.entities;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @OneToMany(targetEntity = User.class,mappedBy = "bornTown")
    private Set<User> born;

    @OneToMany(targetEntity = User.class,mappedBy = "currentlyLiving")
    private Set<User> living;



    public Town() {
        this.born = new HashSet<>();
        this.living = new HashSet<>();
    }

    public Town(String name, String country) {
        this();

        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<User> getBorn() {
        return born;
    }

    public void setBorn(Set<User> born) {
        this.born = born;
    }

    public Set<User> getLiving() {
        return living;
    }

    public void setLiving(Set<User> living) {
        this.living = living;
    }
}
