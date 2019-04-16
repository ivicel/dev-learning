package info.ivicel.domain;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
public class User extends BaseModel implements UserDetails {
    private static final long serialVersionUID = 4465687758039044105L;

    @Size(min = 5, max = 20, message = "用户名长度必须在 5 ~ 20 字符之间")
    @Column(name = "username", unique = true, nullable = false, length = 20)
    private String username;                            // 用户帐号

    @Size(min = 8, max = 16, message = "密码长度必须在 8 ~ 20 字符之间")
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;                            // 密码

    @Size(min = 5, max = 20, message = "名字长度必须在 5 ~ 20 字符之间")
    @Column(name = "name", length = 20)
    private String name;                                // 别名

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度超过 50 个字符")
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;                               // 邮箱

    @Column(name = "avatar", length = 200)
    private String avatar;                              // 头像地址

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;                // 认证信息

    @Column(name = "account_expired", nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
    private Boolean accountExpired;

    @Column(name = "account_locked", nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
    private Boolean accountLocked;

    @Column(name = "credential_expired", nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
    private Boolean credentialExpired;

    @Column(name = "enabled", nullable = false, columnDefinition = "BIT(1) DEFAULT 1")
    private Boolean enabled;

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
