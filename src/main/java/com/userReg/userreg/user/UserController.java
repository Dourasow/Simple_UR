package com.userReg.userreg.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String ShowUserList(Model model)
    {
        List<User> userList = userService.listUsers();
        model.addAttribute("userList", userList);

        return "users";
    }

    @GetMapping("/users/new")
    public String addusers(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "userAdd";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redir)
    {
        userService.save(user);
        redir.addAttribute("message", "The record has been saved successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redir) {
   try {

       User user = userService.updateUser(id);
       model.addAttribute("user", user);
       model.addAttribute("pageTitle", "Edit User(ID: " + id + ")");
       return "userAdd";
   } catch (UserNotFoundException e) {
       redir.addFlashAttribute("message", "The User has been saved successfully");
       return "redirect:/users";
   }

    }

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws UserNotFoundException {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
