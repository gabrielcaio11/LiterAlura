package br.com.gabrielcaio.literalura.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.gabrielcaio.literalura.entities.ApiResponse;
import br.com.gabrielcaio.literalura.entities.Book;
import br.com.gabrielcaio.literalura.entities.Person;
import br.com.gabrielcaio.literalura.service.BookService;
import br.com.gabrielcaio.literalura.service.ConsumoAPI;
import br.com.gabrielcaio.literalura.service.ConverteDados;

@Component
public class LiterAlura {

    @Autowired
    private BookService bookService;

    public void run() {
        try {
            menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void menu() throws IOException {

        boolean running = true;

        while (running) {

            Integer opcao = menuOperacoes();

            switch (opcao) {
                case 1 -> buscarFilmePeloTitulo();
                case 2 -> listarFilmesRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosEmDeterminadoAno();
                case 5 -> listarLivrosEmDeterminadoIdioma();
                default -> {
                    System.out.println("Saindo do programa");
                    running = false;
                }
            }

        }

    }

    private void listarLivrosEmDeterminadoIdioma() throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ir);

        System.out.println("""
                Digite o idioma pretendido:
                Inglês (en)
                Português (pt)
                Espanhol (es)
                Francês (fr)
                Alemão (de)
                """);

        String title = in.readLine().trim();
        List<Book> books = bookService.findByIdioma(title);
        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado");
        } else {
            System.out.println("Livros encontrados: ");
            for (Book book : books) {
                System.out.println(book.toString());
            }
        }
    }

    private void listarAutoresVivosEmDeterminadoAno() throws NumberFormatException, IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ir);
        System.out.println("Digite o ano:");
        Integer ano = Integer.parseInt(in.readLine());
        List<Person> byAuthorsBirthYearLessThanEqual = bookService.findByAuthorsBirthYearLessThanEqual(ano);

        if(byAuthorsBirthYearLessThanEqual.isEmpty()){
            System.out.println("Nenhum autor encontrado");
        }else{
            System.out.println("Autores encontrados: ");
            for (Person person : byAuthorsBirthYearLessThanEqual) {
                System.out.println(person.toString());
            }
        }

    }

    private void listarAutoresRegistrados() {
        List<Person> persons = bookService.findAllPersons();
        if (persons.isEmpty())
            System.out.println("Nenhum autor registrado");
        else {
            System.out.println("-----------------------------------\n");
            for (Person person : persons) {
                System.out.println(person);
                System.out.println("-----------------------------------\n");
            }
            System.out.println("\n-----------------------------------");
        }
    }

    private void listarFilmesRegistrados() {
        List<Book> books = bookService.findAllBooks();

        if (books.isEmpty())
            System.out.println("Nenhum livro registrado");
        else {
            System.out.println("-----------------------------------");
            for (Book book : books) {
                System.out.println(book);
                System.out.println("-----------------------------------\n");
            }

        }

    }

    private void buscarFilmePeloTitulo() throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ir);
        System.out.println("Digite o título do livro:");
        String title = in.readLine().replace(" ", "%20").trim();

        String obterDados = ConsumoAPI.obterDados(title);
        ApiResponse apiResponse = ConverteDados.obterDados(obterDados, ApiResponse.class);

        List<Book> books = apiResponse.getBooks();

        if (books.isEmpty()) {
            System.out.println("Livro não encontrado");
        } else {
            System.out.println("-----------------------------------");
            for (Book book : books) {
                bookService.saveBookWithPersons(book);
                System.out.println(book);
            }
            System.out.println("\n-----------------------------------");
        }
    }

    private Integer menuOperacoes() throws IOException {
        exibirMenu();

        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ir);
        int opcao = Integer.parseInt(in.readLine());

        if (opcao < 1 || opcao > 6) {
            System.out.println("Opção invalida, tente novamente!");
            menuOperacoes();
        }

        return opcao;
    }

    private void exibirMenu() {
        System.out.println("\n\n-----------------------------------------------");
        System.out.println("| Escolha um numero da sua opção              |");
        System.out.println("| 1 - Buscar Livro pelo título                |");
        System.out.println("| 2 - Listar Livros registrados               |");
        System.out.println("| 3 - Listar Autores registrados              |");
        System.out.println("| 4 - Listar Autores vivos em determinado ano |");
        System.out.println("| 5 - Listar Livros em determinado idioma     |");
        System.out.println("| 6 - Sair                                    |");
        System.out.println("-----------------------------------------------\n");
    }

}
