package br.com.mentorama.CadastroEstudante;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class GenericControllerAdvice {

    @ExceptionHandler({AlunoNaoExistenteException.class})
    public ResponseEntity<String> handle(AlunoNaoExistenteException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>("Aluno(a) inexistente.", HttpStatus.NOT_FOUND);
    }
}