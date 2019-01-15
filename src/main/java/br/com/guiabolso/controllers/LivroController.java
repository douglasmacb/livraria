package br.com.guiabolso.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.guiabolso.domain.Livro;
import br.com.guiabolso.exceptions.LivroNotFoundException;
import br.com.guiabolso.services.LivroService;

@RestController
@RequestMapping("/books")
public class LivroController {
	
	Logger logger = LoggerFactory.getLogger(LivroController.class);
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping
    @ResponseBody Iterable<Livro> obterLivros() {
        return livroService.obterLivros();
    }
	
	@GetMapping(path = "/{id}")
	Livro obterLivro(@PathVariable Long id) {
		return livroService.obterLivro(id)
				.orElseThrow(() -> new LivroNotFoundException(id));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	Livro gravar(@RequestBody Livro livro) {
		return livroService.gravar(livro);
	}
}
