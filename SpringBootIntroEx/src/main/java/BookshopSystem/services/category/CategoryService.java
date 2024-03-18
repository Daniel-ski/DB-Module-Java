package BookshopSystem.services.category;

import BookshopSystem.domain.entities.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories(List<Category> categories);
    boolean isDataSeeded();
    Category getRandomCategory();

    Set<Category> getRandomCategories();
}
