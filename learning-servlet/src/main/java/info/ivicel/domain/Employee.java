package info.ivicel.domain;

import java.sql.Date;

import info.ivicel.annotation.Column;
import info.ivicel.annotation.Table;
import lombok.Data;


@Data
@Table("employees")
public class Employee {
    @Column("emp_no")
    private Integer empNo;

    @Column("birth_date")
    private Date birthDate;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("gender")
    private String gender;

    @Column("hire_date")
    private Date hireDate;
}
