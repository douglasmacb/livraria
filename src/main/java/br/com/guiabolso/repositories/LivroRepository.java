package br.com.guiabolso.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.guiabolso.domain.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long> {

	List<Livro> findByIsbn(String isbn);
}
