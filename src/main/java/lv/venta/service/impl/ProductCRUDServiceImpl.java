package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IproductCRUDService;

@Service
public class ProductCRUDServiceImpl implements IproductCRUDService{
	
	
	@Autowired
	private IProductRepo prodRepo;
	@Override
	public void createProduct(String title, String description, float price, int quantity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		if(prodRepo.count() == 0) {
			throw new Exception("Product table is empty");
		}
		ArrayList<Product> result = (ArrayList<Product>) prodRepo.findAll();
		return result;
	}

	@Override
	public Product retrieveById(long id) throws Exception {
		if(id < 0) {
			throw new Exception("ID cannot be negative!");
		}
		if(!prodRepo.existsById(id)){
			throw new Exception("ID does not exist!");
		}
		
		Product result = prodRepo.findById(id).get();
		return result;
		
	}

	@Override
	public void updateProduct(long id, String description, float price, int quantity) throws Exception {
		Product product = retrieveById(id);
		
		if(description == null || !description.matches("[A-Za-z :;]{3,30}") || price < 0 || quantity < 0) {
			throw new Exception("Description, price and quantity must be filled or bigger than 0");
		}
		
		if(!product.getDescription().equals(description)){
			product.setDescription(description);
		}
		if(product.getPrice() != price) {
			product.setPrice(price);
		}
		if(product.getQuantity() != quantity) {
			product.setQuantity(quantity);
		}
		
		prodRepo.save(product);
		
	}

	@Override
	public void deleteProduct(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
