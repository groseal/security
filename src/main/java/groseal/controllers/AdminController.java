package groseal.controllers;

import groseal.models.Role;
import groseal.models.User;
import groseal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import groseal.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    //Показывает всех юзеров из БД
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "allUsers";
    }

    //Создает форму для создания юзера
    @GetMapping("/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    //Получает данные из формы и создает юзера
    @PostMapping("/addUser")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "adminRole", defaultValue = "") String adminRole,
                             @RequestParam(value = "userRole", defaultValue = "") String userRole) {
        userService.createUser(user);
        user.setUserRoles(getRoles(adminRole, userRole));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    //Создает форму для редактирования юзера
    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("user", userService.readUser(id));
        return "/editUser";
    }

    //Получает данные из формы и обновляет данные юзера
    @PatchMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "adminRole", defaultValue = "") String adminRole,
                             @RequestParam(value = "userRole", defaultValue = "") String userRole) {

        user.setUserRoles(getRoles(adminRole, userRole));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    //Удаление юзера
    @DeleteMapping({"/delete{id}"})
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    private Set<Role> getRoles(String adminRole, String userRole) {
        Set<Role> roles = new HashSet<>();
        if (!adminRole.isEmpty()) {
            roles.add(roleService.getRoleByName(adminRole));
        }
        if (!userRole.isEmpty()) {
            roles.add(roleService.getRoleByName(userRole));
        }
        return roles;
    }
}
