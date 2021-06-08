package br.com.letscode.java;

public class Professor extends Usuario{

    protected static final int qtdLivros = 5;
    protected static final int prazo = 20;

    public Professor(String nome,String matricula,String email) {
        super(nome,matricula,email);
    }

    public Professor() {

    }

    @Override
    public int getPrazoDevolucaoDias() {
        return prazo;
    }
}
