package by.beglyakdehterenok.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    @Column(nullable = false, length = 20)
    private String country;

    @Column(nullable = false, length = 20)
    private String town;

    @Column(nullable = false, length = 20)
    private String street;

    @Column(nullable = false, length = 20)
    private String apartment;
}
