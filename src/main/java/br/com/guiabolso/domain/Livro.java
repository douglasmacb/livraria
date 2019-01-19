package br.com.guiabolso.domain;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "livro")
public class Livro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6091972798183480861L;

	@Id
	private String id;

	@NotEmpty
	private String title;

	@NotEmpty
	private String description;

	@NotEmpty
	private String isbn;

	@NotEmpty
	private String language;

	public Livro() {
	}
	
	public Livro(@NotEmpty String title, @NotEmpty String description, @NotEmpty String isbn,
			@NotEmpty String language) {
		super();
		this.title = title;
		this.description = description;
		this.isbn = isbn;
		this.language = language;
	}

	public Livro(String id, @NotEmpty String title, @NotEmpty String description, @NotEmpty String isbn,
			@NotEmpty String language) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.isbn = isbn;
		this.language = language;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
