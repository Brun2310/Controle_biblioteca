package br.com.letscode.java;

public abstract class Usuario {

    protected String nome;
    protected String matricula;
    protected String email;

    public Usuario() {

    }

    public Usuario(String nome, String matricula, String email) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
    }

    public abstract int getPrazoDevolucaoDias();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
