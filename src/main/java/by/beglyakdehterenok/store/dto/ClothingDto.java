package by.beglyakdehterenok.store.dto;

import by.beglyakdehterenok.store.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ClothingDto {

    private Long id;
    private Long count;
    private String name;
    private Double price;
    private String description;
    private String image;
    private Category category;
    private Season season;
    private Size size;
    private Brand brand;
    private Type type;
}
