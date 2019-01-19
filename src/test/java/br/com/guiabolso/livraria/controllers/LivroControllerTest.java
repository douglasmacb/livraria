package br.com.guiabolso.livraria.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;

import br.com.guiabolso.controllers.LivroController;
import br.com.guiabolso.domain.Livro;
import br.com.guiabolso.livraria.LivrariaApplicationTests;

public class LivroControllerTest extends LivrariaApplicationTests {
	
	private MockMvc mockMvc = null;
	
	@Autowired
	LivroController livroController;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(livroController).build();
	}
	
	@Test
	public void testGETAllBooks() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/books")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testPOSTBooks() throws Exception {
		Gson gson = new Gson();
		Livro livro = new Livro("6b525ddabdd5c03419e98788", "Kotlin", "The best language to develop good softwares", "87728595", "EN");
		String json = gson.toJson(livro);

	    this.mockMvc.perform(MockMvcRequestBuilders.post("/books")
	        .contentType(MediaType.APPLICATION_JSON).content(json))
	        .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void testGETSingleBook() throws Exception {
	    this.mockMvc.perform(MockMvcRequestBuilders.get("/books/" + "6b525ddabdd5c03419e98788"))
	        .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
