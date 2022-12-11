package com.example.shoppinglist;

import com.example.shoppinglist.model.Product;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ModuleTests {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void addProduct(){
        String productTitle = "молоко";
        Product saved = productService.save(productTitle);
        assertThat(saved).isNotNull();
        assertThat(saved.getName().equals(productTitle));

        Mockito.verify(productRepository, Mockito.times(1)).save(saved);
    }

    @Test
    public void deleteProduct(){
        Optional<Product> product = Optional.of(new Product(Long.valueOf(3), "ветчина", false));
        Long id = Long.valueOf(3);
        Mockito.doReturn(product).when(productRepository).findById(id);
        int deleteResult = productService.delete(id);
        assertThat(deleteResult).isEqualTo(0);
    }

}
