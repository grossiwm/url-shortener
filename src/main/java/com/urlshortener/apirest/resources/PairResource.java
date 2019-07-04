package com.urlshortener.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	@ApiOperation(value="List of all url pairs.")
	public List<Pair> pairsList() {
		return pairRepository.findAll();
	}
	
	@PostMapping("/pair")
	@ApiOperation(value="Save new pair.")
	public Pair savePair(@RequestBody Pair pair) {
		return pairRepository.save(pair);
	}
	
	@DeleteMapping("/pair")
	@ApiOperation(value="Delete a pair, receiving a pair as parameter.")
	public void deletePair(@RequestBody Pair pair) {
		pairRepository.delete(pair);
	}
	
	@PutMapping("/pair")
	@ApiOperation(value="Update pair.")
	public Pair updatePair(@RequestBody Pair pair) {
		return pairRepository.save(pair);
	}
	
	
}
