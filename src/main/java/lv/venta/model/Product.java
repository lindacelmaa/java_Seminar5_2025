package lv.venta.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
@Table(name = "ProductTable")
@Entity
public class Product {
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "title")
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z ]{2,15}", message = "The first letter should be in capital.")
	//@Size(min=3, max = 16)
	private String title;
	
	@Column(name = "description")
	@NotNull
	@Pattern(regexp = "[A-Za-z :;]{3,30}")
	private String description;
	
	@Column(name = "price")
	@Min(0)
	@Max(1000)
	private float price;
	
	@Column(name = "quantity")
	@Min(0)
	@Max(1000)
	private int quantity;
	
	public Product(String title, String description, float price, int quantity) {
		setTitle(title);
		setDescription(description);
		setPrice(price);
		setQuantity(quantity);
	}
	
}
