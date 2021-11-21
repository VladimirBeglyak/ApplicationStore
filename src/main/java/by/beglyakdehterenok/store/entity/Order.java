package by.beglyakdehterenok.store.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(exclude = "account")
public class Order extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne()
    @JoinColumn(name = "clothing_id")
    private Clothing clothing;

    @Column(nullable = false)
    private Long quantity;

}
