package BookshopSystem.services.seed;

import BookshopSystem.Constants.FilaPath;
import BookshopSystem.domain.entities.Author;
import BookshopSystem.domain.entities.Book;
import BookshopSystem.domain.entities.Category;
import BookshopSystem.domain.enums.AgeRestriction;
import BookshopSystem.domain.enums.EditionType;
import BookshopSystem.services.author.AuthorService;
import BookshopSystem.services.book.BookService;
import BookshopSystem.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.DateFormatter;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static BookshopSystem.Constants.FilaPath.*;

@Component
public class SeedServiceImpl implements SeedService {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

@Autowired
    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedAuthors() throws IOException {
         if (!authorService.isDataSeeded()) {

            this.authorService
                .seedAuthors(Files.readAllLines(Path.of(RESOURCES_URL + AUTHOR_FILE_NAME))
                        .stream()
                        .filter(s -> !s.isBlank())
                        .map(firstNameAndLastName -> {
                            String firstName = firstNameAndLastName.split(" ")[0];
                            String lastName = firstNameAndLastName.split(" ")[1];

                            return new Author(firstName, lastName);
                        })
                        .collect(Collectors.toList()));
        }
    }

    @Override
    public void seedBooks() throws IOException {
        if (!this.bookService.isDataSeeded()){
            this.bookService.seedBooks(Files.readAllLines(Path.of(RESOURCES_URL + BOOK_FILE_NAME))
                    .stream()
                    .filter(row -> !row.isBlank())
                    .map(row -> {
                        String[] data = row.split("\\s+");

                        String title = Arrays.stream(data)
                                .skip(5L)
                                .collect(Collectors.joining(" "));

                        return new Book(
                                title,
                                EditionType.values()[Integer.parseInt(data[0])],
                                Double.parseDouble(data[3]),
                                Integer.parseInt(data[2]),
                                LocalDate.parse(data[1],DateTimeFormatter.ofPattern("d/M/yyyy")),
                                AgeRestriction.values()[Integer.parseInt(data[4])],
                                this.authorService.getRandomAuthor(),
                                this.categoryService.getRandomCategories());

                    }).toList());
        }
    }

    @Override
    public void seedCategory() throws IOException {
        if (!this.categoryService.isDataSeeded()){

            this.categoryService.seedCategories(Files.readAllLines(Path.of(RESOURCES_URL + CATEGORY_FILE_NAME))
                    .stream()
                    .filter(row -> !row.isBlank())
                    .map(Category::new)
                    .collect(Collectors.toList()));
        }
    }
}
