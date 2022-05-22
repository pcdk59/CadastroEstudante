package br.com.mentorama.CadastroEstudante;

public class AlunoNaoExistenteException extends Exception {
    public AlunoNaoExistenteException(String message) {
        super(message);
    }
}
