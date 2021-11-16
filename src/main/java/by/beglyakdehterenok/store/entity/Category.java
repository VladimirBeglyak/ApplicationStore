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

    @Column(name = "category_name", nullable = false, length = 30, unique = true)
    private String name;

//    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<Clothing> clothes;

//    @ManyToMany()
//    @JoinTable(name = "category_clothing",
//    joinColumns = @JoinColumn(name = "category_id"),
//    inverseJoinColumns = @JoinColumn(name = "clothing_id"))
//    private List<Clothing> clothes;

//    @Transient
//    public void addClothing(Clothing clothing){
//        clothes.add(clothing);
//        clothing.getCategories().add(this);
//    }
//    @Transient
//    public void removeClothing(Clothing clothing){
//        clothes.remove(clothing);
//        clothing.getCategories().remove(this);
//    }

//    @Transient
//    public CategoryDto createDto(){
//        return new CategoryDto(getName(), clothes.size());
//    }
}
