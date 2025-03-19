package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductFilterService;

@Service
public class ProductFiltAndCalcServiceImpl implements IProductFilterService{
	
	@Autowired
	private IProductRepo prodRepo;
	
	@Override
	public ArrayList<Product> getAllProductsWherePriceLessThan(float threshold) throws Exception{
		if(threshold < 0 || threshold > 1000) {
			throw new Exception("Incorrect threshold");
		}
		ArrayList<Product> result = prodRepo.findByPriceLessThanEqual(threshold);
		if(result.isEmpty()) {
			throw new Exception("There is no product that price is smaller than " + threshold);
		}
		return result;
	}
	
	@Override
	public ArrayList<Product> getAllProductsWhereQuantityMoreThan(int threshold) throws Exception{
		if(threshold < 0 || threshold > 1000) {
			throw new Exception("Incorrect threshold");
		}
		ArrayList<Product> result = prodRepo.findByQuantityGreaterThanEqual(threshold);
		if(result.isEmpty()) {
			throw new Exception("There is no quantity more than " + threshold);
		}
		return result;
	
	}
	
	@Override
	public ArrayList<Product> getAllProductsWhereTitleOrDescriptionContains(String text) throws Exception{
		if(text == null) {
			throw new Exception("Text cannot be null");
		}
		ArrayList<Product> result = prodRepo.findByTitleOrDescriptionContaining(text, text);
		if(result.isEmpty()) {
			throw new Exception("There is no title or description conataing " + text);
		}
		return result;
	}
	
	@Override
	public float getIncomeFromProducts() throws Exception{
		if(prodRepo.count() == 0) {
			throw new Exception("There is no product in DB");
		}
		float result = prodRepo.calculateSumOfProductValues();
		return result;
	}
	
}
