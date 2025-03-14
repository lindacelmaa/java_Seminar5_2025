package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;

@SpringBootApplication
public class Seminar5Application {

	public static void main(String[] args) {
		SpringApplication.run(Seminar5Application.class, args);
	}
	
	
	@Bean
	public CommandLineRunner testDB(IProductRepo prodRepo) {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
			Product p1 = new Product("Abols", "Garsigs", 0.99f, 4);
			Product p2 = new Product("Gurkis", "Zals", 1.99f, 10);
			Product p3 = new Product("Tomats", "Sarkans", 3.49f, 100);
			//saglabajam prduktus arī DB
			prodRepo.save(p1);
			prodRepo.save(p2);
			prodRepo.save(p3);
				
			System.out.println("Cik produkti ir DB: " + prodRepo.count());
			
			
			System.out.println(prodRepo.findAll());
			System.out.println(prodRepo.findById(2l).get());
			Product productFromDB = prodRepo.findById(2l).get();
			productFromDB.setPrice(0.55f);
			prodRepo.save(productFromDB);
			
			prodRepo.deleteById(2l);
			System.out.println(prodRepo.findAll());
			}
		};
		
		
		
	}

}