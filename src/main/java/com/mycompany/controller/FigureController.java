package com.mycompany.controller;

import com.mycompany.domain.Figure;
import com.mycompany.domain.User;
import com.mycompany.dto.FigureDTO;
import com.mycompany.repository.FigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/figure")
public class FigureController {

    @Autowired
    private FigureRepository figureRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/addFigure")
    @ResponseBody
    public String addNewFigure(@RequestParam Double attackPower,
                               @RequestParam Double abilityPower,
                               @RequestParam String name,
                               @RequestParam User user) {
        Figure figure = new Figure();
        figure.setAttackPower(attackPower);
        figure.setAbilityPower(abilityPower);
        figure.setName(name);
        figure.setUser(user);
        figureRepository.save(figure);

        return "SavedFigure";
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/editFigure")
    @ResponseBody
    public String editCharacter(@RequestParam Integer id,
                                @RequestParam Double attackPower,
                                @RequestParam Double abilityPower,
                                @RequestParam String name,
                                @RequestParam User user) {
        Figure figure = figureRepository.findOne(id);
        figure.setAttackPower(attackPower);
        figure.setAbilityPower(abilityPower);
        figure.setName(name);
        figure.setUser(user);
        figureRepository.save(figure);

        return "EditedFigure";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getFigureById")
    @ResponseBody
    public FigureDTO getFigureById(@RequestParam Integer id) {
        return convertToDto(figureRepository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/allFigures")
    @ResponseBody
    public List<FigureDTO> getAllFigures() {
        List<Figure> figures = figureRepository.findAll();
        List<FigureDTO> figuresDTO = new ArrayList<>();
        for (Figure figure : figures) {
            figuresDTO.add(convertToDto(figure));
        }
        return figuresDTO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/allFigures/jQuery")
    public String getAllFiguresjQuery() {
        return "allFigures";
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteFigure")
    @ResponseBody
    public String deleteFigure(@RequestParam Integer id) {
        figureRepository.delete(id);

        return "DeletedFigure";
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteAllFigures")
    @ResponseBody
    public String deleteAllFigures() {
        figureRepository.deleteAll();

        return "DeletedFigures";
    }

    private FigureDTO convertToDto(Figure figure) {
        FigureDTO figureDTO = new FigureDTO();
        figureDTO.setId(figure.getId());
        figureDTO.setName(figure.getName());

        return figureDTO;
    }

    private Figure convertToEntity(FigureDTO figureDTO) {
        Figure figure = new Figure();
        figure.setId(figureDTO.getId());
        figure.setName(figureDTO.getName());

        return figure;
    }
}
