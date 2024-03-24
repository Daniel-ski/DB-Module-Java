package BookshopSystem.services.book;

import BookshopSystem.domain.entities.Book;
import BookshopSystem.domain.enums.AgeRestriction;
import BookshopSystem.domain.enums.EditionType;
import BookshopSystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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

    @Override
    public void printAllByEditionTypeIsAndCopiesLessThan() {
        this.bookRepository.findAllByEditionTypeIsAndCopiesLessThan(EditionType.GOLD,5000)
                .orElseThrow(NoSuchElementException::new)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printAllByPriceLessThanOrPriceGreaterThan() {
        this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(5.00,40.00)
                .orElseThrow(NoSuchElementException::new)
                .forEach(book -> System.out.printf("%s - $%.2f%n",book.getTitle(),book.getPrice()));
    }

    @Override
    public List<Book> printAllByReleaseDateNot(LocalDate year) {
       return this.bookRepository.findAllByReleaseDateNot(year).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateBefore(date).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void findBookCountByTitleIsGreaterThan() {
        String length = new Scanner(System.in).nextLine();

        System.out.println(this.bookRepository.findBookCountByTitleIsGreaterThan(Integer.valueOf(length)).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public void findBooksByTitle() {
        String title = new Scanner(System.in).nextLine();

        this.bookRepository.findBooksByTitle(title)
                .orElseThrow(NoSuchElementException::new)
                .forEach(bookDto -> System.out.println(bookDto.toString()));
    }
}
