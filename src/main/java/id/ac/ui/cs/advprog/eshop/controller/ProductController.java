package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private static final String REDIRECT_LIST_PRODUCT = "redirect:/product/list";
    private final ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable("productId") String productId, Model model) {
        Product product = service.findById(productId);

        if (product == null) {
            return REDIRECT_LIST_PRODUCT;
        }

        model.addAttribute("product", product);
        model.addAttribute("productId", productId);
        return "editProduct";
    }

    @PostMapping("edit/{productId}")
    public String editProductPost(@PathVariable("productId") String productId, @ModelAttribute Product product, Model model) {
        service.edit(product.getProductId(), product);
        return REDIRECT_LIST_PRODUCT;

    }

    @GetMapping("/delete/{productId}")
    public String deleteProductPage(@PathVariable("productId") String productId, Model model){
        service.delete(productId);
        return REDIRECT_LIST_PRODUCT;
    }
}