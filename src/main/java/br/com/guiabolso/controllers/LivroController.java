package br.com.guiabolso.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.guiabolso.domain.Livro;
import br.com.guiabolso.exceptions.LivroNotFoundException;
import br.com.guiabolso.helpers.MensagemHelper;
import br.com.guiabolso.services.LivroService;

@RestController
@RequestMapping("/books")
public class LivroController {
	
	Logger logger = LoggerFactory.getLogger(LivroController.class);
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping(path = "/{id}")
	Livro obterLivro(@PathVariable Long id) {
		
		Optional<Livro> livro = livroService.buscar(id);
		
		if (!livro.isPresent()) {
			throw new LivroNotFoundException(id);
		}
		return livroService.buscar(id).orElseThrow(() -> new LivroNotFoundException(id));
	}

	@PostMapping
	ResponseEntity<String> gravar(@Valid @RequestBody Livro livro, Errors errors) {

		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(MensagemHelper.formataMensagensErro(errors));
		}
		
		livroService.gravar(livro);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.buildAndExpand(livro.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	

}
