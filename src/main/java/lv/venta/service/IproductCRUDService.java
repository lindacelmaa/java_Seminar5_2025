package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface IproductCRUDService {
	
	public abstract void createProduct(String title, String description, float price, int quantity) throws Exception;
	
	public abstract ArrayList<Product> retrieveAll() throws Exception;
	
	public abstract Product retrieveById(long id) throws Exception;
	
	public abstract void updateProduct(long id, String description, float price, int quantity) throws Exception;
	
	public abstract void deleteProduct(long id) throws Exception;

}
