package by.beglyakdehterenok.store.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "storage")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Storage extends BaseEntity{

    @Column(name = "count")
    private Long count;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clothing_id")
    private Clothing clothing;

}
