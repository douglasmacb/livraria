package br.com.guiabolso.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.guiabolso.domain.Livro;

public class LivroHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(LivroHelper.class);
	public final static String URL_BOOKS = "https://kotlinlang.org/docs/books.html";
	
	final static String rx1 = "^\\d{9}[\\d|X]$";
	final static String rx2 = "ISBN\\x20(?=.{13}$)\\d{1,5}([- ])\\d{1,7}\\1\\d{1,6}\\1(\\d|X)$";
	final static String rx3 = "\\d{3}-?\\d{10,12}";
	final static String rx4 = "ISBN-13.*(978[-]*\\d{10})";
	final static String rx5 = "^(97(8|9))?\\d{9}(\\d|X)$";
	
	final static List<Pattern> rxs = Arrays.asList(
			Pattern.compile(rx1), 
			Pattern.compile(rx2),
			Pattern.compile(rx3), 
			Pattern.compile(rx4), 
			Pattern.compile(rx5)
    );
	
	public static List<Livro> montaLivros(Document documento) {
		
		LOGGER.info("Montando a estrutura dos livros...");
		
		Elements links = null;
		Elements titulos = null;
		Elements linguagens = null;
		Elements artigo = null;
		List<String> listaIsbn = null;
		 
		List<Livro> livros = new ArrayList<Livro>();
		List<String> linksPaginasLivros = new ArrayList<String>();
		
		artigo = documento.select("article");
		
		if(artigo != null && !artigo.isEmpty()) {
			links = artigo.select("a + p").select("a[href]");
			titulos = artigo.select("h2");
			linguagens = documento.getElementsByClass("book-lang");
			String descricoes[] = obterDescricoes(artigo);
			
			if(links != null && !links.isEmpty()) {
				links.forEach(link -> linksPaginasLivros.add(link.attributes().get("href")));
			}
			listaIsbn = obterListaIsbn(linksPaginasLivros);
			
			for(Integer i = 0; i < titulos.size(); i++) {
				livros.add(new Livro(titulos.get(i).text(), descricoes[i+1], listaIsbn.get(i), linguagens.get(i).text()));
			}
		}
		return livros;
	}
	
	public static List<String> obterListaIsbn(List<String> links) {
		
		LOGGER.info("Obtendo a lista de ISBN...");
		
		List<String> listaIsbn = new ArrayList<String>();
		boolean encontrou = false;
		for(String url : links) {
			try {
				Elements elementos = Jsoup.connect(url).get().select("body");
				
				for (Pattern r : rxs) {
					Matcher match = r.matcher(elementos.text());
					
					if (match.find()) {
						listaIsbn.add(match.group());
						encontrou = true;
						break;
					} else {
						encontrou = false;
					}
				}
				if(!encontrou) {
					listaIsbn.add("Unavailable");
				}
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
		}
		return listaIsbn;
	}

	public static String[] obterDescricoes(Elements artigo) {
		
		LOGGER.info("Obtendo as descrições...");
		
		int indice = 0;
		Elements elementos = artigo.select("h2, p");
		String[] descricoes = new String[elementos.size()];
		Iterator<Element> itens = elementos.iterator();
		
		while(itens.hasNext()) {
			Element elemento = itens.next();
			if(elemento.toString().startsWith("<p>")) {
				if(descricoes[indice] == null) {
					descricoes[indice] = new String();
				}
				descricoes[indice] = descricoes[indice].concat(elemento.text());
			}
			if(elemento.toString().endsWith("</h2>")) {
				indice++;
			}
		}
		return descricoes;
	}
}
