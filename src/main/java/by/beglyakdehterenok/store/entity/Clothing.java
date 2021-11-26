package by.beglyakdehterenok.store.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clothing")
@ToString(callSuper = true)
public class Clothing extends BaseEntity {

    @NotEmpty
    @Column(nullable = false, length = 50)
    private String name;

    @NotNull(message = "must be greater 0")
    @Column(nullable = false)
    private Double price;

    @NotEmpty
    @Column(nullable = false)
    private String description;

    @Column(name = "image",unique = true)
    private String imagePath;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "category_id")
    private Category category;

//    @ElementCollection(targetClass = Size.class,fetch = FetchType.EAGER)
//    @JoinTable(name = "clothing_size", joinColumns = @JoinColumn(name = "clothing_id"))
    @Column(name = "size", nullable = false)
    @Enumerated(EnumType.STRING)
    private Size size;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Season season;

    @Column(name = "count",nullable = false)
    private Long count;

}
