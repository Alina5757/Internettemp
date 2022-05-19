package ustu.is.InternetLab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@SpringBootApplication
@RestController
public class InternetLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternetLabApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
		return String.format("Hello %s!", name);
	}

	@GetMapping("/calc")
	public String calc(@RequestParam(defaultValue = "10, 5") List<Integer> args){
		return String.format("Elem1: %d; Elem2: %d ||||  " +
						"Summ: %d, Razn: %d, Umn: %d, Del: %.2f",
				args.get(0),
				args.get(1),
				(args.get(0) + args.get(1)),
				(args.get(0) - args.get(1)),
				(args.get(0) * args.get(1)),
				((float)args.get(0) / (float)args.get(1)));
	}
}
