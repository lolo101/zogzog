package com.example.demo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
class StdOutNotifService implements NotifService {

	@Override
	@Async
	public void notifyCanceled(Toto toto) {
		System.out.println(String.format("Zogzogs %s have been deleted", toto.getZogzogs()));
	}
}
