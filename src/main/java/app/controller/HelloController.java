package app.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@ComponentScan("app")
public class HelloController {
    @GetMapping("/")
    public String helloMessage(Model model) {
        List<String> messageList = new ArrayList<>();
        messageList.add("Hello, this is my first CRUD app\n");
        messageList.add("There you can show, add, edit and delete some phantom users\n");
        model.addAttribute("messages", messageList);
        return "start";
    }
}
