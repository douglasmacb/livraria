package br.com.guiabolso.services;

import java.util.Optional;

import br.com.guiabolso.domain.Livro;

public interface LivroService {
	
	public Optional<Livro> buscar(Long id);
	
	public Livro gravar(Livro livro);
}
