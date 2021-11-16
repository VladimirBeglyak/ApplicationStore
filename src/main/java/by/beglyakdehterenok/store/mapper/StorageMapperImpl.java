package by.beglyakdehterenok.store.mapper;

import by.beglyakdehterenok.store.dto.StorageDto;
import by.beglyakdehterenok.store.entity.Storage;
import org.springframework.stereotype.Component;

@Component
public class StorageMapperImpl implements BaseMapper<Storage, StorageDto> {

    @Override
    public StorageDto mapFrom(Storage entity) {
        return new StorageDto(entity.getId(),
                entity.getCount(),
                entity.getClothing().getName(),
                entity.getClothing().getPrice(),
                entity.getClothing().getId());
    }
}
