package by.beglyakdehterenok.store.service;


import by.beglyakdehterenok.store.dto.ClothingDto;
import by.beglyakdehterenok.store.entity.Brand;
import by.beglyakdehterenok.store.entity.Category;
import by.beglyakdehterenok.store.entity.Clothing;
import by.beglyakdehterenok.store.entity.Size;
import by.beglyakdehterenok.store.mapper.ClothingMapperImpl;
import by.beglyakdehterenok.store.repository.BrandRepository;
import by.beglyakdehterenok.store.repository.CategoryRepository;
import by.beglyakdehterenok.store.repository.ClothingRepository;
import by.beglyakdehterenok.store.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClothingService{

    private final ClothingRepository clothingRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ClothingMapperImpl clothingMapper;

    @Autowired
    public ClothingService(ClothingRepository clothingRepository, CategoryRepository categoryRepository, BrandRepository brandRepository, ClothingMapperImpl clothingMapper) {
        this.clothingRepository = clothingRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.clothingMapper = clothingMapper;
    }

    @Transactional
    public Clothing addNewClothing(Clothing clothing) {

        Brand brand = brandRepository.getOne(clothing.getBrand().getId());
        Category category = categoryRepository.getOne(clothing.getCategory().getId());
        clothing.setCategory(category);
        clothing.setBrand(brand);
        return clothingRepository.save(clothing);
    }

    @Transactional
    public List<Clothing> findAll(){
        return clothingRepository.findAll();
    }

    @Transactional
    public Clothing findById(Long id){
        return clothingRepository.getOne(id);
    }

    @Transactional
    public ClothingDto findClothingDtoById(Long id){
        return clothingMapper.mapFrom(clothingRepository.getOne(id));
    }

    @Transactional
    public void deleteClothing(Long id){
        clothingRepository.deleteById(id);
    }

    @Transactional
    public List<Size> findAllSizesByClothingName(String name){
        return clothingRepository.findAllSizesByClothing(name);
    }

    @Transactional
    public List<ClothingDto> findAllAndGroupByName(){
       return clothingRepository.findAllAndGroupByName().stream()
                .map(clothing -> clothingMapper.mapFrom(clothing))
               .collect(Collectors.toList());
    }

    @Transactional
    public List<ClothingDto> findAllByCategory(String name){
        return clothingRepository.findAllByCategory(name).stream()
                .map(clothing -> clothingMapper.mapFrom(clothing))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ClothingDto> findAllByBrand(String name){
        return clothingRepository.findAllByBrand(name).stream()
                .map(clothing -> clothingMapper.mapFrom(clothing))
                .collect(Collectors.toList());
    }

    public Page<Clothing> getAllPageable(int pageNumber,String sortField, String sortDir,String keyword){
        Sort sort = Sort.by(sortField);
        sort=sortDir.equals("asc") ? sort.ascending() : sort.descending();

        Pageable pageable=PageRequest.of(pageNumber-1, 5, sort);

        if (keyword!=null){
            return clothingRepository.findAll(keyword,pageable);
        }

        return clothingRepository.findAll(pageable);

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
