package BookshopSystem.services.author;

import BookshopSystem.domain.entities.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    void seedAuthors(List<Author> authors);
    boolean isDataSeeded();
    Author getRandomAuthor();
    List<Author> getDistinctByBooksReleaseDateBefore(LocalDate books_releaseDate);

    void getAuthorsOrderByNumberOfBooks();
}
