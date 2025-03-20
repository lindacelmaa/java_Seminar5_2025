package lv.venta.controller;
 
 import java.util.ArrayList;
 
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestMapping;
 
 import lv.venta.model.Product;
 import lv.venta.service.IProductFilterService;
 
 @Controller
 @RequestMapping("/product/process")
 public class ProductFiltAndCalcController {
 	
 	@Autowired
 	private IProductFilterService prodService;
 	
 	@GetMapping("/price/{threshold}")//localhost:8080/product/process/price/2.77
 	public String getControllerProductPriceLessThan
 	(@PathVariable(name ="threshold") float threshold, Model model)
 	{
 		try {
 			ArrayList<Product> filteredProducts = prodService.getAllProductsWherePriceLessThan(threshold);
 			model.addAttribute("package", filteredProducts);
 			return "show-multiple-products";
 		} catch (Exception e) {
 			model.addAttribute("package", e.getMessage());
 			return "show-error";
 		}
 		
 		
 	}
 	
 	@GetMapping("/quantity/{threshold}") // localhost:8080/product/process/quantity/10
    public String getControllerProductQuantityMoreThan(@PathVariable(name = "threshold") int threshold, Model model) {
        try {
            ArrayList<Product> filteredProducts = prodService.getAllProductsWhereQuantityMoreThan(threshold);
            model.addAttribute("package", filteredProducts);
            return "show-multiple-products";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "show-error";
        }
    }
 	
 	 @GetMapping("/search/{text}") // localhost:8080/product/process/search/laptop
     public String getControllerProductTitleOrDescriptionContains(@PathVariable(name = "text") String text, Model model) {
         try {
             ArrayList<Product> filteredProducts = prodService.getAllProductsWhereTitleOrDescriptionContains(text);
             model.addAttribute("package", filteredProducts);
             return "show-multiple-products";
         } catch (Exception e) {
             model.addAttribute("package", e.getMessage());
             return "show-error";
         }
     }
 	 
 	@GetMapping("/income") // localhost:8080/product/process/income
    public String getControllerIncomeFromProducts(Model model) {
        try {
            float income = prodService.getIncomeFromProducts();
            model.addAttribute("package", income);
            return "show-income";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "show-error";
        }
    }
 	
 
 }