package by.beglyakdehterenok.store.mapper;

import by.beglyakdehterenok.store.dto.BrandDto;
import by.beglyakdehterenok.store.entity.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapperImpl implements BaseMapper<Brand, BrandDto>{
    @Override
    public BrandDto mapFrom(Brand entity) {
        return new BrandDto(entity.getId(),entity.getName());
    }
}
