package by.beglyakdehterenok.store.service;


import by.beglyakdehterenok.store.entity.Brand;
import by.beglyakdehterenok.store.entity.Category;
import by.beglyakdehterenok.store.entity.Clothing;
import by.beglyakdehterenok.store.entity.Storage;
import by.beglyakdehterenok.store.repository.BrandRepository;
import by.beglyakdehterenok.store.repository.CategoryRepository;
import by.beglyakdehterenok.store.repository.ClothingRepository;
import by.beglyakdehterenok.store.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClothingService{

    private final ClothingRepository clothingRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final StorageRepository storageRepository;

    @Autowired
    public ClothingService(ClothingRepository clothingRepository, CategoryRepository categoryRepository, BrandRepository brandRepository, StorageRepository storageRepository) {
        this.clothingRepository = clothingRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.storageRepository = storageRepository;
    }

    @Transactional
    public void addNewClothingToStorage(Long brandId,Long categoryId,Clothing clothing,Long count){
        Brand brand = brandRepository.getOne(brandId);
        Category category = categoryRepository.getOne(categoryId);
        Storage storage = new Storage();
        clothing.addCategory(category);
        clothing.setBrand(brand);
        storage.setCount(count);
        storage.setClothing(clothing);
        storageRepository.save(storage);
    }

    @Transactional
    public List<Storage> findAll(){
        List<Storage> storages = storageRepository.findAll();
        List<Clothing> clothing = storages.stream()
                .map(storage1 -> storage1.getClothing())
                .collect(Collectors.toList());
        return storages;
    }


//
//    public int countAll(){
//        return (int) clothingRepository.count();
//    }
//
//    public Clothing findById(Long id){
//        return clothingRepository.findById(id).get();
//    }
//
//    //выборка по всей одежде
//    public List<Clothing> findAllByOrderByPriceAsc(){
//        return clothingRepository.findAllByOrderByPriceAsc();
//    }
//    public List<Clothing> findAllByOrderByPriceDesc(){
//        return clothingRepository.findAllByOrderByPriceDesc();
//    }
//
//
//    //выборка по категории
//    public List<Clothing> findAllByCategoryNameOrderByPriceAsc(String name){
//        by.beglyak.investor.service.CategoryService categoryService = new by.beglyak.investor.service.CategoryService();
//        return clothingRepository.findAllByCategoriesContainingOrderByPriceAsc(categoryService.findByName(name));
//    }
//    public List<Clothing> findAllByCategoryNameOrderByPriceDesc(String name){
//        by.beglyak.investor.service.CategoryService categoryService = new by.beglyak.investor.service.CategoryService();
//        return clothingRepository.findAllByCategoriesContainingOrderByPriceDesc(categoryService.findByName(name));
//    }
//
//
//    //выборка по бренду
//    public List<Clothing> findAllByBrandNameOrderByPriceAsc(String name){
//        return clothingRepository.findAllByBrand_NameOrderByPriceAsc(name);
//    }
//    public List<Clothing> findAllByBrandNameOrderByPriceDesc(String name){
//        return clothingRepository.findAllByBrand_NameOrderByPriceDesc(name);
//    }
//
//
//    //выборка по размеру
//    public List<Clothing> findAllBySizeNameOrderByPriceAsc(String sizeName){
//        return clothingRepository.findAllBySizesContainingOrderByPriceAsc(Size.valueOf(sizeName));
//    }
//    public List<Clothing> findAllBySizeNameOrderByPriceDesc(String sizeName){
//        return clothingRepository.findAllBySizesContainingOrderByPriceDesc(Size.valueOf(sizeName));
//    }
//
//
//    //методы для строки поиска по названию
//    public List<Clothing> findAllByNameIgnoreCaseContainingOrderByPriceAsc(String st){
//        return clothingRepository.findAllByNameIgnoreCaseContainingOrderByPriceAsc(st);
//    }
//    public List<Clothing> findAllByNameIgnoreCaseContainingOrderByPriceDesc(String st){
//        return clothingRepository.findAllByNameIgnoreCaseContainingOrderByPriceDesc(st);
//    }
//
//

}
