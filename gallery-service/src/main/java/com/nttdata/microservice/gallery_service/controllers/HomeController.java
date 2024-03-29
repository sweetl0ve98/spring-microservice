package com.nttdata.microservice.gallery_service.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.ribbon.eureka.EurekaRibbonClientConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nttdata.microservice.gallery_service.entities.Gallery;

@RestController
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	@RequestMapping("/")
	public String home() {
		// This is useful for debugging
		// When having multiple instance of gallery service running at different ports.
		// We load balance among them, and display which instance received the request.
		return "Hello from Gallery Service running at port: " + env.getProperty("local.server.port");
	}
  
	@RequestMapping("/{id}")
	public Gallery getGallery(@PathVariable final int id) {
		// create gallery object  
		Gallery gallery = new Gallery();
		gallery.setId(id);

		// get list of available images 
		//@SuppressWarnings("unchecked")    // we'll throw an exception from image service to simulate a failure
		List<Object> images = restTemplate.getForObject("http://10.10.2.98:8200/images", List.class);

		gallery.setImages(images);
	
		return gallery;
	}
	
	@RequestMapping("/test1")
	public String getTest() {
		// create gallery object  
		// get list of available images 
		//@SuppressWarnings("unchecked")    // we'll throw an exception from image service to simulate a failure
		ResponseEntity<String> response = restTemplate.getForEntity("http://10.10.2.122:8200/test", String.class);

		return response.getBody();
	}
	
	// -------- Admin Area --------
	// This method should only be accessed by users with role of 'admin'
	// We'll add the logic of role based auth later
	@RequestMapping("/admin")
	public String homeAdmin() {
		return "This is the admin area of Gallery service running at port: " + env.getProperty("local.server.port");
	}
}