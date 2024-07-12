package br.com.desafio.Literalura.models;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @ManyToOne
    private Autor autor;
    String idioma;
    Integer numeroDownloads;

    public Livro(){}
    public Livro(DadosLivro dados){
        this.nome = dados.nomeDoLivro();
        this.idioma = String.join(",", dados.idioma());
        this.numeroDownloads = dados.quantidaDeDowloads();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return  "\nNome: " + nome +
                "\nIdioma: " + idioma +
                "\nAutor: " + autor +
                "\nQuantidade De Downloads: " + numeroDownloads;
    }
}
