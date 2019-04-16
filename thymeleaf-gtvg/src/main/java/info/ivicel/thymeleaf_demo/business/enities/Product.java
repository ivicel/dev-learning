package info.ivicel.thymeleaf_demo.business.enities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Boolean inStock;
    private List<Comment> comments;

    public Product(Integer id, String name, Boolean inStock, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        comments = new ArrayList<Comment>();
    }
}
