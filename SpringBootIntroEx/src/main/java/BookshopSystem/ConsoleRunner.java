package BookshopSystem;

import BookshopSystem.domain.entities.Book;
import BookshopSystem.domain.enums.AgeRestriction;
import BookshopSystem.services.author.AuthorService;
import BookshopSystem.services.book.BookService;
import BookshopSystem.services.category.CategoryService;
import BookshopSystem.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    private final Scanner scanner;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.scanner = new Scanner(System.in);
        ;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {



//        this.seedService.seedAuthors();
//        this.seedService.seedCategory();
//        this.seedService.seedBooks();

//      this.printBooksTitleAfterYear();
//      this.printAuthorsWithBooksBeforeYear();
//      this.authorService.getAuthorsOrderByNumberOfBooks();
//      this.printBooksByAuthorNameOrderByReleaseDateDesc();
//      this.printBooksTitleByAgeRestriction();


    }

    private void printBooksTitleByAgeRestriction(){
        final String inputAgeRestriction = scanner.nextLine();

        List<Book> allByAgeRestriction = this.bookService
                .findAllByAgeRestriction(AgeRestriction.valueOf(inputAgeRestriction.toUpperCase()));

        allByAgeRestriction.stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void printBooksTitleAfterYear() {
        this.bookService.getBooksByReleaseDateAfter(LocalDate.of(2000, 12, 31))
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void printAuthorsWithBooksBeforeYear() {
        this.authorService.getDistinctByBooksReleaseDateBefore(LocalDate.of(1990, 1, 1))
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    private void printBooksByAuthorNameOrderByReleaseDateDesc() {
        this.bookService.getBooksByAuthorNameOrderByReleaseDateDesc("George", "Powell")
                .forEach(a -> System.out.println(a.getTitle() + " " + a.getReleaseDate() + " " + a.getCopies()));
    }
}
