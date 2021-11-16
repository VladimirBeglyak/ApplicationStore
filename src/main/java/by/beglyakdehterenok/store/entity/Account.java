package by.beglyakdehterenok.store.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
//import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Account extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

//    @Transient
//    private String fullName = this.firstName + " " + this.lastName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Address address;

    private LocalDate birthday;

    @Column(name = "account_amount")
    private double accountAmount;

    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;

    @Column(nullable = false, length = 40, unique = true)
    private String email;

    @Column(nullable = false, length = 50, unique = true)
    private String login;

    @Column(nullable = false, unique = true)
    private String password;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Order> orders=new ArrayList<>();

    public void addOrder(Order order){
            orders.add(order);
    }

    @Transient
    public void removeOrder(Order order){
        orders.remove(order);
    }

    @Transient
    public void removeAllOrders(List<Order> orders){
        orders.removeAll(orders);
    }

    @Transient
    public void subtractAmount(Double amount){
        this.accountAmount -= amount;
    }


}
