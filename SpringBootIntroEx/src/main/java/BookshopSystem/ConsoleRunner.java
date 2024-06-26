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

    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {



//        this.seedService.seedAuthors();
//        this.seedService.seedCategory();
//        this.seedService.seedBooks();

        this.bookService.totalBooksByAuthor();
//      this.bookService.deleteBooksByCopiesLessThan();
//      this.bookService.updateAllBooksCopiesByReleaseDate();
//      this.bookService.findAllBooksCopiesByAuthor();
//      this.bookService.findBooksByTitle();
//      this.bookService.findBookCountByTitleIsGreaterThan();
//      this.authorService.printAuthorsByFirstNameEndsWith();
//      printAllByReleaseDateBefore();
//      printAllByReleaseDateNot();
//      this.bookService.printAllByPriceLessThanOrPriceGreaterThan();
//      this.bookService.printAllByEditionTypeIsAndCopiesLessThan();
//      this.printBooksTitleAfterYear();
//      this.printAuthorsWithBooksBeforeYear();
//      this.authorService.getAuthorsOrderByNumberOfBooks();
//      this.printBooksByAuthorNameOrderByReleaseDateDesc();
//      this.printBooksTitleByAgeRestriction();


    }

    private void printAllByReleaseDateBefore(){
        final String[] inputDate = scanner.nextLine().split("-");

        final int day = Integer.parseInt(inputDate[0]);
        final int mount = Integer.parseInt(inputDate[1]);
        final int year = Integer.parseInt(inputDate[2]);

        this.bookService.findAllByReleaseDateBefore(LocalDate.of(year,mount,day))
                .forEach(book -> System.out.printf("%s %.2f%n",book.getTitle(),book.getPrice()));
    }

    private void printAllByReleaseDateNot(){
        final int inputYear = scanner.nextInt();

        this.bookService.printAllByReleaseDateNot(LocalDate.of(inputYear,1,1))
                .forEach(book -> System.out.println(book.getTitle()));
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
