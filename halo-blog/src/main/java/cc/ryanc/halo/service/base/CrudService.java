package cc.ryanc.halo.service.base;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface CrudService<DOMAIN, ID> {
    @NotNull
    List<DOMAIN> listAll();

    @NotNull
    List<DOMAIN> listAll(Sort sort);

    @NotNull
    Page<DOMAIN> listAll(Pageable pageable);

    @NotNull
    List<DOMAIN> listAllByIds(@NotNull Collection<ID> ids);

    @NotNull
    List<DOMAIN> listAllByIds(@NotNull Collection<ID> ids, Sort sort);

    @NotNull
    Optional<DOMAIN> fetchById(@NotNull ID id);

    boolean existById(@NotNull ID id);

    void mustExistById(@NotNull ID id);

    long count();

    DOMAIN create(DOMAIN domain);

    List<DOMAIN> createInBatch(@NotNull Collection<DOMAIN> domains);

    DOMAIN update(DOMAIN domain);

    List<DOMAIN> updateInBatch(@NotNull Collection<DOMAIN> domains);

    void removeByID(ID id);

    void remove(DOMAIN domain);

    void removeInBatch(@NotNull Collection<DOMAIN> ids);

    void removeAll(@NotNull Collection<DOMAIN> domains);

    void removeAll();
}
