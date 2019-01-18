package br.com.guiabolso.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LivroResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5976604024212822778L;
	
	public int numberBooks;
	public List<Livro> books = new ArrayList<Livro>();

	/**
	 * @return the numberBooks
	 */
	public int getNumberBooks() {
		return numberBooks;
	}

	/**
	 * @param numberBooks the numberBooks to set
	 */
	public void setNumberBooks(int numberBooks) {
		this.numberBooks = numberBooks;
	}

	/**
	 * @return the books
	 */
	public List<Livro> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Livro> books) {
		this.books = books;
	}

}
