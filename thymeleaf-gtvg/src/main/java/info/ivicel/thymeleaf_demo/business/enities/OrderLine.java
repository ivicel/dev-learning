package info.ivicel.thymeleaf_demo.business.enities;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderLine {
    private Product product;
    private Integer amount;
    private BigDecimal purchasePrice;
}
