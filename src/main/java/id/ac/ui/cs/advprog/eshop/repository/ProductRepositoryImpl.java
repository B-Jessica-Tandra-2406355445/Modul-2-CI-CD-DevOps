package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product) {
        if(product.getId() == null) {
            product.setId(UUID.randomUUID().toString());
        }
        productData.add(product);
        return product;
    }

    @Override
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    @Override
    public Product findById(String productId) {
        for(Product product : productData){
            if(product.getId().equals(productId)) {
                return product;
            }
        }
        return  null;
    }

    @Override
    public Product edit(String productId, Product newProductData) {
        Product productToEdit = findById(productId);
        if(productToEdit != null) {
            productToEdit.setName(newProductData.getName());
            productToEdit.setQuantity(newProductData.getQuantity());
        }
        return productToEdit;
    }

    @Override
    public void delete(String productId) {
        Product productToDelete = findById(productId);
        productData.remove(productToDelete);
    }
}