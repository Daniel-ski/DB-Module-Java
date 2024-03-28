package BookshopSystem.domain.dto;

import BookshopSystem.domain.enums.AgeRestriction;
import BookshopSystem.domain.enums.EditionType;

public class BookDto {
    private String title;

    private EditionType editionType;

    private AgeRestriction ageRestriction;

    private Double price;

    public BookDto(String title, EditionType editionType, AgeRestriction ageRestriction, Double price) {
        this.title = title;
        this.editionType = editionType;
        this.ageRestriction = ageRestriction;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.title + " " +
                this.editionType.name() + " " +
                this.ageRestriction.name() + " " +
                this.price;
    }
}
