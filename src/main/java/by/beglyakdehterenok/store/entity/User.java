package by.beglyakdehterenok.store.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @NotEmpty
    @Column(nullable = false, length = 50, unique = true)
    private String name;

    private String photos;

    private Date birthday;

    private LocalDateTime dateAndTimeOfCreateOrder;

}
