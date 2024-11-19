package br.com.gabrielcaio.literalura.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gabrielcaio.literalura.entities.Book;
import br.com.gabrielcaio.literalura.entities.Person;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b JOIN FETCH b.persons")
    List<Book> findAllBooksWithPersons();

    @Query("SELECT b.persons FROM Book b")
    List<Person> findAllPersons();

    @Query("SELECT DISTINCT a FROM Book b JOIN b.persons a WHERE a.birthYear <= :year")
    List<Person> findByAuthorsBirthYearLessThanEqual(@Param("year") int year);

    @Query("SELECT b FROM Book b JOIN b.languages l WHERE l LIKE %:idioma%")
    List<Book> findByIdioma(@Param("idioma") String idioma);

}
