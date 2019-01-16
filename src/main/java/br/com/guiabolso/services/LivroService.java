package br.com.guiabolso.services;

import java.util.List;
import java.util.Optional;

import br.com.guiabolso.domain.Livro;

public interface LivroService {
	
	public Optional<Livro> buscar(Long id);
	
	public List<Livro> buscar(String isbn);
	
	public Livro gravar(Livro livro);
	
	public List<Livro> buscar();		

}
