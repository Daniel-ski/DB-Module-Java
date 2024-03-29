package domain.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column
    private Boolean isPublic;

    @ManyToMany(mappedBy = "albums")
    private Set<Picture> pictures;

    @ManyToOne(optional = false)
    private User owner;

    public Album() {
        this.pictures = new HashSet<>();
    }

    public Album(String name, String backgroundColor, Boolean isPublic, Set<Picture> pictures, User owner) {

        this();

        this.name = name;
        this.backgroundColor = backgroundColor;
        this.isPublic = isPublic;
        this.pictures = pictures;
        this.owner = owner;
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

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
