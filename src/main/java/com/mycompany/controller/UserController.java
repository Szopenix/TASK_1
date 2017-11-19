package com.mycompany.controller;

import com.mycompany.domain.User;
import com.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/addUser")
    @ResponseBody
    public String addNewUser(@RequestParam String name,
                             @RequestParam String password,
                             @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        userRepository.save(user);

        return "SavedUser";
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/updateUser")
    @ResponseBody
    public String updateUser(@RequestParam Integer id,
                             @RequestParam String name,
                             @RequestParam String password,
                             @RequestParam String email) {
        User user = userRepository.findOne(id);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        userRepository.save(user);

        return "EditedUser";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getUserById")
    @ResponseBody
    public User getUserById(@RequestParam Integer id) {

        return userRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/allUsers")
    @ResponseBody
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam Integer id) {
        userRepository.delete(id);

        return "DeletedUser";
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteAllUsers")
    @ResponseBody
    public String deleteAllUsers() {
        userRepository.deleteAll();

        return "DeletedUsers";
    }

}
