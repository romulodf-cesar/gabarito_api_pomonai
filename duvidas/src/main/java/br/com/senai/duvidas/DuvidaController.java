package br.com.senai.duvidas;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController 
@RequestMapping("/duvida")
@CrossOrigin(origins ="http://localhost:8081") //CORS
public class DuvidaController {
    //Injeção de Dependência
    @Autowired
    private DuvidaRepository repository;   
    
    @GetMapping
    public List<Duvida> listar() {
        return repository.findAllByOrderByDataHoraDesc();
    } 
    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody DadosCadastroDuvida dados){
        repository.save(new Duvida(dados));
        return ResponseEntity.status(HttpStatus.CREATED).build(); //201
    }
}

