package by.beglyakdehterenok.store.dto;

import by.beglyakdehterenok.store.entity.Category;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryCreationDto {
    private List<Category> categoriesDto =new ArrayList<>();

    public void addCategory(Category category){
        this.categoriesDto.add(category);
    }
}
