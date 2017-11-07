package com.mycompany.controller;

import com.mycompany.domain.Figure;
import com.mycompany.domain.User;
import com.mycompany.repository.FigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/figure")
public class FigureController {

    @Autowired
    private FigureRepository figureRepository;

    @GetMapping(path = "/addFigure")
    public @ResponseBody
    String addNewFigure(@RequestParam Double power,
                        @RequestParam String name,
                        @RequestParam User user) {
        Figure figure = new Figure();
        figure.setPower(power);
        figure.setName(name);
        figure.setUser(user);
        figureRepository.save(figure);

        return "SavedFigure";
    }

    @GetMapping(path = "/editFigure")
    public @ResponseBody
    String editCharacter(@RequestParam Integer id,
                         @RequestParam Double power,
                         @RequestParam String name,
                         @RequestParam User user) {
        Figure figure = figureRepository.findOne(id);
        figure.setPower(power);
        figure.setName(name);
        figure.setUser(user);
        figureRepository.save(figure);

        return "EditedFigure";
    }

    @GetMapping(path = "/getFigureById")
    public @ResponseBody
    Figure getFigureById(@RequestParam Integer id) {

        return figureRepository.findOne(id);
    }

    @GetMapping(path = "/allFigures")
    public @ResponseBody
    Iterable<Figure> getAllFigures() {
        return figureRepository.findAll();
    }

    @GetMapping(path = "/deleteFigure")
    public @ResponseBody
    String deleteFigure(@RequestParam Integer id) {
        figureRepository.delete(id);

        return "DeletedFigure";
    }

    @GetMapping(path = "/deleteAllFigures")
    public @ResponseBody
    String deleteAllFigures() {
        figureRepository.deleteAll();

        return "DeletedFigures";
    }

}
