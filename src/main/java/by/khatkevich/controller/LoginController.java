package by.khatkevich.controller;

import by.khatkevich.service.UserPage;
import by.khatkevich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new UserPage());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute("user") UserPage userPage, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
           return "register";
       }
       if(!userPage.getPassword().equals(userPage.getRepeatPassword())){
           bindingResult.rejectValue("password", "", "Пароли не совпадают");
           return "register";
       }
       userService.create(userPage);
       return "redirect:/login";
    }
}
