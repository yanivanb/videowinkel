package be.vdab.videowinkel.controllers;

import be.vdab.videowinkel.services.KlantService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("klant")
public class KlantController {
    private final KlantService klantService;

    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @GetMapping("{name}")
    public ModelAndView findById(@PathVariable String name) {
        return new ModelAndView("klant","klanten", klantService.findKlantListByName(name));
    }
    @GetMapping()
    public ModelAndView laadKlanten() {
        return new ModelAndView("klant","klanten", "");
    }
}
