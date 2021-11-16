package by.beglyakdehterenok.store.service;


import by.beglyakdehterenok.store.dto.CategoryDto;
import by.beglyakdehterenok.store.entity.Category;
import by.beglyakdehterenok.store.mapper.CategoryMapperImpl;
import by.beglyakdehterenok.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements BaseService<Category,Long>{

    private CategoryRepository categoryRepository;
    private CategoryMapperImpl categoryMapper;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public List<Category> findAllCategoriesDto(){
        return categoryRepository.findAll();/*.stream()
                .map(category -> categoryMapper.mapFrom(category))
                .collect(Collectors.toList());*/
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

//    public Category findByName(String name){
//        return categoryRepository.findByName(name);
//    }
}
