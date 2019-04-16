package info.ivicel.thymeleaf_demo.business.enities;

import lombok.Data;

@Data
public class User {
    private String firstName;
    private String lastName;
    private String nationality;
    private Integer age;

    public User(String firstName, String lastName, String nationality, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.age = age;
    }
}
