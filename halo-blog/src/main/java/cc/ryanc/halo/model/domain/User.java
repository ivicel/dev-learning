package cc.ryanc.halo.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 博主信息
 */
@Entity
@Table(name = "halo_user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 164603161637142363L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;                            // 用户 id

    @JsonIgnore
    @NotEmpty(message = "用户名不能为空")
    private String userName;                        // 登录的用户名

    private String userDisplayName;                 // 显示的用户称呼

    @JsonIgnore
    private String userPass;                        // 密码

    @Email(message = "邮箱格式不正确")
    private String userEmail;                       // 用户 email

    private String userAvatar;                      // 用户头像

    private String userDesc;                        // 用户描述

    @JsonIgnore
    private String loginEnable = "true";            // 是否禁用登录

    private Date loginLast;                         // 上次登录时间

    @JsonIgnore
    private Integer loginError = 0;                 // 登录错误次数
}
