package com.mycompany.controller;

import com.mycompany.domain.Figure;
import com.mycompany.domain.User;
import com.mycompany.dto.FigureDTO;
import com.mycompany.repository.FigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/figureDTO")
public class FigureDTOController {

    @Autowired
    private FigureRepository figureRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/addFigure")
    @ResponseBody
    public FigureDTO addNewFigure(@RequestParam String name) {
        FigureDTO figureDTO = new FigureDTO();
        figureDTO.setName(name);
        Figure figure = convertToEntity(figureDTO);
        Figure figureCreated = figureRepository.save(figure);

        return convertToDto(figureCreated);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/editFigure")
    @ResponseBody
    public FigureDTO editFigure(@RequestParam Integer id,
                                @RequestParam String name) {
        FigureDTO figureDTO = new FigureDTO();
        figureDTO.setId(id);
        figureDTO.setName(name);
        Figure figure = convertToEntity(figureDTO);
        Figure figureCreated = figureRepository.save(figure);

        return convertToDto(figureCreated);
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

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteFigure")
    @ResponseBody
    public FigureDTO deleteFigure(@RequestParam Integer id) {
        FigureDTO figureDTO = getFigureById(id);
        figureRepository.delete(id);

        return figureDTO;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteAllFigures")
    @ResponseBody
    public List<FigureDTO> deleteAllFigures() {
        List<FigureDTO> figuresDTO = getAllFigures();
        figureRepository.deleteAll();

        return figuresDTO;
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
