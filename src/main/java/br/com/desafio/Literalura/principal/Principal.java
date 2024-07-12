package br.com.desafio.Literalura.principal;

import br.com.desafio.Literalura.models.Autor;
import br.com.desafio.Literalura.models.DadosAutor;
import br.com.desafio.Literalura.models.DadosLivro;
import br.com.desafio.Literalura.models.Livro;
import br.com.desafio.Literalura.repository.AutorRepository;
import br.com.desafio.Literalura.repository.LivroRepository;
import br.com.desafio.Literalura.service.ConsumoApi;
import br.com.desafio.Literalura.service.ConverteDados;


import java.util.Scanner;

public class Principal {
    Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String URL = "https://gutendex.com/books?search=";

    private LivroRepository repositorioLivro;
    private AutorRepository repositorioAutor;

    public Principal(LivroRepository repositorioLivro, AutorRepository repositorioAutor) {
        this.repositorioLivro = repositorioLivro;
        this.repositorioAutor = repositorioAutor;

    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Bem vindo ao Liter Alura!!! ***
                    
                    Escolha o número da sua opção:
                    
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- listar autores vivos em um determinado ano
                    5- listar livros em um determinado idioma
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();

                    break;
                case 4:
                    autoresVivos();

                    break;
                case 5:
                    livrosPorIdioma();

                    break;
                case 0:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivro() {
        System.out.println("\nQual livro deseja buscar?");
        var buscaDoUsuario = leitura.nextLine();
        var jsonDados = consumo.obterDados(URL+ buscaDoUsuario.replace(" ","%20"));
        salvarNoBd(jsonDados);
    }

    private void salvarNoBd(String jsonDados) {
        try{
            Livro livro = new Livro(conversor.obterDados(jsonDados, DadosLivro.class));
            Autor autor = new Autor(conversor.obterDados(jsonDados, DadosAutor.class));
            Livro livroBd = null;
            Autor autorBd = null;
            if (!repositorioAutor.existsByNome(autor.getNome())){
                repositorioAutor.save(autor);
                autorBd = autor;
            }else{
                autorBd = repositorioAutor.findByNome(autor.getNome());
            }
            if (!repositorioLivro.existsByNome(livro.getNome())){
                livro.setAutor(autorBd);
                repositorioLivro.save(livro);
                livroBd = livro;
            }else{
                livroBd = repositorioLivro.findByNome(livro.getNome());
            }

        }catch (NullPointerException e){
            System.out.println("\n\n*** Livro não encontrado ***\n\n");
        }
    }

    private void listarLivrosRegistrados() {
        var bucas = repositorioLivro.findAll();
        if(!bucas.isEmpty()){
            System.out.println("\nLivros cadastrados no banco de dados: ");
            bucas.forEach(System.out::println);
        }else{
            System.out.println("\nNenhum livro encontrado no banco de dados!");
        }
    }

    private void listarAutoresRegistrados() {
        var buscaDb = repositorioAutor.findAll();
        if(!buscaDb.isEmpty()){
            System.out.println("\nAutores cadastrados no banco de dados:");
            buscaDb.forEach(System.out::println);
        }else{
            System.out.println("\nNenhum autor encontrado no banco de dados!");
        }
    }

    private void autoresVivos() {
        System.out.println("\nQual ano deseja pesquisar?");
        var anoSelecionado = leitura.nextInt();
        leitura.nextLine();
        var buscaAutoresNoDb = repositorioAutor.buscarPorAnoDeFalecimento(anoSelecionado);
        if(!buscaAutoresNoDb.isEmpty()){
            System.out.println("\n\nAtores vivos no ano de: " + anoSelecionado);
            buscaAutoresNoDb.forEach(System.out::println);
        }else {
            System.out.println("\nNenhum autor encontrado para esta data!");
        }
    }

    private void livrosPorIdioma() {
        var idiomasCadastrados = repositorioLivro.bucasidiomas();
        System.out.println("\nIdiomas cadastrados no banco:");
        idiomasCadastrados.forEach(System.out::println);
        System.out.println("\nSelecione um dos idiomas cadastrados no banco:\n");
        var idiomaSelecionado = leitura.nextLine();
        repositorioLivro.buscarPorIdioma(idiomaSelecionado).forEach(System.out::println);
    }

}
























