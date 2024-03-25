package BookshopSystem.repositories;

import BookshopSystem.domain.dto.BookDto;
import BookshopSystem.domain.entities.Author;
import BookshopSystem.domain.entities.Book;
import BookshopSystem.domain.enums.AgeRestriction;
import BookshopSystem.domain.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    ArrayList<Book> getBooksByReleaseDateAfter(LocalDate releaseDate);

    ArrayList<Book> getBooksByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDesc(String firstName, String lastName);

    Optional< List<Book>> findAllByAgeRestriction(AgeRestriction ageRestriction);

    Optional<List<Book>> findAllByEditionTypeIsAndCopiesLessThan(EditionType editionType,int copies);

    Optional<List<Book>> findAllByPriceLessThanOrPriceGreaterThan(double start,double end);

    Optional<List<Book>> findAllByReleaseDateNot(LocalDate year);

    Optional<List<Book>> findAllByReleaseDateBefore(LocalDate date);

    @Query("SELECT count(b) FROM Book AS b WHERE length(b.title) > :length ")
    Optional<Integer> findBookCountByTitleIsGreaterThan(Integer length);

    @Query("SELECT new BookshopSystem.domain.dto.BookDto(b.title,b.editionType,b.ageRestriction,b.price) " +
            "FROM Book AS b WHERE b.title = :title")
    Optional<List<BookDto>> findBooksByTitle(String title);

    @Query("SELECT SUM (b.copies) FROM Book AS b WHERE CONCAT(b.author.firstName, ' ', b.author.lastName) = :full_name")
    Optional<Integer> findAllBooksCopiesByAuthor(@Param("full_name") String fullName);

    @Modifying
    @Query("UPDATE Book AS b SET b.copies = b.copies + :count WHERE b.releaseDate > :date")
    Integer updateAllBooksCopiesByReleaseDate(@Param("count") Integer number , @Param("date") LocalDate date);

    @Transactional
    Integer deleteAllByCopiesLessThan(Integer number);

    @Procedure(value = "get_total_books_by_author",outputParameterName = "totalBooks")
    Integer get_total_books_by_author(@Param("first_name") String firstName,@Param("last_name") String lastName);
}
