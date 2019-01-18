package br.com.guiabolso.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guiabolso.domain.Livro;
import br.com.guiabolso.domain.LivroResponse;
import br.com.guiabolso.exceptions.LivroNotFoundException;
import br.com.guiabolso.helpers.LivroHelper;
import br.com.guiabolso.repositories.LivroRepository;

@Service
public class LivroServiceImpl implements LivroService {

	Logger logger = LoggerFactory.getLogger(LivroServiceImpl.class);
	
	@Autowired
	public LivroRepository livroRepository;
	
	private final String url = "https://kotlinlang.org/docs/books.html";
	
	@Override
	public Optional<Livro> buscar(Long id) {
		return livroRepository.findById(id);
	}
	
	@Override
	public List<Livro> buscar(String isbn) {
		return livroRepository.findByIsbn(isbn);
	}

	@Override
	public Livro gravar(Livro livro) {
		return livroRepository.save(livro);
	}

	@Override
	public LivroResponse buscar() {
		
		LivroResponse response = new LivroResponse();
		List<Livro> livros = new ArrayList<Livro>();
		
		try {
			
			Document documento = Jsoup.connect(url).get();
			if(documento != null) {
				livros = LivroHelper.montarLivros(documento);
				if(livros != null) {
					response.setBooks(livros);
					response.setNumberBooks(livros.size());
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new LivroNotFoundException(e.getMessage());
		}
		return response;
	}
	


}
