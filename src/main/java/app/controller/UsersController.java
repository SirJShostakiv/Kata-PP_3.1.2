package app.controller;

import app.model.User;
import app.model.UserDTO;
import app.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@ComponentScan("app")
public class UsersController {
    private static final String REDIRECT = "redirect:/";
    private final UserServiceImpl userServiceImpl;
    @Autowired
    public UsersController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("users", userServiceImpl.read());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid UserDTO user) {
        User persistentUser = new User();
        persistentUser.setName(user.getName());
        persistentUser.setLastName(user.getLastName());
        persistentUser.setAge(user.getAge());
        userServiceImpl.create(persistentUser);
        return REDIRECT;
    }

    @GetMapping("/enter_id")
    public String getId() {
        return "enter_id";
    }

    @GetMapping("/{id}")
    public String getEdit(@PathVariable("id") @RequestParam("id") Long id, Model model) {
        User user = userServiceImpl.getByID(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("user") @Valid UserDTO user, @PathVariable("id") Long id) {
        User persistentUser = new User();
        persistentUser.setName(user.getName());
        persistentUser.setLastName(user.getLastName());
        persistentUser.setAge(user.getAge());
        userServiceImpl.update(persistentUser.getName(), persistentUser.getLastName(), persistentUser.getAge(), id);
        return REDIRECT;
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userServiceImpl.delete(id);
        return REDIRECT;
    }
}
