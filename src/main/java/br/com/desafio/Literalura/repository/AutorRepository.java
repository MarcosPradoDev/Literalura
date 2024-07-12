package br.com.desafio.Literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.desafio.Literalura.models.Autor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AutorRepository extends JpaRepository<Autor, Long> {
    Boolean existsByNome(String nome);

    Autor findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE a.dataDeFalecimento >= :anoSelecionado AND :anoSelecionado >= a.dataDeNascimento")
    List<Autor> buscarPorAnoDeFalecimento(int anoSelecionado);

}
