package pl.strefakursow.restapi.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {
	@GetMapping("/api/hello")
	public Greeting hello() {
		Greeting greeting = new Greeting();
		greeting.setMsg("hello");
		return greeting;
	}
}
