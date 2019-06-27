package com.senacor.tenkchallenge;


import com.senacor.tenkchallenge.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class ProductModel {

    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_sequence")
    private Long id;
    private String name;

    public Product toProduct() {
        return new Product().id(id).name(name);
    }

    public static ProductModel fromProduct(Product product) {
        return ProductModel.builder().id(product.getId()).name(product.getName()).build();
    }

}
