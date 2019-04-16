package info.ivicel.web.entity;


import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {
    private Integer id;
    private String email;
    private String username;
    private Integer roleId;
    private Boolean confirmed;
    private String name;
    private String location;
    private String aboutMe;
    private Date memberSince;
    private Date lastSeen;
    private String avatarHash;
    private String passwordHash;
    private List<Post> posts;

    public String getAvatar(int size) {
        return "https://secure.gravatar.com/avatar/" + avatarHash + "?s=" + size + "&d=identicon&r=g";
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", roleId=" + roleId +
                ", confirmed=" + confirmed +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", memberSince=" + memberSince +
                ", lastSeen=" + lastSeen +
                ", avatarHash='" + avatarHash + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
