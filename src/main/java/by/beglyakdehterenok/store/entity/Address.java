package by.beglyakdehterenok.store.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

//    @NotNull(message = "form.notnull.valid")
    @Column(nullable = false, length = 20)
    private String country;

//    @NotNull(message = "form.notnull.valid")
    @Column(nullable = false, length = 20)
    private String town;

//    @NotNull(message = "form.notnull.valid")
    @Column(nullable = false, length = 20)
    private String street;

//    @NotNull(message = "form.notnull.valid")
    @Column(nullable = false, length = 20)
    private String apartment;
}
