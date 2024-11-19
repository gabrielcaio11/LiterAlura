package br.com.gabrielcaio.literalura.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "tb_persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer birthYear;
    private Integer deathYear;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
    @JoinTable( 
        name = "person_book", // nome da nova tabela
        joinColumns = @JoinColumn(name = "person_id"),  // fk da entidade desse lado
        inverseJoinColumns = @JoinColumn(name = "book_id") // fk da entidade do outro lado
    )
    @JsonIgnoreProperties("persons")
    private Set<Book> books = new HashSet<>();

    public Person(Author author){
        this.birthYear = author.getBirthYear();
        this.deathYear = author.getDeathYear();
        this.name = author.getName();
    }

    @Override
    public String toString() {
        String toString = "";
        toString += "\nBirthYear: " + birthYear;
        toString += "\nDeathYear: " + deathYear;
        toString += "\nName: " + name;
        toString += "\nLivros [\n";
        for (Book book : books) {
            toString +=  book;
        }
        toString += "\n]"; 
        return toString;
    }
    
}
