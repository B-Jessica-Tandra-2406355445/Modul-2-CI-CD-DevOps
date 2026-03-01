package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        this.productRepository = new ProductRepositoryImpl();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getId(), savedProduct.getId());
        savedProduct = productIterator.next();
        assertEquals(product2.getId(), savedProduct.getId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEdit() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        Product newProductData = new Product();
        newProductData.setName("Sampo Cap Bango");
        newProductData.setQuantity(500);
        productRepository.edit(product.getId(), newProductData);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testIfEmpty() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.edit(product.getId(), product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("123e4567-e89b-12d3-a456-556642440000");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("123e4567-e89b-12d3-a456-556642440001");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(500);
        productRepository.create(product2);

        Product editProduct = new Product();
        editProduct.setId(product1.getId());
        editProduct.setName("Sampo Cap Kaka");
        editProduct.setQuantity(300);

        productRepository.edit(product1.getId(), editProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct1 = productIterator.next();
        assertEquals(product1.getId(), savedProduct1.getId());
        assertEquals(product1.getName(), savedProduct1.getName());
        assertEquals(product1.getQuantity(), savedProduct1.getQuantity());

        Product savedProduct2 = productIterator.next();
        assertEquals(product2.getId(), savedProduct2.getId());
        assertEquals(product2.getName(), savedProduct2.getName());
        assertEquals(product2.getQuantity(), savedProduct2.getQuantity());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setId("123e4567-e89b-12d3-a456-556642440000");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        productRepository.delete(product.getId());

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteIfEmpty() {
        productRepository.delete("123e4567-e89b-12d3-a456-556642440000");
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("123e4567-e89b-12d3-a456-556642440000");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("123e4567-e89b-12d3-a456-556642440001");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(500);
        productRepository.create(product2);

        productRepository.delete(product1.getId());
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product2.getId(), savedProduct.getId());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdNull() {
        Product product = new Product();
        product.setId(null);
        product.setName("dummy");
        product.setQuantity(1);

        productRepository.create(product);
        assertNull(productRepository.findById("non-exist-id"));
    }
}