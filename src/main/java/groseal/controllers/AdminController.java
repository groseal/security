package groseal.controllers;

import groseal.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import groseal.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    public AdminController(UserService service) {
        this.userService = service;
    }

    //Показывает всех юзеров из БД
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("user", new User());
        return "admin/allUsers";
    }

    //Показывает юзера по id
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.readUser(id));
        return "admin/showUser";
    }

    //Создает форму для создания юзера
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/newUser";
    }

    //Получает данные из формы и создает юзера
    @PostMapping("/addUser")
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/";
    }

    //Создает форму для редактирования юзера
    @GetMapping("/edit{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.readUser(id));
        return "admin/editUser";
    }

    //Получает данные из формы и обновляет данные юзера
    @PatchMapping("/e{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user.getName(), user.getPassword(), user.getUserRoles());
        return "redirect:/";
    }

    //Удаление юзера
    @DeleteMapping({"/delete{id}"})
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/";
    }
}