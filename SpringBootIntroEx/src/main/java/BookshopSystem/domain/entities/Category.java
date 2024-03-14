package BookshopSystem.domain.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Category extends BaseEntity{
    @Column(nullable = false)
    private String name;


    public Category(){
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

}
