package by.beglyakdehterenok.store;

import by.beglyakdehterenok.store.config.DatabaseConfig;
import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.repository.*;
import by.beglyakdehterenok.store.service.ClothingService;
import by.beglyakdehterenok.store.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


public class ClothingRepositoryTest extends BaseTest<Clothing> {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClothingService clothingService;

    @Autowired
    private ClothingRepository clothingRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    List<Clothing> findAll() {
        return clothingRepository.findAll();
    }

    @Test
    @Override
    void save(){
        Clothing clothing = new Clothing();
        Category category = categoryRepository.findByName("Shoes");
        Brand brand = brandRepository.findByName("Adidas");

        clothing.setName("Adidas DRose");
        clothing.setPrice(100d);
        clothing.setDescription("sdfghjk");
        clothing.setImagePath("/fghfgh/fghfgh");
        clothing.setCategory(category);
        clothing.setSize(Size.XS);
        clothing.setBrand(brand);
        System.out.println(clothing);

    }

    @Test
    @Override
    void delete(){
        clothingRepository.deleteById(1L);
    }

    @Test
    @Override
    Optional<Clothing> findById() {
        return clothingRepository.findById(1L);
    }

    @Test
    @Override
    public void update(){
        Clothing clothing = clothingRepository.findById(2L).get();
        System.out.println(clothing);
        clothing.setPrice(500d);
        clothingRepository.saveAndFlush(clothing);

    }
}
