package br.com.guiabolso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LivroNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5139260401833602486L;
	
	public LivroNotFoundException(Exception e) {
		super(e.getMessage());
	}
	
	public LivroNotFoundException(String id) {
		super("Livro não encontrado: " + id);
	}

}
