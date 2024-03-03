package BookshopSystem.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table
public class Author extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @OneToMany
    private Set<Book> books;
}
