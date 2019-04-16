package info.ivicel.thymeleaf_demo.business.enities;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private String text;

    public Comment(Integer id, String text) {
        this.id = id;
        this.text = text;
    }
}
