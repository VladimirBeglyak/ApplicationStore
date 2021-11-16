package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.dto.CategoryDto;
import by.beglyakdehterenok.store.dto.StorageDto;
import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.service.BrandService;
import by.beglyakdehterenok.store.service.CategoryService;
import by.beglyakdehterenok.store.service.ClothingService;
import by.beglyakdehterenok.store.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/catalog")
//@SessionAttributes("sortType")
public class CatalogController {

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ClothingService clothingService;
    private final StorageService storageService;

    @Autowired
    public CatalogController(BrandService brandService, CategoryService categoryService, ClothingService clothingService, StorageService storageService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.clothingService = clothingService;
        this.storageService = storageService;
    }

    @ModelAttribute
    public void addNewAttributes(Model model){
        Category category = new Category();
        Brand brand = new Brand();
        List<StorageDto> storages = storageService.findAll();
        List<Storage> allClothing = clothingService.findAll();
        List<Category> allCategories = categoryService.findAll();
        List<Brand> allBrands = brandService.findAll();
        Size[] sizes = Size.values();
        model.addAttribute("allClothing",allClothing);
        model.addAttribute("allSizes",sizes);
        model.addAttribute("allStorage",storages);
        model.addAttribute("allCategories",allCategories);
        model.addAttribute("allBrands",allBrands);
        model.addAttribute("newBrand",brand);
        model.addAttribute("newCategory",category);
    }

    @GetMapping("/")
    public String showMainPageCatalog(){

        return "shop";
    }

    @GetMapping("/all")
    public String showCatalog(){
        System.out.println(storageService.findAll());
        if (storageService.findAll().isEmpty()){
            return "redirect:/clothes/add";
        } else {
            return "catalog";
        }
//        return "catalog";
    }

    @GetMapping("/add-new-brand")
    public String addNewBrand(){
        return "brand-add";
    }

    @PostMapping("/save-new-brand")
    public String saveNewBrand(@ModelAttribute Brand brand){
        brandService.save(brand);
        return "redirect:/clothes/add";
    }

    @GetMapping("/add-new-category")
    public String addNewCategory(ModelAndView modelAndView){
        return "category-add";
    }

    @PostMapping("/save-new-category")
    public String saveNewCategory(@ModelAttribute Category category){
        categoryService.save(category);
        return "redirect:/clothes/add";
    }

    @RequestMapping("/delete")
    public String deleteClothingFromStorage(@RequestParam("clothingId") Long id){
        storageService.deleteClothingFromStorage(id);
        return "redirect:/catalog/all";
    }

