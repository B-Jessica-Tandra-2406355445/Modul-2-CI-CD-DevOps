package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        // No setup needed for this test class
    }

    @Test
    void testCreateProductPage() {
        String pageView = productController.createProductPage(model);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
        assertEquals("createProduct", pageView);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        product.setName("Product 1");
        product.setQuantity(2);
        String pageView = productController.createProductPost(product, model);
        verify(productService).create(product);
        assertEquals("redirect:list", pageView);
    }

    @Test
    void testProductListPage() {
        List<Product> products = new ArrayList<>();
        when(productService.findAll()).thenReturn(products);
        String pageView = productController.productListPage(model);
        verify(model, times(1)).addAttribute("products", products);
        assertEquals("productList", pageView);
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        when(productService.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096")).thenReturn(product);
        String pageView = productController.editProductPage("a0f9de46-90b1-437d-a0bf-d0821dde9096", model);
        verify(model, times(1)).addAttribute("product", product);
        verify(model, times(1)).addAttribute("productId", "a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertEquals("editProduct", pageView);
    }

    @Test
    void testEditProductPageNull() {
        when(productService.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096")).thenReturn(null);
        String pageView = productController.editProductPage("a0f9de46-90b1-437d-a0bf-d0821dde9096", model);
        assertEquals("redirect:/product/list", pageView);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        product.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        String pageView = productController.editProductPost("a0f9de46-90b1-437d-a0bf-d0821dde9096", product, model);
        verify(productService, times(1)).edit("a0f9de46-90b1-437d-a0bf-d0821dde9096", product);
        assertEquals("redirect:/product/list", pageView);
    }

    @Test
    void testDeleteProductPage() {
        String pageView = productController.deleteProductPage("a0f9de46-90b1-437d-a0bf-d0821dde9096", model);
        verify(productService, times(1)).delete("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertEquals("redirect:/product/list", pageView);
    }
}