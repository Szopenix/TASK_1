package com.mycompany.controller;

import com.mycompany.domain.User;
import com.mycompany.dto.UserDTO;
import com.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getName() != null) {
            userRepository.save(user);
            return "savedUser";
        } else {
            return "addUser";
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/updateUser")
    public String updateUser(Model model) {
        model.addAttribute("user", new User());
        return "updateUser";
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/updateUser")
    public String updateUserPut(@ModelAttribute("user") User user) {
        if (user.getName() != null) {
            userRepository.save(user);
            return "savedUser";
        } else {
            return "updateUser";
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getUserById")
    @ResponseBody
    public UserDTO getUserById(@RequestParam Integer id) {
        return convertToDto(userRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/allUsers")
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(convertToDto(user));
        }
        model.addAttribute("allUsers", usersDTO);
        return "allUsers";
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteUser/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable Integer id) {
        userRepository.delete(id);

        return "deletedUser";
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteAllUsers")
    public String deleteAllUsers() {
        userRepository.deleteAll();

        return "deletedUsers";
    }

    private UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        return user;
    }

}
