package by.beglyakdehterenok.store.entity;

import by.beglyakdehterenok.store.dto.CategoryDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Category extends BaseEntity{

    @Column(name = "category_name", length = 30, unique = true)
    private String name;
}
