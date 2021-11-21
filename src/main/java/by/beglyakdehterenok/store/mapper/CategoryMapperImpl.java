package by.beglyakdehterenok.store.mapper;

import by.beglyakdehterenok.store.dto.CategoryDto;
import by.beglyakdehterenok.store.entity.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements BaseMapper<Category, CategoryDto>{
    @Override
    public CategoryDto mapFrom(Category entity) {
        return new CategoryDto(entity.getId(), entity.getName());
    }
}
