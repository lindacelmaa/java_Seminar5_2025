package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.model.Product;

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
	
	@GetMapping("/getproduct")
	public String getControllerSendProduct(Model model) {
		Product newProduct = new Product("Abols", "Garsigs", 0.99f, 4);
		model.addAttribute("package", newProduct);
		return "show-one-product";
	
	}
	@GetMapping("/getproducts")
	public String getControllerSendProducts(Model model) {
		ArrayList<Product> products = new ArrayList<>();
		Product newProduct1 = new Product("Abols", "Garsigs", 0.99f, 4);
		Product newProduct2 = new Product("Bumbieris", "Zals", 0.99f, 4);
		products.addAll(Arrays.asList(newProduct1, newProduct2));
		model.addAttribute("package", products);
		return "show-two-products";
	
	}
	
}

