package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

@Controller
@RequestMapping(path = "/demo")
public class MainController implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharacterRepository characterRepository;

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

    @GetMapping(path = "/addCharacter")
    public @ResponseBody
    String addNewCharacter(@RequestParam Double power,
                           @RequestParam String name,
                           @RequestParam User user) {
        Character ch = new Character();
        ch.setPower(power);
        ch.setName(name);
        ch.setUser(user);
        characterRepository.save(ch);
        return "SavedCharacter";
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

    @GetMapping(path = "/editCharacter")
    public @ResponseBody
    String editCharacter(@RequestParam Integer id,
                         @RequestParam Double power,
                         @RequestParam String name,
                         @RequestParam User user) {
        Character ch = new Character();
        ch.setId(id);
        ch.setPower(power);
        ch.setName(name);
        ch.setUser(user);
        characterRepository.save(ch);
        return "EditedCharacter";
    }

    @GetMapping(path = "/users")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/characters")
    public @ResponseBody
    Iterable<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    @GetMapping(path = "/deleteUser")
    public @ResponseBody
    String deleteUser(@RequestParam Integer id) {
        userRepository.delete(id);
        return "DeletedUser";
    }

    @GetMapping(path = "/deleteCharacter")
    public @ResponseBody
    String deleteCharacter(@RequestParam Integer id) {
        characterRepository.delete(id);
        return "DeletedCharacter";
    }

    @GetMapping(path = "/deleteAllUsers")
    public @ResponseBody
    String deleteAllUsers() {
        userRepository.deleteAll();
        return "DeletedUsers";
    }

    @GetMapping(path = "/deleteAllCharacters")
    public @ResponseBody
    String deleteAllCharacters() {
        characterRepository.deleteAll();
        return "DeletedCharacters";
    }

    @Override
    public void run(String... strings) throws Exception {
        clearData();
        saveData();
        showData();
    }

    @Transactional
    private void clearData() {
        characterRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Transactional
    private void saveData() {
        User user1 = new User("Adrian", "adrian@gmail.com");
        User user2 = new User("Adam", "adam@gmail.com");
        userRepository.save(user1);
        userRepository.save(user2);

        Character warrior = new Character(100d, "warrior", user1);
        Character mage = new Character(50d, "mage", user1);
        Character warlock = new Character(40d, "warlock", user2);
        Character rogue = new Character(80d, "rogue", user2);
        characterRepository.save(warrior);
        characterRepository.save(mage);
        characterRepository.save(warlock);
        characterRepository.save(rogue);
    }

    @Transactional
    private void showData() {
        for (User user : userRepository.findAll()) {
            logger.info(user.getName());
        }

        for (Character character : characterRepository.findAll()) {
            logger.info(character.getName());
        }
    }

}
