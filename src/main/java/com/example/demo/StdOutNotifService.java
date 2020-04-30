package com.example.demo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
class StdOutNotifService implements NotifService {

	private final Object lock = new Object();

	@Async
	@Override
	public void notifyCanceled(Toto toto) {
		synchronized(lock) {
			try {
				lock.wait(1000);
			} catch (InterruptedException ex) {
				System.out.println("Interrupted ! " + ex);
			}
		}
		System.out.println(String.format("Zogzogs %s have been deleted", toto.getZogzogs()));
	}
}
