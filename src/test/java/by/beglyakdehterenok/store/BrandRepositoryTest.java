package by.beglyakdehterenok.store;

import by.beglyakdehterenok.store.entity.Brand;
import by.beglyakdehterenok.store.repository.BrandRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class BrandRepositoryTest extends BaseTest<Brand>{

    @Autowired
    private BrandRepository brandRepository;

    @Test
    @Override
    List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Test
    @Override
    void save() {
        Brand brand = new Brand();

        brandRepository.save(brand);
    }

    @Test
    @Override
    void delete() {
        brandRepository.deleteById(1L);
    }

    @Test
    @Override
    Optional<Brand> findById() {
        return brandRepository.findById(1L);
    }

    @Test
    @Override
    @Transactional
    void update() {
        Brand brand = brandRepository.findById(1L).get();
        brand.setName("");
        brandRepository.saveAndFlush(brand);
    }
}
