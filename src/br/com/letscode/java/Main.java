package br.com.letscode.java;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Main aplicacao = new Main();
        //1 - quantidade máxima de livros por empréstimo
//        aplicacao.emprestimoAcimaDoMaximo();
        //2 - o prazo de devolução para empréstimo de aluno é de 10 dias úteis
//        aplicacao.prazoDevolucaoAluno();
        //3 - Aluno não pode realizar mais de um empréstimo
//        aplicacao.variosEmprestimosAluno();
        //4 - Aluno suspenso tentando pegar livro
//        aplicacao.alunoSuspenso();
        //5 - Professor tentando pegar mais livros que o permitido
        aplicacao.professorLivrosDemais();
    }

    /**
     * Tenta instanciar um Empréstimo com a quantidade de Livros acima do máximo permitido
     */
    private void emprestimoAcimaDoMaximo() {
        Usuario professor = new Professor();
        Livro[] livros = new Livro[5];
        try {
            Emprestimo emprestimo = new Emprestimo(professor, livros);
            System.out.println(emprestimo);
        } catch (ExcedeuQtdMaxLivrosException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * O prazo de devolução para empréstimo de aluno é de 10 dias úteis
     */
    private void prazoDevolucaoAluno() {
        Usuario aluno = new Aluno();
        Livro[] livros = { new Livro() };
        try {
            final var emprestimo = new Emprestimo(aluno, livros);
            final LocalDate dataPrevistaDevolucao = emprestimo.calcularDatadevolucao();
            System.out.println(dataPrevistaDevolucao);
        } catch (ExcedeuQtdMaxLivrosException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void variosEmprestimosAluno() {
        Usuario aluno = new Aluno("Jessé", "123", "jesse@letscode.com.br");
        try {
            var emprestimo = new Emprestimo(aluno, new Livro[]{new Livro()});
            Biblioteca biblioteca = new Biblioteca();
            biblioteca.realizarEmprestimo(emprestimo);
            biblioteca.realizarEmprestimo(emprestimo);
        } catch (ExcedeuQtdMaxLivrosException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void alunoSuspenso() {
        Usuario aluno = new Aluno("Jessé", "123", "jesse@letscode.com.br");
        Biblioteca biblioteca = new Biblioteca();
        try {
            var emprestimo = new Emprestimo(aluno, new Livro[]{new Livro()});
            biblioteca.realizarEmprestimo(emprestimo);
            biblioteca.realizarDevolucao(emprestimo);
        } catch (ExcedeuQtdMaxLivrosException ex) {
            System.err.println(ex.getMessage());
        }

        try {
            var emprestimo = new Emprestimo(aluno, new Livro[]{new Livro()});
            biblioteca.realizarEmprestimo(emprestimo);
        } catch (ExcedeuQtdMaxLivrosException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void professorLivrosDemais() {
        Usuario professor = new Professor("Jessé", "123", "jesse@letscode.com.br");
        try {
            var emprestimo = new Emprestimo(professor, new Livro[]{new Livro(), new Livro(), new Livro()});
            Biblioteca biblioteca = new Biblioteca();
            biblioteca.realizarEmprestimo(emprestimo);
            biblioteca.realizarEmprestimo(emprestimo);
        } catch (ExcedeuQtdMaxLivrosException | ProfessorEstourouLimiteLivrosException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
