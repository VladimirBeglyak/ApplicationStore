package by.beglyakdehterenok.store.entity;

import by.beglyakdehterenok.store.dto.BrandDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Brand extends BaseEntity {

    @Column(name = "brand_name", nullable = false, length = 40, unique = true)
    private String name;

//    @OneToMany(mappedBy = "brand")
//    private List<Clothing> clothes = new ArrayList<>();
//
//    public void addClothing(Clothing clothing){
//        clothes.add(clothing);
//        clothing.setBrand(this);
//    }
//    @Transient
//    public void removeClothing(Clothing clothing){
//        clothes.remove(clothing);
//    }

//    @Transient
//    public BrandDto createDto(){
//        return new BrandDto(getName(), getClothes().size());
//    }
}
