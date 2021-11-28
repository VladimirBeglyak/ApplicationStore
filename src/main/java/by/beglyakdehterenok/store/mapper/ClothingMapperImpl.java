package by.beglyakdehterenok.store.mapper;

import by.beglyakdehterenok.store.dto.ClothingDto;
import by.beglyakdehterenok.store.entity.Clothing;
import org.springframework.stereotype.Component;

@Component
public class ClothingMapperImpl implements BaseMapper<Clothing, ClothingDto>{

    @Override
    public ClothingDto mapFrom(Clothing entity) {
        return new ClothingDto(
                entity.getId(),
                entity.getCount(),
                entity.getName(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getImage(),
                entity.getCategory(),
                entity.getSeason(),
                entity.getSize(),
                entity.getBrand(),
                entity.getType()
        );
    }
}
