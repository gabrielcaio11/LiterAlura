package br.com.gabrielcaio.literalura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.gabrielcaio.literalura.entities.Book;
import br.com.gabrielcaio.literalura.entities.Person;
import br.com.gabrielcaio.literalura.repositories.BookRepository;
import jakarta.transaction.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Book saveBookWithPersons(Book book) {
        
        if (bookRepository.existsById(book.getId())) {
            throw new DataIntegrityViolationException("Book already exists");
        }
        book.getPersons().forEach(p -> {
            if(bookRepository.existsById(p.getId())){
                throw new DataIntegrityViolationException("Person already exists");
            }
        });
        book.getPersons().forEach(p -> p.getBooks().add(book));

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

    public List<Book> findByIdioma(String idioma) {
        return bookRepository.findByIdioma(idioma);
    }
}
