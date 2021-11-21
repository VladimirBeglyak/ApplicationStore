package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.dto.BrandDto;
import by.beglyakdehterenok.store.dto.CategoryDto;
import by.beglyakdehterenok.store.dto.ClothingDto;
import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.mapper.ClothingMapperImpl;
import by.beglyakdehterenok.store.service.BrandService;
import by.beglyakdehterenok.store.service.CategoryService;
import by.beglyakdehterenok.store.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/catalog")
//@SessionAttributes("sortType")
public class CatalogController {

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ClothingService clothingService;
    private final ClothingMapperImpl clothingMapper;

    @Autowired
    public CatalogController(BrandService brandService, CategoryService categoryService, ClothingService clothingService, ClothingMapperImpl clothingMapper) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.clothingService = clothingService;
        this.clothingMapper = clothingMapper;
    }

    @ModelAttribute
    public void addAttributes(Model model) {

        Brand brand = new Brand();
        Category category = new Category();
        List<ClothingDto> allClothing = clothingService.findAllAndGroupByName();
        List<CategoryDto> allCategories = categoryService.findAllCategoriesDto();
        List<BrandDto> allBrands = brandService.findAllBrandsDto();
        Size[] sizes = Size.values();

        model.addAttribute("allSeasons", Season.values());
        model.addAttribute("allTypes", Type.values());
        model.addAttribute("allClothing", allClothing);
        model.addAttribute("allSizes", sizes);
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("allBrands", allBrands);
        model.addAttribute("newBrand", brand);
        model.addAttribute("newCategory", category);
        model.addAttribute("newClothing", new Clothing());
    }

    @GetMapping("/")
    public String showMainPageCatalog() {
        return "shop";
    }

    @GetMapping("/add")
//    @PreAuthorize("hasAuthority('admin:write')")
    public String addNewClothingToCatalog() {
        return "clothing-info";
    }

//    @GetMapping("/all")
//    public String showCatalog(){
//        System.out.println(storageService.findAll());
//        if (storageService.findAll().isEmpty()){
//            return "redirect:/catalog/add";
//        } else {
//            return "catalog";
//        }

//    }

    @GetMapping("/add-new-brand")
    public String addNewBrand() {
        return "brand-add";
    }

    @PostMapping("/save-new-brand")
    public String saveNewBrand(@ModelAttribute Brand brand) {
        brandService.save(brand);
        return "redirect:/catalog/add";
    }

    @GetMapping("/add-new-category")
    public String addNewCategory(ModelAndView modelAndView) {
        return "category-add";
    }

    @PostMapping("/save-new-category")
    public String saveNewCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/catalog/add";
    }

    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('account:write')")
    public String saveNewClothingToCatalog(@Valid @ModelAttribute("newClothing") Clothing clothing,
                                           BindingResult bindingResult
//                                           @RequestParam("countOfClothing") Long countOfClothing
//                                          @RequestParam("img") MultipartFile multipartFile
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getErrorCount());
            System.out.println(clothing);
            return "clothing-info";
        }
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        clothing.setImagePath(fileName);
        System.out.println(clothing);

        clothingService.addNewClothing(clothing);

//        Clothing savedClothing = clothingService.addNewClothingToStorage(clothing, countOfClothing);
//        String uploadDir = "clothing-photos/" + savedClothing.getId();
//        FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);

        return "redirect:/catalog/all";
    }

    @GetMapping("/delete")
//    @PreAuthorize("hasAuthority('account:write')")
    public String deleteClothingFromStorage(@RequestParam("clothingId") Long id) {
        System.out.println("id = " + id);
        clothingService.deleteClothing(id);
        return "forward:/catalog/all";
    }

    @RequestMapping("/update")
//    @PreAuthorize("hasAuthority('account:write')")
    public String updateInfo(@RequestParam("clothingId") Long id, Model model) {
        ClothingDto clothing = clothingService.findClothingDtoById(id);
        System.out.println(clothing);
        model.addAttribute("newClothing", clothing);
        return "clothing-info";
    }

    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('account:write')")
    public String showAllCatalogForManagerAndAdmin(Model model) {

        List<Clothing> storageServiceAll = clothingService.findAll();

        System.out.println(storageServiceAll);
        model.addAttribute("allClothingOnStorage", storageServiceAll);
        return "catalog-all";
    }

    @GetMapping("/{id}")
    public String showDetails(@PathVariable("id") Long id,Model model){
        ClothingDto clothing = clothingService.findClothingDtoById(id);
        List<Size> allSizesByClothingName = clothingService.findAllSizesByClothingName(clothing.getName());

        model.addAttribute("allSizesByClothingName",allSizesByClothingName);
        model.addAttribute("clothingDetails",clothing);

        return "shop-details";
    }

//    @GetMapping("/sort")
//    public ModelAndView sortByCategory(@RequestParam("category") String name){
//
//    }
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
