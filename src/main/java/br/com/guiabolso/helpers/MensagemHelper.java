package br.com.guiabolso.helpers;

import java.util.stream.Collectors;

import org.springframework.validation.Errors;

public class MensagemHelper {
	
	public static String formataMensagensErro(Errors errors) {
		return errors.getFieldErrors()
				.stream()
				.map(x -> "O campo ".concat(x.getField()) + " ".concat(x.getDefaultMessage()))
				.collect(Collectors.joining("\n"));
	}
}
