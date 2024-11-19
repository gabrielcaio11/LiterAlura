package br.com.gabrielcaio.literalura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielcaio.literalura.entities.Book;
import br.com.gabrielcaio.literalura.entities.Person;
import br.com.gabrielcaio.literalura.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBookWithPersons(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAllBooksWithPersons();
    }
 
    public List<Person> findAllPersons() {
        return bookRepository.findAllPersons();
    }

    public List<Person> findByAuthorsBirthYearLessThanEqual(int year) {
        return bookRepository.findByAuthorsBirthYearLessThanEqual(year);
    }

    public List<Book> findByIdioma(String idioma){
        return bookRepository.findByIdioma(idioma);
    }
}
