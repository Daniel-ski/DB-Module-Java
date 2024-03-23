package BookshopSystem.services.author;

import BookshopSystem.domain.entities.Author;
import BookshopSystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors(List<Author> authors) {
        this.authorRepository.saveAllAndFlush(authors);
    }

    @Override
    public boolean isDataSeeded() {
        return authorRepository.count() > 0;
    }

    @Override
    public Author getRandomAuthor() {
        final long count = this.authorRepository.count();

        if (count > 0){
            long randomId = new Random().nextLong(1L,count) + 1L;

            return this.authorRepository.findById(randomId).orElseThrow(NoSuchElementException::new);
        }

        throw new RuntimeException();
    }

    @Override
    public List<Author> getDistinctByBooksReleaseDateBefore(LocalDate books_releaseDate) {
        return this.authorRepository.getDistinctByBooksReleaseDateBefore(books_releaseDate)
                .stream()
                .filter(author -> author.getBooks().size() > 0 )
                .toList();
    }

    @Override
    public void getAuthorsOrderByNumberOfBooks() {
        this.authorRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(author -> author.getBooks().size()))
                .forEach(a -> System.out.printf("%s %s - %d%n",a.getFirstName(),a.getLastName(),a.getBooks().size()));
    }

    @Override
    public void printAuthorsByFirstNameEndsWith() {
        String suffix = new Scanner(System.in).nextLine();

        this.authorRepository.getAuthorsByFirstNameEndsWith(suffix)
                .orElseThrow(NoSuchElementException::new)
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }
}
