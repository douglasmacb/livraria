package br.com.guiabolso.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.guiabolso.domain.Livro;

public class LivroHelper {

	final static String regex1 = "^\\d{9}[\\d|X]$";
	final static String regex2 = "ISBN\\x20(?=.{13}$)\\d{1,5}([- ])\\d{1,7}\\1\\d{1,6}\\1(\\d|X)$";
	final static String regex3 = "\\d{3}-?\\d{10}";
	final static String regex4 = "ISBN-13.*(978[-]*\\d{10}) ";
	final static String regex5 = "^(97(8|9))?\\d{9}(\\d|X)$";
	
	final static List<Pattern> rxs = Arrays.asList(Pattern.compile(regex1), Pattern.compile(regex2),
			Pattern.compile(regex3), Pattern.compile(regex4), Pattern.compile(regex5));
	
	public static List<Livro> montarLivros(Document documento) {
		List<Livro> livros = new ArrayList<Livro>();
		List<String> linksPaginaLivros = new ArrayList<String>();

		Elements links = null;
		Elements titulos = null;
		Elements languages = null;
		Elements artigo = null;
		
		artigo = documento.select("article");
		links = artigo.select("a + p").select("a[href]");
		titulos = artigo.select("h2");
		languages = documento.getElementsByClass("book-lang");
		
		links.forEach(link -> linksPaginaLivros.add(link.attributes().get("href")));
		
		String descricoes[] = obterDescricoes(artigo);
		List<String> listaIsbn = obterListaIsbn(linksPaginaLivros);
		
		for(Integer i = 0; i < titulos.size(); i++) {
			livros.add(new Livro(i.longValue(), titulos.get(i).text(), descricoes[i+1], listaIsbn.get(i), languages.get(i).text()));
		}
		return livros;
	}
	
	public static List<String> obterListaIsbn(List<String> links) {
		List<String> listaIsbn = new ArrayList<String>();
		
		for(String url : links) {
			try {
				Elements elementos = Jsoup.connect(url).get().getAllElements();
				
				for (Element elemento : elementos) {
					for (Pattern rx : rxs) {
					}
				}
		        
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listaIsbn;
	}

	public static String[] obterDescricoes(Elements artigo) {
		int indice = 0;
		Elements elementos = artigo.select("h2, p");
		String[] descricoes = new String[elementos.size()];
		Iterator<Element> itens = elementos.iterator();
		while(itens.hasNext()) {
			Element elemento = itens.next();
			if(elemento.toString().startsWith("<p>")) {
				if(descricoes[indice] == null) descricoes[indice] = new String();
				descricoes[indice] = descricoes[indice].concat(elemento.text());
			}
			if(elemento.toString().endsWith("</h2>")) {
				indice++;
			}
		}
		return descricoes;
	}
}
