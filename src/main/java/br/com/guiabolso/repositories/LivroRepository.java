package br.com.guiabolso.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.guiabolso.domain.Livro;

public interface LivroRepository extends MongoRepository<Livro, String> {
}
