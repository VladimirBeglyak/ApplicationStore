package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.service.BrandService;
import by.beglyakdehterenok.store.service.CategoryService;
import by.beglyakdehterenok.store.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clothes")
public class ClothingController {


    private final ClothingService clothingService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    @Autowired
    public ClothingController(ClothingService clothingService, CategoryService categoryService, BrandService brandService) {
        this.clothingService = clothingService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

//    @ModelAttribute
//    public void addClothToPage(Model model){
//        Clothing clothing = new Clothing();
//        model.addAttribute("clothing",clothing);
//    }

    @GetMapping("/add")
    public ModelAndView addClothing(ModelAndView modelAndView) {
        Clothing clothing = new Clothing();
//        modelAndView.addObject("allSeasons", Season.values());
//        modelAndView.addObject("allTypes", Type.values());
        modelAndView.addObject("allCategories", categoryService.findAll());
        modelAndView.addObject("allSizes", Size.values());
        modelAndView.addObject("allBrands", brandService.findAll());
        modelAndView.addObject("newClothing", clothing);
        modelAndView.setViewName("clothes-add");
        return modelAndView;
    }

    @PostMapping("/save")
    public String addNewClothingToCatalog(@ModelAttribute("newClothing") Clothing clothing,
                                          @RequestParam("brandId") Long brandId,
                                          @RequestParam("categoryId") Long categoryId,
                                          @RequestParam("count") Long count) {
        clothingService.addNewClothingToStorage(brandId,categoryId,clothing,count);

        return "redirect:/catalog/";
    }
//*************Misha*************************************
//    private final ClothingService clothingService;
//    private final OrderService orderService;
//    private final AccountService accountService;
//
//    @Autowired
//    public ClothingController(ClothingService clothingService, OrderService orderService, AccountService accountService) {
//        this.clothingService = clothingService;
//        this.orderService = orderService;
//        this.accountService = accountService;
//    }
//
//    //название одежды - это будет ссылка на его личную страницу,
//    //на странице одежды будет форма для заполнения заказа(будут скрытые поля некоторые)
//    @GetMapping("/clothing/{id}")
//    public String showClothingPage(@PathVariable("id") Long id,
//                                   @SessionAttribute("accountId") Long accountId,
//                                   Model model){
//        Clothing clothing = clothingService.findById(id);
//        model.addAttribute("clothing", clothing);
//        Order order = new Order();
//        order.setAccount(accountService.findById(accountId));
//        order.setClothing(clothing);
//        model.addAttribute("order", order);
//        return "clothing";
//    }
//
//    //после заполнения формы идет сохранение нового заказа(или обновление существующего)
//    // и редирект на страницу корзины
//    @PostMapping("/order/save")
//    public String saveOrder(@ModelAttribute("order") Order order,
//                            @SessionAttribute("accountId") Long accountId){
//        orderService.saveOrUpdate(order, accountId);
//        return "redirect:/basket";
//    }
//

}
