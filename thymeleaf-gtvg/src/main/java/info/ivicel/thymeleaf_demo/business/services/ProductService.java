package info.ivicel.thymeleaf_demo.business.services;

import info.ivicel.thymeleaf_demo.business.enities.Product;
import info.ivicel.thymeleaf_demo.business.enities.repositories.ProductRepository;
import java.util.List;

public class ProductService {
    public List<Product> findAll() {
        return ProductRepository.getInstance().findAll();
    }

    public Product findById(final Integer id) {
        return ProductRepository.getInstance().findById(id);
    }
}
