package be.vdab.videowinkel.controllers;

import be.vdab.videowinkel.services.FilmService;
import be.vdab.videowinkel.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final FilmService filmService;

    public MandjeController(Mandje mandje, FilmService filmService) {
        this.mandje = mandje;
        this.filmService = filmService;
    }

    @PostMapping("{id}") public String voegToe(@PathVariable long id) {
        mandje.voegToe(id);
        return "redirect:/mandje";
    }

    @PostMapping("verwijderen")
    public String delete(Optional<Set<Long>> id) {
        id.ifPresent(ids -> mandje.verwijderUit(ids));
// als de gebruikt geen enkele entry selecteerde, is id een lege Optional
        return "redirect:/gastenboek";
    }

    @GetMapping
    public ModelAndView toonMandje() {
        return new ModelAndView("mandje",
                "films", filmService.findByIds(mandje.getIds()));
    }
}