package be.vdab.videowinkel.controllers;

import be.vdab.videowinkel.services.FilmService;
import be.vdab.videowinkel.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("genres")
public class GenreController {
    private final GenreService genreService;
    private final FilmService filmService;

    public GenreController(GenreService genreService, FilmService filmService) {
        this.genreService = genreService;
        this.filmService = filmService;
    }

    @GetMapping("{id}")
    public ModelAndView findById(@PathVariable long id) {
        var modelAndView = new ModelAndView("genre");
        genreService.findById(id).ifPresent(genre ->
                modelAndView.addObject(genre)
                        .addObject("films",
                                filmService.findByGenreId(id)).addObject("genres", genreService.findAll()));
        return modelAndView;
    }
    @GetMapping("{id}/films")
    public ModelAndView findFilmsBijGenre(@PathVariable long id) {
        var modelAndView = new ModelAndView("genrefilms");
        genreService.findById(id).ifPresent(genre ->
                modelAndView.addObject(genre)
                        .addObject("films",
                                filmService.findByGenreId(id)));
        return modelAndView;
    }
}
