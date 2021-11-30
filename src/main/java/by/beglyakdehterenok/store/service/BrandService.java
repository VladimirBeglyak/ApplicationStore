package by.beglyakdehterenok.store.service;

import by.beglyakdehterenok.store.dto.BrandDto;
import by.beglyakdehterenok.store.dto.CategoryDto;
import by.beglyakdehterenok.store.entity.Brand;
import by.beglyakdehterenok.store.mapper.BrandMapperImpl;
import by.beglyakdehterenok.store.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService implements BaseService<Brand,Long>{

    private final BrandRepository brandRepository;
    private final BrandMapperImpl brandMapper;

    public BrandService(BrandRepository brandRepository, BrandMapperImpl brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }


    @Override
    public List<Brand> findAll(){
        return brandRepository.findAll();
    }

    @Override
    public void save(Brand brand) {
        brandRepository.saveAndFlush(brand);
    }

    @Override
    public Optional<Brand> findById(Long id){
        return brandRepository.findById(id);
    }

    public List<BrandDto> findAllBrandsDto(){
        return brandRepository.findAll().stream()
                .map(category -> brandMapper.mapFrom(category))
                .collect(Collectors.toList());
    }
}
