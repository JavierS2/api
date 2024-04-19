package edu.unimagdalena.api.service.services;

import java.util.List;

import edu.unimagdalena.api.model.dto.ProductDTO;
import edu.unimagdalena.api.model.dto_save.ProductToSaveDto;

public interface ProductService {

    // CRUD
    ProductDTO create(ProductToSaveDto productToSaveDto);

    ProductDTO getProductById(Long productId);

    ProductDTO update(ProductToSaveDto productToSaveDto, Long productId);

    void delete(Long productId);

    // Others methods

    List<ProductDTO> getAllProducts();

    ProductDTO getByName(String name);

    List<ProductDTO> getByPrice(Float price);

    List<ProductDTO> getProductsInStock();

    List<ProductDTO> getByMaxPriceAndStock(Float price, Integer stock);
}
