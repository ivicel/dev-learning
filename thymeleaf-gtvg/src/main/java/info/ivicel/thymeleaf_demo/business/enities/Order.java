package info.ivicel.thymeleaf_demo.business.enities;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Data;

@Data
public class Order {
    private Integer id;
    private Calendar date;
    private Customer customer;
    private Set<OrderLine> orderLines;

    public Order() {
        orderLines = new LinkedHashSet<OrderLine>();
    }
}
