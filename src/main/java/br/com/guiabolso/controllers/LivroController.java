package br.com.guiabolso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.guiabolso.domain.Livro;
import br.com.guiabolso.services.LivroService;

@Controller
@RequestMapping("/")
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping("/books")
    public @ResponseBody Iterable<Livro> obterLivros() {
        return livroService.obterLivros();
    }

	@RequestMapping(value = "/books", method = RequestMethod.POST) 
	public ResponseEntity<String> gravar(@RequestBody Livro livro) {
		livroService.gravar(livro);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
