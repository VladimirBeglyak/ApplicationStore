package by.beglyakdehterenok.store;

import by.beglyakdehterenok.store.config.DatabaseConfig;
import by.beglyakdehterenok.store.entity.Clothing;
import by.beglyakdehterenok.store.entity.Season;
import by.beglyakdehterenok.store.entity.Size;
import by.beglyakdehterenok.store.entity.Type;
import by.beglyakdehterenok.store.repository.BrandRepository;
import by.beglyakdehterenok.store.repository.CategoryRepository;
import by.beglyakdehterenok.store.repository.ClothingRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        ClothingRepository clothingRepository = context.getBean("clothingRepository", ClothingRepository.class);
        CategoryRepository categoryRepository = context.getBean("categoryRepository", CategoryRepository.class);
        BrandRepository brandRepository = context.getBean("brandRepository", BrandRepository.class);


//        List<Clothing> clothingList = List.of(
//                new Clothing("Nike SportSuit 2021", 200d, "Nike SportSuit Desc", null, categoryRepository.findByName("Suit"), Size.XS, brandRepository.findByName("Nike"), Type.MAN, Season.SUMMER, 10L),
//                new Clothing("Nike SportSuit 2021", 200d, "Nike SportSuit Desc", null, categoryRepository.findByName("Suit"), Size.M, brandRepository.findByName("Nike"), Type.MAN, Season.SUMMER, 15L),
//                new Clothing("Nike SportSuit 2021", 200d, "Nike SportSuit Desc", null, categoryRepository.findByName("Suit"), Size.XXL, brandRepository.findByName("Nike"), Type.MAN, Season.SUMMER, 20L),
//
//                new Clothing("Tommy Hilfiger Coat", 400d, "Tommy Hilfiger Coat Desc", null, categoryRepository.findByName("Coat"), Size.M, brandRepository.findByName("Tommy Hilfiger"), Type.MAN, Season.WINTER, 40L),
//                new Clothing("Tommy Hilfiger Coat", 400d, "Tommy Hilfiger Coat Desc", null, categoryRepository.findByName("Coat"), Size.S, brandRepository.findByName("Tommy Hilfiger"), Type.MAN, Season.WINTER, 50L),
//                new Clothing("Tommy Hilfiger Coat", 400d, "Tommy Hilfiger Coat Desc", null, categoryRepository.findByName("Coat"), Size.XXL, brandRepository.findByName("Tommy Hilfiger"), Type.MAN, Season.WINTER, 30L),
//
//                new Clothing("D&G Coat", 200d, "D&G Coat Desc", null, categoryRepository.findByName("Coat"), Size.XXL, brandRepository.findByName("D&G"), Type.MAN, Season.WINTER, 20L),
//                new Clothing("D&G Coat", 200d, "D&G Coat Desc", null, categoryRepository.findByName("Coat"), Size.XS, brandRepository.findByName("D&G"), Type.MAN, Season.WINTER, 10L),
//                new Clothing("D&G Coat", 200d, "D&G Coat Desc", null, categoryRepository.findByName("Coat"), Size.M, brandRepository.findByName("D&G"), Type.MAN, Season.WINTER, 15L),
//                new Clothing("D&G Coat", 200d, "D&G Coat Desc", null, categoryRepository.findByName("Coat"), Size.S, brandRepository.findByName("D&G"), Type.MAN, Season.WINTER, 20L),
//
//                new Clothing("Huggo Boss Suit", 500d, "Huggo Boss Suit Desc", null, categoryRepository.findByName("Suit"), Size.XXL, brandRepository.findByName("Huggo Boss"), Type.MAN, Season.SUMMER, 50L),
//                new Clothing("Huggo Boss Suit", 500d, "Huggo Boss Suit Desc", null, categoryRepository.findByName("Suit"), Size.M, brandRepository.findByName("Huggo Boss"), Type.MAN, Season.SUMMER, 20L),
//                new Clothing("Huggo Boss Suit", 500d, "Huggo Boss Suit Desc", null, categoryRepository.findByName("Suit"), Size.XS, brandRepository.findByName("Huggo Boss"), Type.MAN, Season.SUMMER, 30L),
//                new Clothing("Huggo Boss Suit", 500d, "Huggo Boss Suit Desc", null, categoryRepository.findByName("Suit"), Size.XL, brandRepository.findByName("Huggo Boss"), Type.MAN, Season.SUMMER, 40L),
//
//                new Clothing("Gucci Suit 2021", 1500d, "Gucci Suit 2021 Desc", null, categoryRepository.findByName("Suit"), Size.XL, brandRepository.findByName("Gucci"), Type.MAN, Season.SUMMER, 40L),
//                new Clothing("Gucci Suit 2021", 1500d, "Gucci Suit 2021 Desc", null, categoryRepository.findByName("Suit"), Size.M, brandRepository.findByName("Gucci"), Type.MAN, Season.SUMMER, 40L),
//                new Clothing("Gucci Suit 2021", 1500d, "Gucci Suit 2021 Desc", null, categoryRepository.findByName("Suit"), Size.S, brandRepository.findByName("Gucci"), Type.MAN, Season.SUMMER, 40L),
//                new Clothing("Gucci Suit 2021", 1500d, "Gucci Suit 2021 Desc", null, categoryRepository.findByName("Suit"), Size.XS, brandRepository.findByName("Gucci"), Type.MAN, Season.SUMMER, 40L),
//
//                new Clothing("Adidas DRose 5", 250d, "Adidas DRose 5 Desc", null, categoryRepository.findByName("Sneakers"), Size.XL, brandRepository.findByName("Adidas"), Type.MAN, Season.SUMMER, 10L),
//                new Clothing("Adidas DRose 5", 250d, "Adidas DRose 5 Desc", null, categoryRepository.findByName("Sneakers"), Size.M, brandRepository.findByName("Adidas"), Type.MAN, Season.SUMMER, 20L),
//                new Clothing("Adidas DRose 5", 250d, "Adidas DRose 5 Desc", null, categoryRepository.findByName("Sneakers"), Size.XXL, brandRepository.findByName("Adidas"), Type.MAN, Season.SUMMER, 30L),
//
//                new Clothing("Air Jordan XX", 320d, "Air Jordan XX Desc", null, categoryRepository.findByName("Sneakers"), Size.M, brandRepository.findByName("Air Jordan"), Type.MAN, Season.SUMMER, 25L),
//                new Clothing("Air Jordan XX", 320d, "Air Jordan XX Desc", null, categoryRepository.findByName("Sneakers"), Size.S, brandRepository.findByName("Air Jordan"), Type.MAN, Season.SUMMER, 25L),
//                new Clothing("Air Jordan XX", 320d, "Air Jordan XX Desc", null, categoryRepository.findByName("Sneakers"), Size.XS, brandRepository.findByName("Air Jordan"), Type.MAN, Season.SUMMER, 25L)
//        );
//
//        clothingList.stream().forEach(clothing -> clothingRepository.save(clothing));
//        for (Clothing clothing : clothingList) {
//            System.out.println(clothing);
//        }
    }
}
