package br.com.letscode.java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Emprestimo {

    private Usuario usuario;
    private Livro[] livros;
    private final LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    private static final int QTD_MAX_LIVROS = 3;
    private static final List<LocalDate> feriados = getListFeriados();

    private static List<LocalDate> getListFeriados() {
        List<LocalDate> datas = new ArrayList<>();
        datas.add(LocalDate.of(2021, 1, 1));
        datas.add(LocalDate.of(2021, 5, 1));
        datas.add(LocalDate.of(2021, 4, 21));
        datas.add(LocalDate.of(2021, 10, 12));
        datas.add(LocalDate.of(2021, 11, 15));
        datas.add(LocalDate.of(2021, 11, 2));
        datas.add(LocalDate.of(2021, 12, 25));
        datas.add(LocalDate.of(2021, 9, 7));
        datas.add(LocalDate.of(2021, 6, 3));
        return datas;
    }

    public Emprestimo() {
        this.dataEmprestimo = LocalDate.now();
    }

    public Emprestimo(Usuario usuario, Livro[] livros)
            throws ExcedeuQtdMaxLivrosException {

        this.usuario = usuario;
        this.livros = livros;
        this.dataEmprestimo = LocalDate.now();

        if (livros.length > QTD_MAX_LIVROS) {
            throw new ExcedeuQtdMaxLivrosException(livros.length, QTD_MAX_LIVROS);
        }
    }

    /**
     * Regra "dataInicio + prazo (Aluno/Professor)"
     * @return a data estimada para devolução
     */
    public LocalDate calcularDatadevolucao() {
        var date = this.dataEmprestimo;
        var prazoEmDias = this.usuario.getPrazoDevolucaoDias();
        int contDiasUteis = 0;

        while (contDiasUteis < prazoEmDias) {
            boolean fimDeSemana = date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                    || date.getDayOfWeek().equals(DayOfWeek.SUNDAY);

            if (!fimDeSemana && !feriados.contains(date)) {
                contDiasUteis++;//dia útil
            }
            date = date.plusDays(1);
        }
        return date;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario pessoa) {
        this.usuario = pessoa;
    }

    public Livro[] getLivros() {
        return livros;
    }

    public void setLivros(Livro[] livros) {
        this.livros = livros;
    }

    public LocalDate getDataInicio() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "pessoa=" + usuario +
                ", livros=" + Arrays.toString(livros) +
                ", dataInicio=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}
