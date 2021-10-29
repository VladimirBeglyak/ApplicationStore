package by.beglyakdehterenok.store.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Data
public class Account extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

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

    @Column(nullable = false, length = 50, unique = true)
    private String password;

//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
//    private List<Order> orders = new ArrayList<>();
//
//    public void addOrder(Order order){
//        orders.add(order);
//        order.setAccount(this);
//    }


}
