package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.dto.BrandDto;
import by.beglyakdehterenok.store.dto.CategoryDto;
import by.beglyakdehterenok.store.dto.ClothingDto;
import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.mapper.ClothingMapperImpl;
import by.beglyakdehterenok.store.service.BrandService;
import by.beglyakdehterenok.store.service.CategoryService;
import by.beglyakdehterenok.store.service.ClothingService;
import by.beglyakdehterenok.store.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@MultipartConfig
@RequestMapping("/catalog")
public class ClothingController {

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ClothingService clothingService;

    @Autowired
    public ClothingController(BrandService brandService, CategoryService categoryService, ClothingService clothingService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.clothingService = clothingService;
    }


    @ModelAttribute
    public void addAttributes(Model model) {

        Brand brand = new Brand();
        Category category = new Category();
        Order order = new Order();
        List<CategoryDto> allCategories = categoryService.findAllCategoriesDto();
        List<BrandDto> allBrands = brandService.findAllBrandsDto();
        Size[] sizes = Size.values();

        model.addAttribute("allSeasons", Season.values());
        model.addAttribute("allTypes", Type.values());
        model.addAttribute("allSizes", sizes);
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("allBrands", allBrands);
        model.addAttribute("newBrand", brand);
        model.addAttribute("newCategory", category);
        model.addAttribute("newClothing", new Clothing());
        model.addAttribute("newOrder", order);

    }

    @GetMapping("/")
//    @PreAuthorize("hasAuthority('account:write')")
    public String showMainPageCatalog(Model model,String keyword) {
        return listCatalogForUser(1, "name", "asc", keyword,6, model);
    }


    @GetMapping("/page/{pageNumber}")
    public String listCatalogForUser(@PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword,
                             @Param("size") int size,
                             Model model) {

        Page<Clothing> page = clothingService.getAllPageableCatalogForUser(currentPage, sortField, sortDir, keyword,size);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Clothing> allClothing = page.getContent();

        model.addAttribute("allClothing", allClothing);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("size",size);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir", reverseSortDir);

        return "shop";
    }


    @GetMapping("/add")
//    @PreAuthorize("hasAuthority('admin:write')")
    public String addNewClothingToCatalog() {
        return "clothing-info";
    }


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
                                           BindingResult bindingResult,
                                           @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getErrorCount());
            System.out.println(clothing);
            return "clothing-info";
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); //получить имя файла
        clothing.setImagePath(fileName);
        Clothing savedClothing = clothingService.addNewClothing(clothing);
        String uploadDir = "src/main/webapp/resources/img/clothing-photos/" + savedClothing.getId();
        FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
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

    @GetMapping("/{id}")
    public String showDetails(@PathVariable("id") Long id, Model model) {
        ClothingDto clothing = clothingService.findClothingDtoById(id);
        List<Size> allSizesByClothingName = clothingService.findAllSizesByClothingName(clothing.getName());

        model.addAttribute("allSizesByClothingName", allSizesByClothingName);
        model.addAttribute("clothingDetails", clothing);

        return "shop-details";
    }

    @GetMapping("/sort/category/{name}")
    public String showCatalogByCategory(@PathVariable("name") String categoryName, Model model) {
        List<ClothingDto> clothingDtoList = clothingService.findAllByCategory(categoryName);
        model.addAttribute("allClothing", clothingDtoList);
        return "shop";
    }

    @GetMapping("/sort/brand/{name}")
    public String showCatalogByBrand(@PathVariable("name") String brandName, Model model) {
        List<ClothingDto> clothingDtoList = clothingService.findAllByBrand(brandName);
        model.addAttribute("allClothing", clothingDtoList);
        return "shop";
    }

    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('account:write')")
    public String showAllCatalog(Model model,String keyword) {
        return listByPage(1, "name", "asc", keyword,5, model);
    }


    @GetMapping("/all/page/{pageNumber}")
    public String listByPage(@PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword,
                             @Param("size") int size,
                             Model model) {

        Page<Clothing> page = clothingService.getAllPageable(currentPage, sortField, sortDir, keyword,size);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Clothing> listClothing = page.getContent();

        model.addAttribute("listClothing", listClothing);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("size",size);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir", reverseSortDir);

        return "catalog-all";
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
