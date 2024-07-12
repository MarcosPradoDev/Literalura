package br.com.desafio.Literalura.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private int dataDeNascimento;
    private int dataDeFalecimento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(){};
    public Autor(DadosAutor dados){
        this.nome = dados.nomeDoAutor();
        this.dataDeNascimento = dados.dataDeNascimento();
        this.dataDeFalecimento = dados.dataDeFalecimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public int getDataDeFalecimento() {
        return dataDeFalecimento;
    }

    public void setDataDeFalecimento(int dataDeFalecimento) {
        this.dataDeFalecimento = dataDeFalecimento;
    }

    public int getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(int dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
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
                "\nData De Nascimento: " + dataDeNascimento +
                "\nData De Falecimento: " + dataDeFalecimento;
    }
}
