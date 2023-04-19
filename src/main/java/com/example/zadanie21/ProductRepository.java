package com.example.zadanie21;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new Product("Milka", 5.50, Category.GROCERIES));
        products.add(new Product("Oat Milk", 11.00, Category.GROCERIES));
        products.add(new Product("Domestos", 12.00, Category.HOUSEHOLD_ITEMS));
        products.add(new Product("Lotto", 2.50, Category.OTHER));
    }

    public List<Product> getProducts() {
        return products;
    }

    List<Product> getProductsOfCategory(Category category) {
        List<Product> products1 = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory() == category) {
                products1.add(product);
            }
        }
        return products1;
    }

    void addProduct(Product product) {
        products.add(product);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
