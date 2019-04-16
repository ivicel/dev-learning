package cc.ryanc.halo.repository.base;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseRepository<DOMAIN, ID> extends JpaRepository<DOMAIN, ID> {
    /**
     * 根据 ids 按指定排序查找所有的 domain
     * @param ids
     * @param sort 排序方法
     * @return domain 列表
     */
    @NotNull
    List<DOMAIN> findAllByIdIn(@NotNull Iterable<ID> ids, @NotNull Sort sort);

    /**
     * 根据指定的 id 删除 domain
     * @param ids
     * @return 删除的行数
     */
    long deleteByIdIn(@NotNull Iterable<ID> ids);
}
