package cc.ryanc.halo.model.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "halo_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = -5779101807646209477L;

    @Id
    @GeneratedValue
    private Long menuId;

    /**
     * 菜单名称
     */
    @NotNull(message = "菜单名称不能为空")
    private String menuName;

    /**
     * 菜单链接路径
     */
    @NotNull(message = "菜单链接路径不能为空")
    private String menuUrl;

    /**
     * 排序编号
     */
    @NotNull(message = "菜单排序编号不能为空")
    private Integer menuSort;

    /**
     * 图标
     */
    private String menuIcon;

    /**
     * 打开方式
     */
    private String menuTarget;
}
