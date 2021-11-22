package by.beglyakdehterenok.store.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
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
