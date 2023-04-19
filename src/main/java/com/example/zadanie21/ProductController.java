package com.example.zadanie21;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    String home(Model model) {
        return "index";
    }

    @GetMapping("/list")
    String showInfo(Model model,
                    @RequestParam(required = false) Category category) {
        List<Product> products;
        if (category != null) {
            products = productRepository.getProductsOfCategory(category);
        } else {
            products = productRepository.getProducts();
        }
        double totalPrice = products.stream()
                .map(Product::getPrice)
                .reduce(Double::sum)
                .orElse(0.);
        model.addAttribute("products", products);
        model.addAttribute("totalPrice", totalPrice);
        return "listOfProducts";
    }

    @PostMapping("/add")
    String addProduct(@RequestParam String name,
                      @RequestParam double price,
                      @RequestParam Category category,
                      Model model) {
        Product product = new Product(name, price, category);
        productRepository.addProduct(product);
        model.addAttribute("product", product);
        return "add";
    }
}
