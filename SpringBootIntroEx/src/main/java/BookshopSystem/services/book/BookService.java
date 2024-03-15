package BookshopSystem.services.book;

import BookshopSystem.domain.entities.Book;
import BookshopSystem.domain.enums.AgeRestriction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface BookService {
    void seedBooks(List<Book> books);

    boolean isDataSeeded();

    ArrayList<Book> getBooksByReleaseDateAfter(LocalDate releaseDate);

    List<Book> getBooksByAuthorNameOrderByReleaseDateDesc(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);
}
