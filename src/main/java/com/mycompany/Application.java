package com.mycompany;

import com.mycompany.domain.Figure;
import com.mycompany.domain.User;
import com.mycompany.repository.FigureRepository;
import com.mycompany.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.transaction.Transactional;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner{

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FigureRepository figureRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        clearData();
        saveData();
        showData();
    }

    @Transactional
    private void clearData() {
        figureRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Transactional
    private void saveData() {
        User user1 = new User("Adrian", "adrian", "adrian@gmail.com");
        User user2 = new User("Adam", "adam", "adam@gmail.com");
        userRepository.save(user1);
        userRepository.save(user2);

        Figure warrior = new Figure(100d, 0d, "warrior", user1);
        Figure mage = new Figure(20d, 100d, "mage", user1);
        Figure warlock = new Figure(10d, 120d, "warlock", user2);
        Figure rogue = new Figure(80d, 20d, "rogue", user2);
        figureRepository.save(warrior);
        figureRepository.save(mage);
        figureRepository.save(warlock);
        figureRepository.save(rogue);
    }

    @Transactional
    private void showData() {
        for (User user : userRepository.findAll()) {
            logger.info(user.getName());
        }

        for (Figure figure : figureRepository.findAll()) {
            logger.info(figure.getName());
        }
    }

}
