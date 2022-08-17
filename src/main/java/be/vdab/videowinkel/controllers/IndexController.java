package be.vdab.videowinkel.controllers;

import be.vdab.videowinkel.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController {
    private final GenreService genreService;

    IndexController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index",
                "genres", genreService.findAll());
    }
}
