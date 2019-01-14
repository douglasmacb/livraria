package br.com.guiabolso.services;

import br.com.guiabolso.domain.Livro;

public interface LivroService {
	public Iterable<Livro> obterLivros(); 
	
	public void gravar(Livro livro);
}
