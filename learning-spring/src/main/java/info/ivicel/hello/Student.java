package info.ivicel.hello;


import java.util.Date;

import lombok.Data;

@Data
public class Student {
    private Integer sid;
    private String sname;
    private Date sage;
    private String ssex;
    private Integer bid;
}
