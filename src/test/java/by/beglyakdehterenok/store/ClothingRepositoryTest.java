package by.beglyakdehterenok.store;

import by.beglyakdehterenok.store.config.DatabaseConfig;
import by.beglyakdehterenok.store.config.WebConfig;
import by.beglyakdehterenok.store.config.WebSecurityConfig;
import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.mapper.StorageMapperImpl;
import by.beglyakdehterenok.store.repository.*;
import by.beglyakdehterenok.store.service.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClothingRepositoryTest {

    @Autowired
    private StorageService storageService;

    @Autowired
    private StorageMapperImpl storageMapper;

    @Autowired
    private ClothingRepository clothingRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Test
    public void addNewClothing(){
        Category category = categoryRepository.findByName("Shoes");
        Brand brand = brandRepository.findByName("Adidas");

        Clothing clothing = new Clothing();
        clothing.setName("Adidas DRose");
        clothing.setPrice(100);
        clothing.setDescription("sdfghjk");
        clothing.setImagePath("/fghfgh/fghfgh");
        clothing.addCategory(category);
        clothing.addSize(Size.XS);
        clothing.setBrand(brand);

        System.out.println(clothing);

        Storage storage = new Storage();
        storage.setClothing(clothing);
        storage.setCount(10L);

        System.out.println(storage);

        storageRepository.save(storage);
    }

    @Test
    public void deleteClothingFromStorage(){
        storageRepository.deleteById(2L);
    }

    @Test
    public void allActivitiesWithBrands(){
//        Brand brand = new Brand();
//        brand.setName("Adidas");
//        brandRepository.save(brand);

        brandRepository.findAll()
                .forEach(someBrand -> System.out.println(someBrand));

        Brand repositoryOne = brandRepository.findById(1L).get();
        System.out.println(repositoryOne);
    }

    @Test
    public void allActivitiesWithCategories(){
        Category category = new Category();
        category.setName("Shoes");
        categoryRepository.save(category);

        categoryRepository.findAll()
                .forEach(someCategory -> System.out.println(someCategory));


    }

    @Test
    @Transactional
    public void showAllStorage(){
        storageService.findAll()
        .forEach(storageDto -> System.out.println(storageDto));
    }
}
