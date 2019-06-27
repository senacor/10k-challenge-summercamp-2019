package com.senacor.tenkchallenge;

import com.senacor.tenkchallenge.api.ProductsApiDelegate;
import com.senacor.tenkchallenge.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService implements ProductsApiDelegate {


    private ProductsRepository productsRepository;


    @Override
    public ResponseEntity<Product> addProduct(Product body) {
        Product save = productsRepository.save(body);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long productId) {
        Product product = productsRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
        productsRepository.delete(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> getProductById(Long productId) {
        return new ResponseEntity<>(productsRepository.findById(productId).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> updateProductWithForm(Long productId, String name) {
        Product product = productsRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
        product.setName(name);
        return new ResponseEntity<>(productsRepository.save(product), HttpStatus.ACCEPTED);
    }
}
