package br.com.guiabolso.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guiabolso.domain.Livro;
import br.com.guiabolso.exceptions.LivroNotFoundException;
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
		String html = null;
		List<Livro> livros = new ArrayList<Livro>();
		
		try {
			html = Jsoup.connect(url).get().className();
			
			
		} catch (IOException e) {
			throw new LivroNotFoundException(e.getMessage());
		}
		return livros;
	}
	
	public static void main(String[] args) {
		String url = "https://kotlinlang.org/docs/books.html";
		Elements titles = null;
		Elements paragraph =  null;
		Elements lang = null;
		Elements links = null;
		List<Livro> livros = new ArrayList<Livro>();
		
		try {
			Document livroHtml = Jsoup.connect(url).get();
			titles = livroHtml.select("h2");
			lang = livroHtml.getElementsByClass("book-lang");
			paragraph = livroHtml.select("p");
			
			for(int i = 0; i < titles.size(); i++) {
				livros.add(new Livro(i, titles.get(i).text(), "", "", ""));
			}
			
		} catch (IOException e) {
			throw new LivroNotFoundException(e.getMessage());
		}
	}


}
