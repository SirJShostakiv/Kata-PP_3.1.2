package app.controller;

import app.model.User;
import app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public String create(@ModelAttribute("user") User user) {
        userServiceImpl.create(user);
        return REDIRECT;
    }

    @GetMapping("/enter_id")
    public String getId() {
        return "enter_id";
    }

    @GetMapping("/{id}")
    public String getEdit(@PathVariable("id") @RequestParam("id") Long id, Model model) {
        List<Long> idList = new ArrayList<>(userServiceImpl.getIdList());
        if (!idList.contains(id)) {
            return "input_error";
        }
        User user = userServiceImpl.getByID(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userServiceImpl.update(user, id);
        return REDIRECT;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userServiceImpl.delete(id);
        return REDIRECT;
    }
}
