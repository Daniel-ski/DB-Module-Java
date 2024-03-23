package BookshopSystem.repositories;

import BookshopSystem.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    List<Author> getDistinctByBooksReleaseDateBefore(LocalDate books_releaseDate);

    Optional<List<Author>> getAuthorsByFirstNameEndsWith(String suffix);
}
