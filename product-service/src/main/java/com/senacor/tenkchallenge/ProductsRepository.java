package com.senacor.tenkchallenge;

import com.senacor.tenkchallenge.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {


    Stream<Product> findAllById(Long id);
}
