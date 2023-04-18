package com.example.zadanie21_00;

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
        model.addAttribute("categories", Category.values());
        return "index";
    }

    @GetMapping("/list")
    String showInfo(Model model,
                    @RequestParam(required = false) Category category) {
        List<Product> products = productRepository.getProducts();
        double totalPrice = productRepository.calculateTotalPrice();
        if (category != null) {
            Category[] categories = Category.values();
            for (Category category1 : categories) {
                if (category == category1) {
                    products = productRepository.getProductsOfCategory(category1);
                    totalPrice = productRepository.calculateTotalPriceForCategory(category1);
                }
            }
        }
        model.addAttribute("products", products);
        model.addAttribute("totalPrice", totalPrice);
        return "listOfProducts";
    }

    @PostMapping("/add")
    String addProduct(@RequestParam String name,
                      @RequestParam double price,
                      @RequestParam String descriptionPl,
                      Model model) {
        Category[] values = Category.values();
        Category category = null;
        for (Category value : values) {
            if (value.getDescriptionPl().equals(descriptionPl)) {
                category = value;
            }
        }
        Product product = new Product(name, price, category);
        productRepository.addProduct(product);
        model.addAttribute("product", product);
        return "add";
    }
}
