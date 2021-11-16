package by.beglyakdehterenok.store.mapper;

import by.beglyakdehterenok.store.dto.CategoryDto;
import by.beglyakdehterenok.store.entity.Category;

public class CategoryMapperImpl implements BaseMapper<Category, CategoryDto>{
    @Override
    public CategoryDto mapFrom(Category entity) {
        return new CategoryDto(entity.getName());
    }
}
