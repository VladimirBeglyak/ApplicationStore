package by.beglyakdehterenok.store.service;


import by.beglyakdehterenok.store.dto.CategoryDto;
import by.beglyakdehterenok.store.entity.Category;
import by.beglyakdehterenok.store.mapper.CategoryMapperImpl;
import by.beglyakdehterenok.store.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements BaseService<Category,Long>{

    private final CategoryRepository categoryRepository;
    private final CategoryMapperImpl categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapperImpl categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public List<CategoryDto> findAllCategoriesDto(){
        return categoryRepository.findAll().stream()
                .map(category -> categoryMapper.mapFrom(category))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Category category) {
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

//    public Category findByName(String name){
//        return categoryRepository.findByName(name);
//    }
}
