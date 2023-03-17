package com.sap.testing;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController 
public class RealTimeNewsController {
	
	
	public CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();
	
	// method for client subscriptions
	@CrossOrigin
	@RequestMapping (value="/subscribe", consumes = MediaType.ALL_VALUE)
	public SseEmitter subscribe() {
		
		SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

		try {
			emitter.send(SseEmitter.event().name("INIT"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		emitter.onCompletion(() -> emitters.remove(emitter));
		emitters.add(emitter);
		
		
		return emitter;
		
	}

	// method for dispatching events to all clients 
	@PostMapping(value = "/dispatchEvent")
	public void dispatchEventToClients (@RequestParam String freshNews) {
		
		for (SseEmitter emitter : emitters) {
			try {
				emitter.send(SseEmitter.event().name("latestNews").data(freshNews));
				
			} catch (IOException e) {
				emitters.remove(emitter);
			}
			
			
			
		}
		
		
	}
	
	

}
