package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.model.Product;
import lv.venta.service.IproductCRUDService;

@Controller
@RequestMapping("/product/crud")
public class ProductCRUDController {
	
	@Autowired
	private IproductCRUDService prodService;
	
	@GetMapping("/all") //localhost:8080/product/crud/all
	public String getControllerGetAllProducts(Model model) {
		try {
			ArrayList<Product> allProducts = prodService.retrieveAll();
			model.addAttribute("package", allProducts);
			return "show-two-products";
		}catch (Exception e){
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
		
	}
	
	//localhost:8080/product/crud/one?id=1
	@GetMapping("/one")
	public String getControllerGetOneProduct(@RequestParam(name = "id")long id, Model model) {
		try {
			Product productFound = prodService.retrieveById(id);
			model.addAttribute("package", productFound);
			return "show-one-product";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
		
	}
	
	////localhost:8080/product/crud/one/1
	@GetMapping("/one/{id}")
	public String getControllerOneProduct(@PathVariable("id") long id, Model model) {
		try {
			Product productFound = prodService.retrieveById(id);
			model.addAttribute("package", productFound);
			return "show-one-product";
			
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}
	
	@GetMapping("/create")//localhost:8080/product/crud/create
	public String getControllerCreateNewProduct(Model model) {
		model.addAttribute("product", new Product());
		return "create-product"; 
		
	}
	@PostMapping("/create")
	public String postControllerCreateNewProduct(Product product, Model model) {
		try {
			System.out.println(product);
			prodService.createProduct(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
			return "redirect:/product/crud/all";
		}catch(Exception e) {
			
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}
	
	
	
}
