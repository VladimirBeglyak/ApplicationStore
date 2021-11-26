package by.beglyakdehterenok.store.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(exclude = "account")
public class Order extends BaseEntity {

    @Column(name = "date_of_create")
    private LocalDateTime dateOfCreate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne()
    @JoinColumn(name = "clothing_id")
    private Clothing clothing;

    @Column(nullable = false)
    private Long quantity;

    @Transient
    public Clothing getClothingByNewCount(){
        clothing.setCount(clothing.getCount()-quantity);
        return clothing;
    }
}
