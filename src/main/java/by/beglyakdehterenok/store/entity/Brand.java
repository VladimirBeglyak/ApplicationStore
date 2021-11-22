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
@AllArgsConstructor
@ToString(callSuper = true)
public class Brand extends BaseEntity {

    @Column(name = "brand_name", length = 40, unique = true)
    private String name;

}
