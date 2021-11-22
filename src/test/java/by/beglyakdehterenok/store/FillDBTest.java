package by.beglyakdehterenok.store;

import by.beglyakdehterenok.store.config.DatabaseConfig;
import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.mapper.BrandMapperImpl;
import by.beglyakdehterenok.store.mapper.CategoryMapperImpl;
import by.beglyakdehterenok.store.repository.*;
import by.beglyakdehterenok.store.service.AccountService;
import by.beglyakdehterenok.store.service.ClothingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FillDBTest {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ClothingRepository clothingRepository;

    @Autowired
    private ClothingService clothingService;

    @Autowired
    private CategoryMapperImpl categoryMapper;

    @Autowired
    private BrandMapperImpl brandMapper;

    @Test
    public void saveAccounts(){
        List<Account> accounts = List.of(
                new Account("Ivan", "Ivanov", Role.USER, Gender.MALE, new Address("Belarus", "Minsk", "Lenina", "14"), LocalDate.of(1986, 10, 20), 100, "+375295874152", "test12@mail.ru", "ivan", "ivan", new ArrayList<Order>()),
                new Account("Petr", "Petrov", Role.USER, Gender.MALE, new Address("Belarus", "Grodno", "Zvezdnaya", "15"), LocalDate.of(1996, 5, 15), 110, "+375336478525", "test2@mail.ru", "petr", "petr", new ArrayList<Order>()),
                new Account("Max", "Maximov", Role.USER, Gender.MALE, new Address("Belarus", "Minsk", "Nekrasova", "30"), LocalDate.of(2000, 1, 1), 50, "+375291234578", "test3@mail.ru", "max", "max", new ArrayList<Order>()),
                new Account("Sveta", "Svetikova", Role.USER, Gender.FEMALE, new Address("Belarus", "Vitebsk", "Lenina", "110"), LocalDate.of(1998, 2, 25), 400, "+375256984578", "test4@mail.ru", "sveta", "sveta", new ArrayList<Order>()),
                new Account("Alex", "Ivanov", Role.USER, Gender.MALE, new Address("Belarus", "Minsk", "Kozlova", "20"), LocalDate.of(1987, 10, 15), 800, "+375292584536", "test5@mail.ru", "alex", "alex", new ArrayList<Order>()),
                new Account("Michael", "Bubnov", Role.MANAGER, Gender.MALE, new Address("Belarus", "Gomel", "Pushkina", "18"), LocalDate.of(1990, 3, 8), 200, "+375292581476", "test6@mail.ru", "mike", "mike", new ArrayList<Order>()),
                new Account("Admin", "Admin", Role.ADMIN, Gender.MALE, new Address("Belarus", "Gomel", "Lenina", "2"), LocalDate.of(1950, 3, 8), 0, "+375299361236", "admin@mail.ru", "admin", "admin", new ArrayList<Order>())
        );

        accounts.stream()
                .forEach(account -> accountService.save(account));

    }

    @Test
    public void saveBrands(){
        List.of(
                new Brand("Nike"),
                new Brand("Adidas"),
                new Brand("Mark Formel"),
                new Brand("Burvin"),
                new Brand("Apti Eziev"),
                new Brand("Gucci"),
                new Brand("D&G"),
                new Brand("Calvin Klein"),
                new Brand("Tommy Hilfiger"),
                new Brand("Huggo Boss"),
                new Brand("Puma"),
                new Brand("Air Jordan"),
                new Brand("Givehchy"),
                new Brand("Prada")
        ).stream().forEach(brand -> brandRepository.save(brand));
    }

    @Test
    public void saveCategories(){
        List.of(
                new Category("Shoes"),
                new Category("Dress"),
                new Category("T-shirt"),
                new Category("Cardigan"),
                new Category("Shirt"),
                new Category("Suit"),
                new Category("Shorts"),
                new Category("Trousers"),
                new Category("Coat"),
                new Category("Gloves"),
                new Category("Belt"),
                new Category("Socks"),
                new Category("Sneakers"),
                new Category("Tie")
        ).stream().forEach(category -> categoryRepository.save(category));
    }

    @Test
    @Transactional
    public void saveClothes(){

        Category suit = categoryRepository.findByName("Suit");
        Category sneakers = categoryRepository.findByName("Sneakers");
        Category coat = categoryRepository.findByName("Coat");

        Brand nike = brandRepository.findByName("Nike");
        Brand tommy_hilfiger = brandRepository.findByName("Tommy Hilfiger");
        Brand DG = brandRepository.findByName("D&G");
        Brand huggo_boss = brandRepository.findByName("Huggo Boss");
        Brand gucci = brandRepository.findByName("Gucci");
        Brand adidas = brandRepository.findByName("Adidas");
        Brand air_jordan = brandRepository.findByName("Air Jordan");

        List<Clothing> clothingList = List.of(


                new Clothing("Nike SportSuit 2021", 200d, "Nike SportSuit Desc", null, suit, Size.XS, nike, Type.MAN, Season.SUMMER, 10L),
                new Clothing("Nike SportSuit 2021", 200d, "Nike SportSuit Desc", null, suit, Size.M, nike, Type.MAN, Season.SUMMER, 15L),
                new Clothing("Nike SportSuit 2021", 200d, "Nike SportSuit Desc", null, suit, Size.XXL, nike, Type.MAN, Season.SUMMER, 20L),

                new Clothing("Tommy Hilfiger Coat", 400d, "Tommy Hilfiger Coat Desc", null, coat, Size.M, tommy_hilfiger, Type.MAN, Season.WINTER, 40L),
                new Clothing("Tommy Hilfiger Coat", 400d, "Tommy Hilfiger Coat Desc", null, coat, Size.S, tommy_hilfiger, Type.MAN, Season.WINTER, 50L),
                new Clothing("Tommy Hilfiger Coat", 400d, "Tommy Hilfiger Coat Desc", null, coat, Size.XXL, tommy_hilfiger, Type.MAN, Season.WINTER, 30L),

                new Clothing("D&G Coat", 200d, "D&G Coat Desc", null, coat, Size.XXL, DG, Type.MAN, Season.WINTER, 20L),
                new Clothing("D&G Coat", 200d, "D&G Coat Desc", null, coat, Size.XS, DG, Type.MAN, Season.WINTER, 10L),
                new Clothing("D&G Coat", 200d, "D&G Coat Desc", null, coat, Size.M, DG, Type.MAN, Season.WINTER, 15L),
                new Clothing("D&G Coat", 200d, "D&G Coat Desc", null, coat, Size.S, DG, Type.MAN, Season.WINTER, 20L),

                new Clothing("Huggo Boss Suit", 500d, "Huggo Boss Suit Desc", null, suit, Size.XXL, huggo_boss, Type.MAN, Season.SUMMER, 50L),
                new Clothing("Huggo Boss Suit", 500d, "Huggo Boss Suit Desc", null, suit, Size.M, huggo_boss, Type.MAN, Season.SUMMER, 20L),
                new Clothing("Huggo Boss Suit", 500d, "Huggo Boss Suit Desc", null, suit, Size.XS, huggo_boss, Type.MAN, Season.SUMMER, 30L),
                new Clothing("Huggo Boss Suit", 500d, "Huggo Boss Suit Desc", null, suit, Size.XL, huggo_boss, Type.MAN, Season.SUMMER, 40L),

                new Clothing("Gucci Suit 2021", 1500d, "Gucci Suit 2021 Desc", null, suit, Size.XL, gucci, Type.MAN, Season.SUMMER, 40L),
                new Clothing("Gucci Suit 2021", 1500d, "Gucci Suit 2021 Desc", null, suit, Size.M, gucci, Type.MAN, Season.SUMMER, 40L),
                new Clothing("Gucci Suit 2021", 1500d, "Gucci Suit 2021 Desc", null, suit, Size.S, gucci, Type.MAN, Season.SUMMER, 40L),
                new Clothing("Gucci Suit 2021", 1500d, "Gucci Suit 2021 Desc", null, suit, Size.XS, gucci, Type.MAN, Season.SUMMER, 40L),

                new Clothing("Adidas DRose 5", 250d, "Adidas DRose 5 Desc", null, sneakers, Size.XL, adidas, Type.MAN, Season.SUMMER, 10L),
                new Clothing("Adidas DRose 5", 250d, "Adidas DRose 5 Desc", null, sneakers, Size.M, adidas, Type.MAN, Season.SUMMER, 20L),
                new Clothing("Adidas DRose 5", 250d, "Adidas DRose 5 Desc", null, sneakers, Size.XXL, adidas, Type.MAN, Season.SUMMER, 30L),

                new Clothing("Air Jordan XX", 320d, "Air Jordan XX Desc", null, sneakers, Size.M, air_jordan, Type.MAN, Season.SUMMER, 25L),
                new Clothing("Air Jordan XX", 320d, "Air Jordan XX Desc", null, sneakers, Size.S, air_jordan, Type.MAN, Season.SUMMER, 25L),
                new Clothing("Air Jordan XX", 320d, "Air Jordan XX Desc", null, sneakers, Size.XS, air_jordan, Type.MAN, Season.SUMMER, 25L)
        );

        clothingList.stream().forEach(clothing -> clothingService.addNewClothing(clothing));
        for (Clothing clothing : clothingList) {
            System.out.println(clothing);
        }
    }


}
