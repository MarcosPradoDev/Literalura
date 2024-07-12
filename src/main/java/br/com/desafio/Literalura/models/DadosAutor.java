package br.com.desafio.Literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonAlias("name") String nomeDoAutor,
                         @JsonAlias("birth_year") Integer dataDeNascimento,
                         @JsonAlias("death_year") Integer dataDeFalecimento) {
}
