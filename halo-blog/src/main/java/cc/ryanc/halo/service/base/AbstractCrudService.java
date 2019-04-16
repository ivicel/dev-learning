package cc.ryanc.halo.service.base;

import cc.ryanc.halo.exception.PageNotFoundException;
import cc.ryanc.halo.repository.base.BaseRepository;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@SuppressWarnings("unchecked")
public abstract class AbstractCrudService<DOMAIN, ID> implements CrudService<DOMAIN, ID> {
    private final String domainName;
    private final BaseRepository<DOMAIN, ID> repository;

    protected AbstractCrudService(BaseRepository<DOMAIN, ID> repository) {
        this.domainName = getClass().getName();
        this.repository = repository;

        Class<DOMAIN> domainClass = (Class<DOMAIN>) fetchType(0);
    }

    private Type fetchType(int index) {
        Assert.isTrue(index >= 0 && index <= 1, "type index must be between 0 and 1");

        return ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[index];
    }

    @Override
    public @NotNull List<DOMAIN> listAll() {
        return repository.findAll();
    }

    @Override
    public @NotNull List<DOMAIN> listAll(Sort sort) {
        Assert.notNull(sort, "Sort info must not be null");

        return repository.findAll(sort);
    }

    @Override
    @NotNull
    public Page<DOMAIN> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public @NotNull List<DOMAIN> listAllByIds(@NotNull Collection<ID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public @NotNull List<DOMAIN> listAllByIds(@NotNull Collection<ID> ids, Sort sort) {
        return repository.findAllByIdIn(ids, sort);
    }

    @Override
    public @NotNull Optional<DOMAIN> fetchById(@NotNull ID id) {
        return repository.findById(id);
    }

    @Override
    public boolean existById(@NotNull ID id) {
        return repository.existsById(id);
    }

    @Override
    public void mustExistById(@NotNull ID id) {
        if (!repository.existsById(id)) {
            throw new PageNotFoundException(domainName + " was not exist");
        }
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public DOMAIN create(DOMAIN domain) {
        return repository.save(domain);
    }

    @Override
    public List<DOMAIN> createInBatch(@NotNull Collection<DOMAIN> domains) {
        return repository.saveAll(domains);
    }

    @Override
    public DOMAIN update(DOMAIN domain) {
        return repository.saveAndFlush(domain);
    }

    @Override
    public List<DOMAIN> updateInBatch(@NotNull Collection<DOMAIN> domains) {
        return null;
    }

    @Override
    public void removeByID(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void remove(DOMAIN domain) {
        repository.delete(domain);
    }

    @Override
    public void removeInBatch(Collection<DOMAIN> domains) {
        repository.deleteInBatch(domains);
    }

    @Override
    public void removeAll(@NotNull Collection<DOMAIN> domains) {
        repository.deleteAll(domains);
    }

    @Override
    public void removeAll() {
        repository.deleteAll();
    }
}
