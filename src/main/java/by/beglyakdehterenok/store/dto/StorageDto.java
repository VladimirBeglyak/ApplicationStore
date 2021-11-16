package by.beglyakdehterenok.store.dto;

import by.beglyakdehterenok.store.entity.Clothing;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class StorageDto {

    private Long id;
    private Long count;
    private String name;
    private double price;
    private Long clothingId;

}
