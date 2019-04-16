package info.ivicel.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Salary {
    private Integer empNo;
    private Integer salary;
    private Date fromDate;
    private Date toDate;
}
