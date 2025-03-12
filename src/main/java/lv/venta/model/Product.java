package lv.venta.model;
import lombok.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {
	@Setter(value = AccessLevel.NONE)
	private long id;
	private String title;
	private String description;
	private float price;
	private int quantity;
	
	public Product(String title, String description, float price, int quantity) {
		setTitle(title);
		setDescription(description);
		setPrice(price);
		setQuantity(quantity);
	}
	
}
