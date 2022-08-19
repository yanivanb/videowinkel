package be.vdab.videowinkel.controllers;

import be.vdab.videowinkel.forms.ZoekKlantForm;
import be.vdab.videowinkel.services.FilmService;
import be.vdab.videowinkel.services.KlantService;
import be.vdab.videowinkel.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("klant")
public class KlantController {
    private final KlantService klantService;
    private final Mandje mandje;
    private final FilmService filmService;

    public KlantController(KlantService klantService, Mandje mandje, FilmService filmService) {
        this.klantService = klantService;
        this.mandje = mandje;
        this.filmService = filmService;
    }

    @GetMapping()
    public ModelAndView klanten(@Valid ZoekKlantForm form, Errors errors) {
        var modelAndView = new ModelAndView("klant");
        if (errors.hasErrors()) {
            return modelAndView;
        }
        return modelAndView.addObject("klanten", klantService.findKlantListByName(form.naam()));
    }

    @GetMapping("/bevestig/{id}")
    public ModelAndView bevestigen(@PathVariable long id) {
        var modelAndView = new ModelAndView("bevestig");
        mandje.setKlantId(id);
        klantService.findById(id).ifPresent(klant ->
                modelAndView.addObject(klant)
                        .addObject("films",
                                filmService.findByIds(mandje.getIds())));
        return modelAndView;
    }


}
