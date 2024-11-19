package br.com.gabrielcaio.literalura.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    private List<BookResult> results = new ArrayList<>();

    public List<Book> getBooks(){
        return results.stream().map(Book::new).collect(Collectors.toList());
    }
}

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
class BookResult {
    
    private String title;
    private List<Author> authors = new ArrayList<>();
    private Set<String> languages = new HashSet<>();

    @JsonAlias("download_count")
    private Long downloadCount;
}

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
class Author {
    private String name;

    @JsonProperty("birth_year")
    private int birthYear;

    @JsonProperty("death_year")
    private int deathYear;
}


