package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

public interface IProductRepo extends CrudRepository<Product, Long>{
	
	
	
	public abstract boolean existsByTitleAndDescriptionAndPrice(String title, String description, float price) ;

	public abstract Product findByTitleAndDescriptionAndPrice(String title, String description, float price);
	
	public abstract ArrayList<Product> findByPriceLessThanEqual(float threshold);
	
	public abstract ArrayList<Product> findByQuantityGreaterThanEqual (int threshold);
	
	public abstract ArrayList<Product> findByTitleOrDescriptionContaining(String text, String text2);
	
	@Query(nativeQuery = true, value = "SELECT sum(price * quantity) FROM product_table;")
	public abstract float calculateSumOfProductValues();
	
}
