package br.com.guiabolso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guiabolso.domain.Livro;
import br.com.guiabolso.repositories.LivroRepository;

@Service
public class LivroServiceImpl implements LivroService {

	@Autowired
	public LivroRepository livroRepository;
	
	@Override
	public Iterable<Livro> obterLivros() {
		return livroRepository.findAll();
	}

	@Override
	public void gravar(Livro livro) {
		livroRepository.save(livro);
	}

}
