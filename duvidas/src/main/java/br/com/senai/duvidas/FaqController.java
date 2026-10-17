package br.com.senai.duvidas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqController {

    private final DuvidaRepository repository;

    public FaqController(DuvidaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/faq")
    public String faq(Model model) {
        model.addAttribute("duvidas", repository.findAllByOrderByDataHoraDesc());
        return "faq";
    }
}