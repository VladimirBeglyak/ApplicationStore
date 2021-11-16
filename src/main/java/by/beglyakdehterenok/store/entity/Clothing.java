package by.beglyakdehterenok.store.entity;

import lombok.*;

import javax.persistence.*;
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

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;

    @Column(name = "image", nullable = false, unique = true)
    private String imagePath;


//    private String brand;
//
//    private String category;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_id")
//    private Category category;

    @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH},fetch = FetchType.EAGER)
    @JoinTable(name = "category_clothing",
            joinColumns = @JoinColumn(name = "clothing_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

//    @Column(name = "quantity_stock", nullable = false)
//    private int quantityInStock;

    @ElementCollection(targetClass = Size.class)
    @JoinTable(name = "clothing_size", joinColumns = @JoinColumn(name = "clothing_id"))
    @Column(name = "size", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Size> sizes = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "brand_id")
    private Brand brand;

//    @Enumerated(EnumType.STRING)
//    private Type type;
//
//    @Enumerated(EnumType.STRING)
//    private Season season;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clothing")
//    private List<Order> orders=new ArrayList<>();

    public void addCategory(Category category){
        categories.add(category);
    }

    public void addSize(Size size){
        sizes.add(size);
    }

//    public void removeCategory(Category category){
//        categories.remove(category);
//        category.getClothes().remove(this);
//    }

//    public void subtractQuantity(int n){
//        this.quantityInStock -= n;
//    }

}
