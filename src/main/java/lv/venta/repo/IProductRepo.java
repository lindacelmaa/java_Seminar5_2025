package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

public interface IProductRepo extends CrudRepository<Product, Long>{
	
	
	
	public abstract boolean existsByTitleAndDescriptionAndPrice(String title, String description, float price) ;

	public abstract Product findByTitleAndDescriptionAndPrice(String title, String description, float price);
	
}
