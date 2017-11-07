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
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);

        return "SavedUser";
    }

    @GetMapping(path = "/updateUser")
    public @ResponseBody
    String updateUser(@RequestParam Integer id,
                      @RequestParam String name,
                      @RequestParam String email) {
        User user = userRepository.findOne(id);
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);

        return "EditedUser";
    }

    @GetMapping(path = "/getUserById")
    public @ResponseBody
    User getUserById(@RequestParam Integer id) {

        return userRepository.findOne(id);
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
