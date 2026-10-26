package br.com.senai.duvidas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/faq/excluir")
    public String excluir(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/faq";
    }
    @PostMapping("/faq/responder")
    public String responder(@RequestParam Long id, @RequestParam String resposta) {
        repository.findById(id).ifPresent(duvida -> {
            duvida.setResposta(resposta);
            repository.save(duvida);
        });
        return "redirect:/faq";
    }
}