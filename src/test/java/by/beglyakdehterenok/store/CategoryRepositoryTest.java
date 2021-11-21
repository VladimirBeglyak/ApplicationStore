package by.beglyakdehterenok.store;

import by.beglyakdehterenok.store.entity.Category;
import by.beglyakdehterenok.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CategoryRepositoryTest extends BaseTest<Category>{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    List<Category> findAll() {
        return null;
    }

    @Override
    void save() {
        Category category = new Category();
        category.setName("Shoes");
        categoryRepository.save(category);

        categoryRepository.findAll()
                .forEach(someCategory -> System.out.println(someCategory));
    }

    @Override
    void delete() {

    }

    @Override
    Optional<Category> findById() {
        return Optional.empty();
    }

    @Override
    void update() {

    }


}
