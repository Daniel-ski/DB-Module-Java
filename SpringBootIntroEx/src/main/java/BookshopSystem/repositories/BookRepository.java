package BookshopSystem.repositories;

import BookshopSystem.domain.entities.Book;
import BookshopSystem.domain.enums.AgeRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    ArrayList<Book> getBooksByReleaseDateAfter(LocalDate releaseDate);

    ArrayList<Book> getBooksByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDesc(String firstName, String lastName);

    Optional< List<Book>> findAllByAgeRestriction(AgeRestriction ageRestriction);
}
