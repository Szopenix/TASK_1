package com.mycompany.controller;

import com.mycompany.domain.User;
import com.mycompany.dto.UserDTO;
import com.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/userDTO")
public class UserDTOController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/addUser")
    @ResponseBody
    public UserDTO addNewUser(@RequestParam String name,
                              @RequestParam String email) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setEmail(email);
        User user = convertToEntity(userDTO);
        User userCreated = userRepository.save(user);

        return convertToDto(userCreated);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/updateUser")
    @ResponseBody
    public UserDTO updateUser(@RequestParam Integer id,
                              @RequestParam String name,
                              @RequestParam String email) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setName(name);
        userDTO.setEmail(email);
        User user = convertToEntity(userDTO);
        User userCreated = userRepository.save(user);

        return convertToDto(userCreated);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getUserById")
    @ResponseBody
    public UserDTO getUserById(@RequestParam Integer id) {
        return convertToDto(userRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/allUsers")
    @ResponseBody
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(convertToDto(user));
        }

        return usersDTO;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteUser")
    @ResponseBody
    public UserDTO deleteUser(@RequestParam Integer id) {
        UserDTO userDTO = getUserById(id);
        userRepository.delete(id);

        return userDTO;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteAllUsers")
    @ResponseBody
    public List<UserDTO> deleteAllUsers() {
        List<UserDTO> usersDTO = getAllUsers();
        userRepository.deleteAll();

        return usersDTO;
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
