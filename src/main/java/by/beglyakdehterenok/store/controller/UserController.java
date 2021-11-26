package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.entity.Clothing;
import by.beglyakdehterenok.store.entity.User;
import by.beglyakdehterenok.store.repository.UserRepository;
import by.beglyakdehterenok.store.util.FileUploadUtil;
import by.beglyakdehterenok.store.util.LocalDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocalDateConverter localDateConverter;

    @ModelAttribute
    public User user() {
        return new User();
    }


    @GetMapping
    public String showUserPage() {
        return "user-info";
    }

    @PostMapping("/save")
    @Transactional
    public String saveUser(@RequestParam("image") MultipartFile multipartFile,
                           @Valid User user, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getErrorCount());
            System.out.println(user);
            return "user-info";
        }

        user.setDateAndTimeOfCreateOrder(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        user.setPhotos(fileName);
        String uploadDir = "clothing-photos/" + savedUser.getId();
        FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
        System.out.println("User was being save!");
        return "user-info";
    }
}
