package info.ivicel.thymeleaf_demo.business.enities;


import java.util.Calendar;
import lombok.Data;



@Data
public class Customer {
    private Integer id;
    private String name;
    private Calendar customerSince;
}
