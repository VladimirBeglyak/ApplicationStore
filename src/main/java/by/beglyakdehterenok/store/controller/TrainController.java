package by.beglyakdehterenok.store.controller;


import by.beglyakdehterenok.store.converter.Book;
import by.beglyakdehterenok.store.converter.BookService;
import by.beglyakdehterenok.store.converter.BooksCreationDto;
import by.beglyakdehterenok.store.entity.Brand;
import by.beglyakdehterenok.store.entity.Category;
import by.beglyakdehterenok.store.entity.Clothing;
import by.beglyakdehterenok.store.entity.Size;
import by.beglyakdehterenok.store.service.BrandService;
import by.beglyakdehterenok.store.service.CategoryService;
import by.beglyakdehterenok.store.service.ClothingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TrainController {

    private final ClothingService clothingService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    public TrainController(ClothingService clothingService, CategoryService categoryService, BrandService brandService) {
        this.clothingService = clothingService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

//    @ModelAttribute
//    public void addAttribute(Model model){
//        Clothing clothing = new Clothing();
//        List<Category> categoryList = categoryService.findAll();
//        List<Brand> brandList = brandService.findAll();
//        Size[] sizes = Size.values();
//        model.addAttribute("clothing",clothing);
//        model.addAttribute("allSizes",sizes);
//        model.addAttribute("allBrand",brandList);
//        model.addAttribute("allCategory",categoryList);
//    }
//
//    @RequestMapping(path = "/",method = RequestMethod.GET)
//    public String showFormTest(){
//        return "test";
//    }
//
//    @PostMapping("/save")
//    public String saveNewClothing(@ModelAttribute("clothing") Clothing clothing){
//        System.out.println(clothing);
//        clothingService.save(clothing);
//        System.out.println(clothing);
//        System.out.println("Final!!!");
//        return "test";
//    }


    @GetMapping("/all")
    public String showAll(Model model) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "One", "One"));
        books.add(new Book(2L, "Two", "Two"));
        books.add(new Book(3L, "Three", "Three"));

        model.addAttribute("books", books);
        return "allBooks";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        BooksCreationDto booksForm = new BooksCreationDto();

        for (int i = 1; i <= 3; i++) {
            booksForm.addBook(new Book());
        }
        System.out.println(booksForm);
        model.addAttribute("form", booksForm);
        return "createBooksForm";
    }

    @PostMapping("/save")
    public String saveBooks(@ModelAttribute("form") BooksCreationDto form, Model model) {
        System.out.println(form.getBooksDto());
        List<Book> books = form.getBooksDto();

        model.addAttribute("books", books);
        return "redirect:/test/all";
    }

    @GetMapping("/edit")
    public String showEditForm(Model model) {
        List<Book> books = new ArrayList<>();
        BookService.findAll().iterator().forEachRemaining(books::add);

        model.addAttribute("form", new BooksCreationDto(books));
        return "editBooksForm";
    }
}
