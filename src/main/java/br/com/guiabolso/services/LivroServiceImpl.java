package br.com.guiabolso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guiabolso.domain.Livro;
import br.com.guiabolso.repositories.LivroRepository;

@Service
public class LivroServiceImpl implements LivroService {

	@Autowired
	public LivroRepository livroRepository;
	
	@Override
	public Optional<Livro> buscar(Long id) {
		return livroRepository.findById(id);
	}

	@Override
	public Livro gravar(Livro livro) {
		return livroRepository.save(livro);
	}
}
