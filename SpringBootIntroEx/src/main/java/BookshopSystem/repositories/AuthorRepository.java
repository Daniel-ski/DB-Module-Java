package BookshopSystem.repositories;

import BookshopSystem.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    List<Author> getDistinctByBooksReleaseDateBefore(LocalDate books_releaseDate);

}
