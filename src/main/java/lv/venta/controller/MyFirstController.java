package lv.venta.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller


public class MyFirstController {
	
	private Random rand = new Random();
	@GetMapping("/simple") //localhost:8080/simple
	public String myFirstGetController() {
		System.out.println("Pirmais kontrolieris nostradaja");
		return "simple-page"; //paraadiis simple-page.html lapu
	}
	
	@GetMapping("/getdata")
	public String getControllerSendData(Model model) {
		System.out.println("Send data kontrolieris nostr'ad'aja");
		String data = "Linda ->" + rand.nextInt(0, 101);
		model.addAttribute("package", data);
		return "show-data-page";
	}
}

