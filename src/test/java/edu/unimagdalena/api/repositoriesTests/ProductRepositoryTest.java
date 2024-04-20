package edu.unimagdalena.api.repositoriesTests;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unimagdalena.api.AbstractIntegrationDBTest;

import edu.unimagdalena.api.model.entities.Product;
import edu.unimagdalena.api.repository.ProductRepository;

public class ProductRepositoryTest extends AbstractIntegrationDBTest {

    ProductRepository productRepository;

    @Autowired
    public ProductRepositoryTest(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    
     Product product1 = Product.builder()
            .name("pasta")
            .price((float) 6.0)
            .stock(260)
            .orderItems(null)
            .build();

    Product product2 = Product.builder()
            .name("sopa")
            .price((float) 5.0)
            .stock(200)
            .orderItems(null)
            .build();

    @BeforeEach
    void setUp(){
        productRepository.deleteAll();
    }
    
    @Test
    @DisplayName("test save")
    void givenAnProduct_WhenSave_ThenProductWithId(){
        //given
        Product productSaved = productRepository.save(product1);
        //then
        assertThat(productSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("test read")
    void givenProducts_ThenGetAll(){
        //given
        productRepository.save(product1);
        productRepository.save(product2);
        List<Product> products = productRepository.findAll();
        //then
        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("test delete")
    void givenIdProduct_thenDeleteIt(){
        //given
        productRepository.save(product1);
        Long id = product1.getId();
        //when
        productRepository.deleteById(id);
        //then
        assertThat(productRepository.findById(id)).isEmpty();
    }

    @Test
    @DisplayName("test update")
    void givenAnProduct_ThenUpdateThis(){
        //given
        Long id = productRepository.save(product2).getId();
        Integer newStock = 60;
        //when
        Product product = productRepository.findById(id).orElseThrow(null);
        if(product!= null){
            product.setStock(newStock);
            Product productUpdate = productRepository.save(product);
            //then
            assertThat(productUpdate.getStock()).isEqualTo(newStock);
        }
    }

    @Test
    @DisplayName("test findByName")
    void givenProduct_ThenFindByName(){
        //given
        String name = product1.getName();
        productRepository.save(product1);
        Product findProduct = productRepository.findByName(name);
        //then
        assertThat(findProduct).isEqualTo(product1);
    }

    @Test
    @DisplayName("test findByPrice")
    void givenProduct_ThenFindByPrice(){
        //given
        Float price = product1.getPrice();
        productRepository.save(product1);
        List<Product> products = productRepository.findByPrice(price);
        //then
        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("test findProductsInStock")
    void givenProduct_ThenFindProductsInStock(){
        //given
        productRepository.save(product1);
        productRepository.save(product2);
        List<Product> findProducts = productRepository.findProductsInStock();
        //then
        assertThat(findProducts.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("test findByMaxPriceAndStock")
    void givenProduct_ThenFindByMaxPriceAndStock(){
        //given
        productRepository.save(product1);
        productRepository.save(product2);
        List<Product> findProducts = productRepository.findByMaxPriceAndStock((float)5.0, (Integer)200);
        //then
        assertThat(findProducts.size()).isEqualTo(1);
    }
}
