package br.com.guiabolso.exceptions;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LivroNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5139260401833602486L;
	
	public LivroNotFoundException(String message) {
		super(message);
	}
	
	public LivroNotFoundException(ObjectId id) {
		super("Livro não encontrado: " + id);
	}

}
