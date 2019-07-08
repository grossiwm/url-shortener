package com.urlshortener.apirest.resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.apirest.models.Pair;
import com.urlshortener.apirest.repository.PairRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/")
@Api(value="API REST URL Shortener")
@CrossOrigin(origins="*")
public class PairResource {
	
	@Autowired
	PairRepository pairRepository;
	
	@GetMapping("/pairs")
	@ApiOperation(value="Lista todos os pares.")
	public List<Pair> pairsList() {
		return pairRepository.findAll();
	}
	
	@PostMapping("/pair")
	@ApiOperation(value="Salva novo par.")
	public ResponseEntity<Object> savePair(@RequestBody @Valid Pair pair) {
		
		Pair existingPairWithOriginal = pairRepository.findByOriginal(pair.getOriginal());
		
		
		if (existingPairWithOriginal != null) {
			return new ResponseEntity<>(existingPairWithOriginal, HttpStatus.OK);
		} else if (shortenedExists(pair)) {
			return ResponseEntity.badRequest()
			.body("Algo deu errado.");
		} else {
			pairRepository.save(pair);
			return new ResponseEntity<>(pair, HttpStatus.CREATED);
		}

	}
	
	@DeleteMapping("/pair")
	@ApiOperation(value="Recebe um par e o deleta.")
	public void deletePair(@RequestBody Pair pair) {
		pairRepository.delete(pair);
	}
	
	@PutMapping("/pair")
	@ApiOperation(value="Atualiza um par de urls..")
	public ResponseEntity<Object> updatePair(@RequestBody @Valid Pair pair) {
		
		Pair existingPairWithOriginal = pairRepository.findByOriginal(pair.getOriginal());
		
		
		if (existingPairWithOriginal != null) {
			return new ResponseEntity<>(existingPairWithOriginal, HttpStatus.OK);
		} else if (shortenedExists(pair)) {
			return ResponseEntity.badRequest()
			.body("Algo deu errado.");
		} else {
			pairRepository.save(pair);
			return new ResponseEntity<>(pair, HttpStatus.CREATED);
		}


	}
	
	@ApiOperation(value="Redireciona para url correspondente.")
	@GetMapping("/s/{shortened}")
	public void redirectToOriginal(HttpServletResponse response, @PathVariable("shortened") String shortened) throws IOException {
		Pair pair = pairRepository.findByShortened(shortened);
		if (pair != null) {
			String original = pair.getOriginal();
			response.sendRedirect(original);
		} else {
			return;
		}
	}
	
	private Boolean shortenedExists(Pair pair) {
		return pairRepository.findByShortened(pair.getShortened()) != null;
	}
	
	
}
