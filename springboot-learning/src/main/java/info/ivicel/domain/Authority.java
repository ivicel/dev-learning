package info.ivicel.domain;

import java.io.Serializable;
import java.security.Principal;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authority")
public class Authority extends BaseModel implements GrantedAuthority {
    private static final long serialVersionUID = -2833933356193225754L;


    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
