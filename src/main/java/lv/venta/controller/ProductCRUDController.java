package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
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
	public String postControllerCreateNewProduct(@Valid Product product, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "create-product";
		}
		
		try {
			System.out.println(product);
			prodService.createProduct(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
			return "redirect:/product/crud/all";
		}catch(Exception e) {
			
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}
	@GetMapping("/update/{id}")//localhost:8080/product/crud/update{0}
	public String getControllerUpdateProduct(@PathVariable(name = "id") long id, Model model) {
		try {
			Product productFound = prodService.retrieveById(id);
			model.addAttribute("product", productFound);
			return "update-product";
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}
	@PostMapping("/update/{id}")
	public String postControllerUpdateProduct(@PathVariable(name = "id") long id, @Valid Product product, BindingResult result, Model model) {
		try {
			prodService.updateProduct(id, product.getDescription(), product.getPrice(), product.getQuantity());
			return "redirect:/product/crud/all";
		}catch(Exception e) {
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}
	@GetMapping("delete/{id}")////localhost:8080/product/crud/delete/5
	public String getControllerDeleteProduct(@PathVariable(name = "id") long id, Model model) {
		try {
			prodService.deleteProduct(id);
			model.addAttribute("product", prodService.retrieveAll());
			return "show-two-products";
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("package", e.getMessage());
			return "show-error";
		}
	}
}
