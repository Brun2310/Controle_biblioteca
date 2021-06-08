package br.com.letscode.java;

public class ExcedeuQtdMaxLivrosException extends Exception {

    public ExcedeuQtdMaxLivrosException(){
        super("A quantidade maxima de livros por emprestimo foi excedida");
    }

    public ExcedeuQtdMaxLivrosException(int qtdInformada, int qtdMax){
        super(String.format("A quantidade maxima foi excedida, o limite de livros em um emprestimo é %d", qtdMax));
    }
}
