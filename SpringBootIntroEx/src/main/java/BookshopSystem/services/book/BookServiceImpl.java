package BookshopSystem.services.book;

import BookshopSystem.domain.entities.Book;
import BookshopSystem.domain.enums.AgeRestriction;
import BookshopSystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAllAndFlush(books);
    }

    @Override
    public boolean isDataSeeded() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public ArrayList<Book> getBooksByReleaseDateAfter(LocalDate releaseDate) {
       return this.bookRepository.getBooksByReleaseDateAfter(releaseDate);

    }

    @Override
    public List<Book> getBooksByAuthorNameOrderByReleaseDateDesc(String firstName, String lastName) {
        return this.bookRepository.getBooksByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDesc(firstName, lastName)
                .stream()
                .sorted(Comparator.comparing(Book::getTitle)).toList();
    }

    @Override
    public List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(ageRestriction).orElseThrow(NoSuchElementException::new);
    }
}
