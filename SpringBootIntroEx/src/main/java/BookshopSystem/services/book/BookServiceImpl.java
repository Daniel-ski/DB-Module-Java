package BookshopSystem.services.book;

import BookshopSystem.domain.entities.Author;
import BookshopSystem.domain.entities.Book;
import BookshopSystem.domain.enums.AgeRestriction;
import BookshopSystem.domain.enums.EditionType;
import BookshopSystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.*;

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

    @Override
    public void findAllBooksCopiesByAuthor() {
        System.out.println(this.bookRepository.findAllBooksCopiesByAuthor("Randy Graham")
                .orElseThrow(NoSuchElementException::new));
    }

    @Override
    public void updateAllBooksCopiesByReleaseDate() {
        String inputDate = new Scanner(System.in).nextLine();
        Integer inputCopies = Integer.parseInt(new Scanner(System.in).nextLine());

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy",Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try{
            LocalDate date = LocalDate.parse(inputDate,inputFormatter);
            String formattedDate = outputFormatter.format(date);

            Integer updateCount = this.bookRepository.updateAllBooksCopiesByReleaseDate(inputCopies, LocalDate.parse(formattedDate));

            System.out.println(updateCount * inputCopies);

        }catch (DateTimeParseException e){
            System.out.println("Invalid date formatter");
            e.printStackTrace();
        }

    }

    @Override
    public void deleteBooksByCopiesLessThan() {
        Integer number = new Scanner(System.in).nextInt();

        System.out.println(this.bookRepository.deleteAllByCopiesLessThan(number));
    }

    @Override
    public void totalBooksByAuthor() {
        String[] authorFullName = new Scanner(System.in).nextLine().split(" ");
        String firstName = authorFullName[0].trim();
        String lastName = authorFullName[1].trim();

        Integer totalBooksByAuthor = this.bookRepository.get_total_books_by_author(firstName,lastName);

        System.out.println(totalBooksByAuthor);
    }
}
