package br.com.gabrielcaio.literalura.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tb_books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String title;

    @ManyToMany(mappedBy = "books",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("books")
    private Set<Person> persons = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> languages = new HashSet<>();
    private Long downloadCount;

    

    public Book(String title, Set<String> languages, Long downloadCount) {
        this.title = title;
        this.languages = languages;
        this.downloadCount = downloadCount;
    }
    public Book(BookResult result) {
        this.title = result.getTitle();
        this.persons = result.getAuthors().stream().map(Person::new).collect(Collectors.toSet());
        this.languages = result.getLanguages();
        this.downloadCount = result.getDownloadCount();
    }
    @Override
    public String toString() {
        String toString = "Título: " + title;

        toString += "\nAutor(es):";
        for(Person person : persons){
            toString += " | "+ person.getName() + " |";
        }

        toString += "\nIdioma(s):";
        for(String language : languages){
            toString += " "+ language;
        }
        toString += "\nDownload(s): " + downloadCount;
        return  toString;
    }
}
