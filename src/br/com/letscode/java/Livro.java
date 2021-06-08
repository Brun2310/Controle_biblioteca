package br.com.letscode.java;

public class Livro {

    private String titulo;
    private Long isbn;
    private String autor;
    private String editora;
    private int copias;
    //TODO verificar se livro ja esta na biblioteca, e adicionar copia

    public Livro() {

    }

    public Livro(Long isbn, String titulo, String autor, String editora, int copias) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.editora = editora;
        this.copias = copias;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }


    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    public String getEditora() {
        return editora;
    }

    public String setEditora(String editora) {
        return this.editora = editora;
    }


    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias += copias;
    }

    public boolean isDisponivel() {
        return copias != 0;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "isbn=" + isbn +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editora='" + editora + '\'' +
                ", copias=" + copias +
                '}';
    }
}
