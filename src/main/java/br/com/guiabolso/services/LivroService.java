package br.com.guiabolso.services;

import java.util.Optional;

import br.com.guiabolso.domain.Livro;

public interface LivroService {
	public Iterable<Livro> obterLivros(); 
	
	public Optional<Livro> obterLivro(Long id);
	
	public Livro gravar(Livro livro);
	
}
