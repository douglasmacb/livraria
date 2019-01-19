package br.com.guiabolso.services;

import java.util.Optional;

import br.com.guiabolso.domain.Livro;
import br.com.guiabolso.domain.LivroResponse;

public interface LivroService {
	
	public Optional<Livro> buscar(String id);
	
	public Livro gravar(Livro livro);
	
	public LivroResponse buscar();		

}
