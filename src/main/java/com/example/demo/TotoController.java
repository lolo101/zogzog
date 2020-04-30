package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("toto")
public class TotoController {

	private final TotoService totoService;

	TotoController(TotoService totoService) {
		this.totoService = totoService;
	}

	@GetMapping
	public Iterable<Toto> findAll() {
		return totoService.findAll();
	}

	@GetMapping("add/{id}/{value}")
	public ResponseEntity<Toto> addZogzog(@PathVariable("id") Long id, @PathVariable("value") String value) {
		return ResponseEntity.of(totoService.addZogzog(id, value));
	}

	@GetMapping("cancel/{id}")
	public ResponseEntity<Toto> cancel(@PathVariable("id") Long id) {
		return ResponseEntity.of(totoService.cancel(id));
	}
}