    @RequestMapping("/update")
    public String updateInfo(@RequestParam("clothingId") Long id,Model model){
        Clothing clothing = storageService.findClothingById(id);
        model.addAttribute("newClothing",clothing);
        return "clothes-update";
    }
    //Misha*******************************

//    private final ClothingService clothingService;
//
//    @Autowired
//    public CatalogController(ClothingService clothingService) {
//        this.clothingService = clothingService;
//    }
//
//    @ModelAttribute
//    public void addCategoriesBrandsSizes(Model model) {
//        int countOfAllClothing = clothingService.countAll();
//        List<BrandDto> allBrandsDto = new BrandService().findAll().stream()
//                .map(Brand::createDto).collect(Collectors.toList());
//        List<CategoryDto> allCategoriesDto = new CategoryService().findAll().stream()
//                .map(Category::createDto).collect(Collectors.toList());
//        Size[] allSizes = Size.values();
//
//        model.addAttribute("countOfAllClothing", countOfAllClothing);
//        model.addAttribute("allBrands", allBrandsDto);
//        model.addAttribute("allCategories", allCategoriesDto);
//        model.addAttribute("allSizes", allSizes);
//    }
//
//    //формирование каталога по категории, бренду, размеру, строке поиска, сотрировке и номеру страницы
//    @GetMapping("/catalog")
//    public ModelAndView showCatalogPageByCategories(@RequestParam() Map<String, String> allParams,
//                                                    @SessionAttribute("sortType") String currentSortingType,
//                                                    ModelAndView model) {
//        //если в парметрах есть сортировка то меняем текущую и кидаем в сессиию
//        if (allParams.containsKey("sortType")) {
//            model.addObject("sortType", allParams.get("sortType"));
//        }
//        List<Clothing> clothingList = null;
//        //далее сортируем в зависимости от категории
//        if (allParams.containsKey("category")) {
//            String categoryName = allParams.get("category");
//            if (categoryName.equals("All")) {
//                clothingList = currentSortingType.equals("desc") ?
//                        clothingService.findAllByOrderByPriceDesc() :
//                        clothingService.findAllByOrderByPriceAsc();
//            } else {
//                clothingList = currentSortingType.equals("desc") ?
//                        clothingService.findAllByCategoryNameOrderByPriceDesc(categoryName) :
//                        clothingService.findAllByCategoryNameOrderByPriceAsc(categoryName);
//            }
//        } else if (allParams.containsKey("brand")) {
//            String brandName = allParams.get("brand");
//            clothingList = currentSortingType.equals("desc") ?
//                    clothingService.findAllByBrandNameOrderByPriceDesc(brandName) :
//                    clothingService.findAllByBrandNameOrderByPriceAsc(brandName);
//        } else if (allParams.containsKey("size")) {
//            String sizeName = allParams.get("size");
//            clothingList = currentSortingType.equals("desc") ?
//                    clothingService.findAllBySizeNameOrderByPriceDesc(sizeName) :
//                    clothingService.findAllBySizeNameOrderByPriceAsc(sizeName);
//        } else if (allParams.containsKey("search")) {
//            String st = allParams.get("search");
//            clothingList = currentSortingType.equals("desc") ?
//                    clothingService.findAllByNameIgnoreCaseContainingOrderByPriceDesc(st) :
//                    clothingService.findAllByNameIgnoreCaseContainingOrderByPriceAsc(st);
//        }
//        return clothingList != null ? getModelAndViewForCatalogPage(
//                Integer.parseInt(allParams.get("page")), model, clothingList) :
//                new ModelAndView("/error404");
//    }
//
//    //формирование модели для страницы каталога, на странице будет по 6 элементов одежды
//    private ModelAndView getModelAndViewForCatalogPage(@RequestParam("page") int pageNumber,
//                                                       ModelAndView model,
//                                                       List<Clothing> clothingList) {
//        int listSize = clothingList.size();
//        int numberOfPages = listSize % 6 == 0 ? listSize / 6 : listSize / 6 + 1;
//        if (pageNumber > numberOfPages) {
//            return new ModelAndView("/error404");
//        }
//        List<Clothing> resultList = getClothingListAccordingToPageNumber(pageNumber, clothingList);
//
//        model.addObject("numberOfPages", numberOfPages);
//        model.addObject("resultList", resultList);
//        model.setViewName("catalog");
//        return model;
//    }
//
//    //формирование списка вещей в зависимости от номера страницы, на странице будет по 6 элементов одежды
//    private List<Clothing> getClothingListAccordingToPageNumber(int pageNumber, List<Clothing> clothingList) {
//        int listSize = clothingList.size();
//        int numberOfPages = listSize % 6 == 0 ?
//                listSize / 6 : (listSize / 6) + 1;
//        int indexOfFirstElement = (pageNumber - 1) * 6;
//        List<Clothing> resultList = new ArrayList<>();
//        if (pageNumber == numberOfPages) {
//            for (int i = indexOfFirstElement; i < listSize; i++) {
//                resultList.add(clothingList.get(i));
//            }
//        } else {
//            for (int i = indexOfFirstElement; i < indexOfFirstElement + 6; i++) {
//                resultList.add(clothingList.get(i));
//            }
//        }
//        return resultList;
//    }
//

}
