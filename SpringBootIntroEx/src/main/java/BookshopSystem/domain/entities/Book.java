package BookshopSystem.domain.entities;

import BookshopSystem.domain.enums.AgeRestriction;
import BookshopSystem.domain.enums.EditionType;
import jakarta.persistence.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Book extends BaseEntity{
    @Column(length = 50,nullable = false)
    private String title;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EditionType editionType;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer copies;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgeRestriction ageRestriction;
    @ManyToOne
    private Author author;
    @ManyToMany
    private Set<Category> categories;

    public Book() {
    }

    public Book(String title, EditionType editionType, Double price, Integer copies,
                LocalDate releaseDate, AgeRestriction ageRestriction, Author author, Set<Category> categories) {

        this.title = title;
       // this.description = description;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categories = categories;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
