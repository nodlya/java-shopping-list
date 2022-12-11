package com.example.shoppinglist;

import com.example.shoppinglist.controller.RESTController;
import com.example.shoppinglist.model.Product;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
class ShoppingListApplicationTests {
    @Autowired
    private RESTController restController;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void contextLoads() {
    }

    @Test
    void rest() throws Exception{
        assertThat(restController).isNotNull();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listOfProducts() throws Exception {
        this.mockMvc.perform(get("/api/product/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getProductById() throws Exception {
        long id = 202L;
        Optional<Product> product = productRepository.findById(id);

        this.mockMvc.perform(get("/api/product/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":202,\"name\":\"сыр\",\"bought\":\"false\"}"))
                .andDo(print())
                .andExpect( content().string(containsString(product.get().getName())));
    }

    @Test
    public void postAddProduct() throws Exception{
        String productName = "чипсеки";
        this.mockMvc.perform(post("/api/add")
                .param("productName", productName))
                .andDo(print());

        this.mockMvc.perform(get("/api/product/").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[*].name", hasItem(productName)));

    }

}
