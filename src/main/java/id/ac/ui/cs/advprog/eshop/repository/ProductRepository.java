package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.Iterator;

public interface ProductRepository {
    Product create(Product product);
    Iterator<Product> findAll();
    Product findById(String productId);
    Product edit(String productId, Product newProductData);
    void delete(String productId);
}