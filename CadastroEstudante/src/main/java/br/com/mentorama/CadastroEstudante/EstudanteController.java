package br.com.mentorama.CadastroEstudante;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastroalunos")
public class EstudanteController {

    private final EstudanteService estudanteService;

    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }

    @GetMapping
    public List<EstudanteModel> findAll(@RequestParam(required = false) String name, Integer age) throws AlunoNaoExistenteException {
        try {
            return estudanteService.findAllEstudantes(name, age);
        } catch (AlunoNaoExistenteException e) {
            throw new AlunoNaoExistenteException("Aluno(a) inexistente");
        }
    }

    @GetMapping("/id/{id}")
    public EstudanteModel findById(@PathVariable("id") Integer id) throws AlunoNaoExistenteException {
        try {
            return estudanteService.findByIdEstudante(id);
        } catch (RuntimeException e) {
            throw new AlunoNaoExistenteException("O ID expecificado do(a) Aluno não existe.");
        }
    }

    @PostMapping
    public String add(@RequestBody EstudanteModel estudante) {
        return estudanteService.addEstudante(estudante);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody EstudanteModel estudante) {
        estudanteService.getEstudantes().stream()
                .filter(aluno -> aluno.getId().equals(estudante.getId()))
                .forEach(aluno -> aluno.setAge(estudante.getAge()));

        estudanteService.getEstudantes().stream()
                .filter(aluno -> aluno.getId().equals(estudante.getId()))
                .forEach(aluno -> aluno.setName(estudante.getName()));

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        estudanteService.getEstudantes().removeIf(aluno -> aluno.getId().equals(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}