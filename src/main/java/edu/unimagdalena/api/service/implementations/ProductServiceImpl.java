package edu.unimagdalena.api.service.implementations;

import java.util.List;

import edu.unimagdalena.api.model.dto_save.ProductToSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.api.model.entities.Product;
import edu.unimagdalena.api.model.dto.ProductDTO;
import edu.unimagdalena.api.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.model.mappers.ProductMapper;
import edu.unimagdalena.api.repository.ProductRepository;
import edu.unimagdalena.api.service.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO create(ProductToSaveDto productToSaveDto) {
        Product productSaved = productRepository.save(ProductMapper.INSTANCE.productToSaveDtoToProduct(productToSaveDto));
        return ProductMapper.INSTANCE.productToProductDto(productSaved);
    }

    @Override
    public void delete(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotAbleToDeleteException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .toList();
    }

    @Override
    public List<ProductDTO> getByMaxPriceAndStock(Float price, Integer stock) {
        List<Product> products = productRepository.findByMaxPriceAndStock(price, stock);
        return products.stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .toList();
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found"));
        return ProductMapper.INSTANCE.productToProductDto(product);
    }

    @Override
    public ProductDTO getByName(String name) {
        Product product = productRepository.findByName(name);
        return ProductMapper.INSTANCE.productToProductDto(product);
    }

    @Override
    public List<ProductDTO> getByPrice(Float price) {
        List<Product> products = productRepository.findByPrice(price);
        return products.stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .toList();
    }

    @Override
    public List<ProductDTO> getProductsInStock() {
        List<Product> products = productRepository.findProductsInStock();
        return products.stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .toList();
    }

    @Override
    public ProductDTO update(ProductToSaveDto productToSaveDto, Long productId) {
        Product productInDb = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found"));
        productInDb.setName(productToSaveDto.name());
        productInDb.setPrice(productToSaveDto.price());
        productInDb.setStock(productToSaveDto.stock());
        return ProductMapper.INSTANCE.productToProductDto(productRepository.save(productInDb));
    }
}
