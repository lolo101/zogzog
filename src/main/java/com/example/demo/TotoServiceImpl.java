package com.example.demo;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class TotoServiceImpl implements TotoService {

	private final TotoRepository totoRepository;
	private final ZogzogRepository zogzogRepository;
	private final NotifService notifService;

	TotoServiceImpl(TotoRepository totoRepository, ZogzogRepository zogzogRepository, NotifService notifService) {
		this.totoRepository = totoRepository;
		this.zogzogRepository = zogzogRepository;
		this.notifService = notifService;
	}

	@Override
	public Iterable<Toto> findAll() {
		return totoRepository.findAll();
	}

	@Override
	public Optional<Toto> addZogzog(Long id, String value) {
		return totoRepository.findById(id)
				.map(toto -> doAddZogzog(toto, value));
	}

	@Override
	public Optional<Toto> cancel(Long id) {
		return totoRepository.findById(id)
				.map(this::doCancel);
	}

	private Toto doAddZogzog(Toto toto, String value) {
		Zogzog zogzog = zogzogRepository.save(new Zogzog(toto, value));
		toto.getZogzogs().add(zogzog);
		return toto;
	}

	private Toto doCancel(Toto toto) {
		zogzogRepository.deleteAllByToto(toto);
		notifService.notifyCanceled(toto);
		return toto;
	}
}
