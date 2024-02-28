package com.kirubelkinfe.inventoryservice;

import com.kirubelkinfe.inventoryservice.model.Inventory;
import com.kirubelkinfe.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory1 = Inventory.builder()
					.skuCode("iphone_13")
					.quantity(100)
					.build();
			Inventory inventory2 = Inventory.builder()
					.skuCode("iphone_13_pro")
					.quantity(150)
					.build();
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}
}
