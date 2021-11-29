package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.dto.BrandDto;
import by.beglyakdehterenok.store.dto.CategoryDto;
import by.beglyakdehterenok.store.dto.ClothingDto;
import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.service.AccountService;
import by.beglyakdehterenok.store.service.BrandService;
import by.beglyakdehterenok.store.service.CategoryService;
import by.beglyakdehterenok.store.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@MultipartConfig
@RequestMapping("/catalog")
public class ClothingController {

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ClothingService clothingService;
    private final AccountService accountService;

    @Autowired
    public ClothingController(BrandService brandService, CategoryService categoryService, ClothingService clothingService, AccountService accountService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.clothingService = clothingService;
        this.accountService = accountService;
    }


    @ModelAttribute
    public void addAttributes(Model model) {

        Order order = new Order();
        List<CategoryDto> allCategories = categoryService.findAllCategoriesDto();
        List<BrandDto> allBrands = brandService.findAllBrandsDto();
        Size[] sizes = Size.values();


        model.addAttribute("allSeasons", Season.values());
        model.addAttribute("allTypes", Type.values());
        model.addAttribute("allSizes", sizes);
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("allBrands", allBrands);
        model.addAttribute("newOrder", order);

    }

    @GetMapping("/")
    public String showMainPageCatalog(Model model, String keyword) {
        return listCatalogForUser(1, "name", "asc", keyword, 6, model);
    }


    @GetMapping("/page/{pageNumber}")
    public String listCatalogForUser(@PathVariable("pageNumber") int currentPage,
                                     @Param("sortField") String sortField,
                                     @Param("sortDir") String sortDir,
                                     @Param("keyword") String keyword,
                                     @Param("size") int size,
                                     Model model) {

        Page<Clothing> page = clothingService.getAllPageableCatalogForUser(currentPage, sortField, sortDir, keyword, size);
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
        model.addAttribute("size", size);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir", reverseSortDir);

        return "shop";
    }


    @GetMapping("/add")
    public String addNewClothingToCatalog(Model model) {
        model.addAttribute("newClothing", new Clothing());
        return "clothing-info";
    }


    @GetMapping("/add-new-brand")
    public String addNewBrand(Model model) {
        model.addAttribute("newBrand", new Brand());
        return "brand-add";
    }

    @PostMapping("/save-new-brand")
    public String saveNewBrand(@ModelAttribute Brand brand) {
        brandService.save(brand);
        return "redirect:/catalog/add";
    }

    @GetMapping("/add-new-category")
    public String addNewCategory(Model model) {
        model.addAttribute("newCategory", new Category());
        return "category-add";
    }

    @PostMapping("/save-new-category")
    public String saveNewCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/catalog/add";
    }

    @PostMapping("/save")
    public String saveNewClothingToCatalog(@RequestParam("photo") MultipartFile multipartFile,
                                           @Valid Clothing clothing,
                                           BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getErrorCount());
            System.out.println(clothing);
            return "clothing-info";
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a valid file");
        }

        try {
            clothing.setImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        clothingService.addNewClothing(clothing);
        return "redirect:/catalog/all";
    }

    @GetMapping("/delete")
    public String deleteClothingFromStorage(@RequestParam("clothingId") Long id) {
        System.out.println("id = " + id);
        clothingService.deleteClothing(id);
        return "forward:/catalog/all";
    }

    @RequestMapping("/update")
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
    public String showAllCatalog(Model model, String keyword) {
        return listByPage(1, "name", "asc", keyword, 5, model);
    }


    @GetMapping("/all/page/{pageNumber}")
    public String listByPage(@PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword,
                             @Param("size") int size,
                             Model model) {

        Page<Clothing> page = clothingService.getAllPageable(currentPage, sortField, sortDir, keyword, size);
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
        model.addAttribute("size", size);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir", reverseSortDir);

        return "catalog-all";
    }

}
