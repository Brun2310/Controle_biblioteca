package br.com.letscode.java;

import java.time.LocalDate;

public class Aluno extends Usuario {

    public static final int qtdLivros = 3;
    protected static final int prazo = 10;

    private LocalDate suspensoAte;

    public Aluno() {}

    public Aluno(String nome,String matricula,String email) {
        super(nome,matricula,email);
    }

    @Override
    public int getPrazoDevolucaoDias() {
        return prazo;
    }

    public LocalDate getSuspensoAte() {
        return suspensoAte;
    }

    public void setSuspensoAte(LocalDate suspensoAte) { this.suspensoAte = suspensoAte;}
}
