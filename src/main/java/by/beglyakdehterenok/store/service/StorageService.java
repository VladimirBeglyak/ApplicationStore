package by.beglyakdehterenok.store.service;

import by.beglyakdehterenok.store.dto.StorageDto;
import by.beglyakdehterenok.store.entity.Clothing;
import by.beglyakdehterenok.store.entity.Storage;
import by.beglyakdehterenok.store.mapper.StorageMapperImpl;
import by.beglyakdehterenok.store.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {

    private final StorageMapperImpl storageMapper;
    private final StorageRepository storageRepository;

    @Autowired
    public StorageService(StorageMapperImpl storageMapper, StorageRepository storageRepository) {
        this.storageMapper = storageMapper;
        this.storageRepository = storageRepository;
    }

    @Transactional
    public List<StorageDto> findAll(){
        return storageRepository.findAll().stream()
                .map(storage -> storageMapper.mapFrom(storage))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteClothingFromStorage(Long id){
        storageRepository.deleteById(id);
    }

    @Transactional
    public Storage findById(Long id){
        return storageRepository.findById(id).get();
    }

    @Transactional
    public Clothing findClothingById(Long id){
        return storageRepository.findByClothing_Id(id);
    }

}
