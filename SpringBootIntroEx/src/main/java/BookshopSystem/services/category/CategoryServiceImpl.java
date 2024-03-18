package BookshopSystem.services.category;

import BookshopSystem.domain.entities.Category;
import BookshopSystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories(List<Category> categories) {
        this.categoryRepository.saveAllAndFlush(categories);
    }

    @Override
    public boolean isDataSeeded() {
        return this.categoryRepository.count() > 0;
    }

    @Override
    public Category getRandomCategory() {
        long count = this.categoryRepository.count();

        if (count > 0) {

            long randomId = new Random().nextLong(1L, count) + 1L;

            return this.categoryRepository.findById(randomId).orElseThrow(NoSuchElementException::new);
        }
        throw new RuntimeException();
    }

    @Override
    public Set<Category> getRandomCategories() {
        Random random = new Random();
        long size = this.categoryRepository.count();

        long categoriesCount = random.nextLong(1L ,size) + 1L;

        Set<Long> categoriesIds = new HashSet<>();

        for (int i = 0; i < categoriesCount; i++) {
            long nextId = random.nextLong(size) + 1L;

            categoriesIds.add(nextId);
        }

        List<Category> allById = this.categoryRepository.findAllById(categoriesIds);

        return new HashSet<>(allById);
    }
}
