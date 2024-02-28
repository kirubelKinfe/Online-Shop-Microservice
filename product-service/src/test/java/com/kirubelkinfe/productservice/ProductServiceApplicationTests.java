package com.kirubelkinfe.productservice;

import com.kirubelkinfe.productservice.model.Product;
import com.kirubelkinfe.productservice.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class ProductServiceApplicationTests {

	@Test
	void contextLoads() {
	}
		@Container
		private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
				.withDatabaseName("test")
				.withUsername("test")
				.withPassword("test");

		@Autowired
		private ProductRepository productRepository;

		@BeforeEach
		public void setUp() {
			Product product =  Product.builder()
					.name("Product 1")
					.description("Product 1 Desc")
					.price(BigDecimal.valueOf(100.40))
					.build();
			productRepository.save(product);
		}

		@AfterEach
		public void tearDown() {
			productRepository.deleteAll();
		}

		@Test
		public void getAllProducts() {
			List<Product> products = productRepository.findAll();
			assertNotNull(products.get(0));
			assertEquals("Product 1", products.get(0).getName());
			System.out.println(products.get(0));
		}
}
