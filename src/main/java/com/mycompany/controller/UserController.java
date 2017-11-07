package com.mycompany.controller;

import com.mycompany.domain.User;
import com.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/addUser")
    public @ResponseBody
    String addNewUser(@RequestParam String name,
                      @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);

        return "SavedUser";
    }

    @GetMapping(path = "/editUser")
    public @ResponseBody
    String editUser(@RequestParam Integer id,
                    @RequestParam String name,
                    @RequestParam String email) {
        User n = new User();
        n.setId(id);
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);

        return "EditedUser";
    }

    @GetMapping(path = "/allUsers")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/deleteUser")
    public @ResponseBody
    String deleteUser(@RequestParam Integer id) {
        userRepository.delete(id);
        return "DeletedUser";
    }

    @GetMapping(path = "/deleteAllUsers")
    public @ResponseBody
    String deleteAllUsers() {
        userRepository.deleteAll();
        return "DeletedUsers";
    }

}
