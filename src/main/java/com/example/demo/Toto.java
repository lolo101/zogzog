package com.example.demo;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Toto {

	static final Toto EMPTY = new Toto();

	@Id
	private Long id;

	@OneToMany(mappedBy = "toto")
	private Collection<Zogzog> zogzogs;

	public Collection<Zogzog> getZogzogs() {
		return zogzogs;
	}
}
