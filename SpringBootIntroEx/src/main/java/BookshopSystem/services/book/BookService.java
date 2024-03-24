package BookshopSystem.services.book;

import BookshopSystem.domain.dto.BookDto;
import BookshopSystem.domain.entities.Book;
import BookshopSystem.domain.enums.AgeRestriction;
import BookshopSystem.domain.enums.EditionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BookService {
    void seedBooks(List<Book> books);

    boolean isDataSeeded();

    ArrayList<Book> getBooksByReleaseDateAfter(LocalDate releaseDate);

    List<Book> getBooksByAuthorNameOrderByReleaseDateDesc(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);


    void printAllByEditionTypeIsAndCopiesLessThan();

   void printAllByPriceLessThanOrPriceGreaterThan();

   List<Book> printAllByReleaseDateNot(LocalDate year);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    void findBookCountByTitleIsGreaterThan();

    void findBooksByTitle();
}
