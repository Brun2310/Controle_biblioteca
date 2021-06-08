package br.com.letscode.java;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private final List<Livro> livros = new ArrayList<>();
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public Biblioteca() {

        this.livros.add(new Livro(8550804622L, "Java efetivo", "Joshua Bloch", "Alta Books", 0));
        this.livros.add(new Livro(
                8576082675L, "Código limpo", "Robert C. Martin", "Alta Books", 1));
        this.livros.add(new Livro(
                8573933755L, "Estruturas De Dados E Algoritimos Em Java",
                "Robert Lafore", "CIENCIA MODERNA", 3));
        this.livros.add(new Livro(
                8573076100L, "Padrões de Projetos",
                "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "Bookman", 4));
        this.livros.add(new Livro(
                8575227246L, "Refatoração", "Martin Fowler", "Novatec Editora", 5));
        this.livros.add(new Livro(
                8575223305L, "Introdução às Expressões Regulares",
                "Michael Fitzgerald", "Novatec Editora", 2));
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void realizarEmprestimo(Emprestimo emprestimo) {
        //1 - verificar se é Aluno ou Professor
        //2 - checar se o livro está disponível
        //3 - checar se é possível realizar o empréstimo
        this.livrosEstaoTodosDisponiveis(emprestimo.getLivros());
        String matricula = emprestimo.getUsuario().getMatricula();
        if (emprestimo.getUsuario() instanceof Aluno) {
            boolean existeEmprestimoEmAndamento = this.existeEmprestimoEmAndamento(matricula);
            if (existeEmprestimoEmAndamento) {
                throw new AlunoJaTemEmprestrimoEmAndamentoException(matricula);
            }
            var aluno = (Aluno) emprestimo.getUsuario();
            final var suspensoAte = aluno.getSuspensoAte();
            if (suspensoAte != null && suspensoAte.isAfter(LocalDate.now())) {
                throw new AlunoSuspensoException(matricula, suspensoAte);
            }
        } else if (emprestimo.getUsuario() instanceof Professor) {
            int totalLivrosEmprestados = 0;
            for (Emprestimo emp : this.emprestimos) {
                if (emp.getUsuario().getMatricula().equals(emprestimo.getUsuario().getMatricula())
                        && emp.getDataDevolucao() == null) {
                    totalLivrosEmprestados += emp.getLivros().length;
                }
            }
            if (emprestimo.getLivros().length + totalLivrosEmprestados > Professor.qtdLivros) {
                throw new ProfessorEstourouLimiteLivrosException(totalLivrosEmprestados);
            }
        }
        this.emprestimos.add(emprestimo);
        System.out.println("Empréstimo com sucesso.");
    }

    private void livrosEstaoTodosDisponiveis(Livro[] livros) {
        for (Livro livro : livros) {
            if (! livro.isDisponivel()) {
                throw new LivroNaoDisponivelException(livro);
            }
        }
    }

    private boolean existeEmprestimoEmAndamento(String matricula) {
        for (Emprestimo emprestimo : this.emprestimos) {
            final Usuario pessoaEmprestimo = emprestimo.getUsuario();
            final String pessoaEmprestimoMatricula = pessoaEmprestimo.getMatricula();
            if (emprestimo.getDataDevolucao() == null && pessoaEmprestimoMatricula.equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    public void realizarDevolucao(Emprestimo emprestimo) {
        if (! this.emprestimos.contains(emprestimo)) {
            throw new EmprestimoNaoEncontradoException();
        }
        final var dataPrevistaDevolucao = emprestimo.calcularDatadevolucao();
        final LocalDate hoje = LocalDate.now().plusDays(20);
        if (hoje.isAfter(dataPrevistaDevolucao) && emprestimo.getUsuario() instanceof Aluno) {
            aplicarSuspensaoNoAluno(emprestimo, dataPrevistaDevolucao, hoje);
        }
        emprestimo.setDataDevolucao(hoje);
        for (Livro livro : emprestimo.getLivros()) {
            livro.setCopias(1);
        }
    }

    private void aplicarSuspensaoNoAluno(Emprestimo emprestimo, LocalDate dataPrevistaDevolucao, LocalDate dataEfetivaDevolucao) {
        //Realizando o cast de Pessoa para Aluno, para poder acessar o método "setSuspensoAte"
        Aluno aluno = (Aluno) emprestimo.getUsuario();
        //https://www.baeldung.com/java-date-difference
        final int diasAtraso = Period.between(dataPrevistaDevolucao, dataEfetivaDevolucao).getDays();
        LocalDate suspensoAte = dataEfetivaDevolucao.plusDays(diasAtraso);
        aluno.setSuspensoAte(suspensoAte);
    }
}
