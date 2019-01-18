package br.com.guiabolso.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guiabolso.domain.Livro;
import br.com.guiabolso.exceptions.LivroNotFoundException;
import br.com.guiabolso.helpers.LivroHelper;
import br.com.guiabolso.repositories.LivroRepository;

@Service
public class LivroServiceImpl implements LivroService {

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
	public List<Livro> buscar() {
		
		List<Livro> livros = new ArrayList<Livro>();
		try {
			
			Document documento = Jsoup.connect(url).get();
			if(documento != null) {
				return livros = LivroHelper.montarLivros(documento);
			}
		} catch (IOException e) {
			throw new LivroNotFoundException(e.getMessage());
		}
		return livros;
	}
	


}
