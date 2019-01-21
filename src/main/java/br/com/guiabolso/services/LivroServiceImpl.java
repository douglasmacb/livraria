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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.guiabolso.domain.Livro;
import br.com.guiabolso.domain.LivroResponse;
import br.com.guiabolso.exceptions.LivroNotFoundException;
import br.com.guiabolso.helpers.LivroHelper;
import br.com.guiabolso.repositories.LivroRepository;

@Service
public class LivroServiceImpl implements LivroService {

	public static final Logger LOGGER = LoggerFactory.getLogger(LivroServiceImpl.class);
	
	@Autowired
	public LivroRepository livroRepository;
	
	@Override	
	@Cacheable(value = "book")
	public Optional<Livro> buscar(String id) {
		LOGGER.info("Acessando o banco de dados para buscar o livro id : {} ", id);
		return livroRepository.findById(id);
	}
	
	@Override
	public Livro gravar(Livro livro) {
		LOGGER.info("Gravando o livro : {} ", livro.getTitle());
		return livroRepository.save(livro);
	}

	@Override
	@Cacheable(value = "books")
	public LivroResponse buscar() {
		
		LOGGER.info("Buscando os livros do website : {} ", LivroHelper.URL_BOOKS);
		LivroResponse response = new LivroResponse();
		List<Livro> livros = new ArrayList<Livro>();
		
		try {
			livros = livroRepository.findAll();
			
			if(livros.size() == 0) {			
				Document documento = Jsoup.connect(LivroHelper.URL_BOOKS).get();
				if(documento != null) {
					livros = LivroHelper.montaLivros(documento);
					if(livros != null && livros.size() > 0) {						
						livroRepository.saveAll(livros);
					}
				}
			}
			response.setBooks(livros);
			response.setNumberBooks(livros.size());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new LivroNotFoundException(e);
		}
		return response;
	}

}
