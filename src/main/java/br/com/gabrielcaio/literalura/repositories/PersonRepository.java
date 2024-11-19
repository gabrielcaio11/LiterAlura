package br.com.gabrielcaio.literalura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielcaio.literalura.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
