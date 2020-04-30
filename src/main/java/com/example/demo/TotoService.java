package com.example.demo;

import java.util.Optional;

interface TotoService {

	Iterable<Toto> findAll();

	Optional<Toto> cancel(Long id);

	Optional<Toto> addZogzog(Long id, String value);
}
