package be.vdab.videowinkel.controllers;

import be.vdab.videowinkel.services.FilmService;
import be.vdab.videowinkel.services.ReservatieService;
import be.vdab.videowinkel.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("rapport")
public class RapportController {
    private final Mandje mandje;
    private final FilmService filmService;

    private final ReservatieService reservatieService;


    public RapportController(Mandje mandje, FilmService filmService, ReservatieService reservatieService) {
        this.mandje = mandje;
        this.filmService = filmService;
        this.reservatieService = reservatieService;
    }

    @GetMapping
    public ModelAndView laadFilms() {
        var modelAndView = new ModelAndView("rapport");
        if(!filmService.findGereserveerdByIds(mandje.getIds()).isEmpty()){
            return modelAndView.addObject("films", filmService.findGereserveerdByIds(mandje.getIds()));
        }
        else {
            reservatieService.create(mandje.getKlantId(), mandje.getIds());
            filmService.reserveerFilmsByIds(mandje.getIds());
            mandje.clearMandje();
            return modelAndView.addObject("films");
        }
    }

}
