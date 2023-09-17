package app.controller;

import app.model.User;
import app.model.UserDTO;
import app.service.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@ComponentScan("app")
public class UsersController {
    private static final String REDIRECT = "redirect:/users";
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userService.read());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") UserDTO user) {
        User persistentUser = new User();
        persistentUser.setName(user.getName());
        persistentUser.setLastName(user.getLastName());
        persistentUser.setAge(user.getAge());
        userService.create(persistentUser);
        return REDIRECT;
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getByID(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") UserDTO user, @PathVariable("id") int id) {
        User persistentUser = new User();
        persistentUser.setName(user.getName());
        persistentUser.setLastName(user.getLastName());
        persistentUser.setAge(user.getAge());
        userService.update(persistentUser, id);
        return REDIRECT;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return REDIRECT;
    }
}
