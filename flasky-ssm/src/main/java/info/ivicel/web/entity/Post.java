package info.ivicel.web.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private Integer id;
    private String body;
    private String bodyHtml;
    private Date timestamp;
    private Integer authorId;
    private User author;


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", bodyHtml='" + bodyHtml + '\'' +
                ", timestamp=" + timestamp +
                ", authorId=" + authorId +
                ", author=" + author +
                '}';
    }
}
