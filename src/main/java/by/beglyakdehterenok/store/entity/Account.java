package by.beglyakdehterenok.store.entity;

import by.beglyakdehterenok.store.validation.CheckEmail;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Account extends BaseEntity {

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "form.notnull.valid")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull(message = "form.notnull.valid")
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Embedded
    private Address address;

    private Date birthday;

    @Column(name = "account_amount")
    private double accountAmount;

//    @Pattern(regexp = "\\+375\\s*(29|33|44)\\s*\\d{3}-\\d{2}-\\d{2}",message = "account.form.valid.phone")
    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;

    @Column(nullable = false, length = 40, unique = true)
    private String email;

    @Column(nullable = false, length = 50, unique = true)
    @NotEmpty(message = "account.form.valid.login")
    private String login;

    @Column(nullable = false, unique = true)
    @Size(min = 5, message = "account.form.valid.password")
    private String password;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Order> orders=new ArrayList<>();

}
