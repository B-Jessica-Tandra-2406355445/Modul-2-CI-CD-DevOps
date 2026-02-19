package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        verify(productRepository).create(product);
        assertEquals("a0f9de46-90b1-437d-a0bf-d0821dde9096", createdProduct.getProductId());
    }

    @Test
    void testFindAll() {
        List<Product> products = new ArrayList<>();
        Iterator<Product> iterator = products.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> listOfProducts = productService.findAll();

        verify(productRepository).findAll();
        assertEquals(products, listOfProducts);
    }

    @Test
    void testEdit() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product newProduct = new Product();
        newProduct.setProductId(productId);

        when(productRepository.edit(productId, newProduct)).thenReturn(newProduct);

        Product editedProduct = productService.edit(productId, newProduct);
        verify(productRepository, times(1)).edit(productId, newProduct);
        assertEquals(productId, editedProduct.getProductId());
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        productService.delete("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        verify(productRepository, times(1)).delete("a0f9de46-90b1-437d-a0bf-d0821dde9096");
    }

    @Test
    void testFindById() {
        Product product = new Product();
        when(productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096")).thenReturn(product);

        Product retrievedProduct = productService.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");

        verify(productRepository).findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertEquals(product, retrievedProduct);
    }
}

