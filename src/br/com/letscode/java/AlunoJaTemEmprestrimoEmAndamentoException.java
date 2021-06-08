package br.com.letscode.java;

public class AlunoJaTemEmprestrimoEmAndamentoException extends RuntimeException {
    public AlunoJaTemEmprestrimoEmAndamentoException(String matricula) {
        super("O aluno de matricula" + matricula + "ja possui um emprestimo em andamento");
    }
}
