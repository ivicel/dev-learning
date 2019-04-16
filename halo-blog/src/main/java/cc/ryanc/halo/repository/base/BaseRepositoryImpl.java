package cc.ryanc.halo.repository.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;


@Slf4j
public class BaseRepositoryImpl<DOMAIN, ID> extends SimpleJpaRepository<DOMAIN, ID>
        implements BaseRepository<DOMAIN, ID> {

    private final JpaEntityInformation<DOMAIN, ID> entityInformation;

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<DOMAIN, ID> entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    @Override
    public List<DOMAIN> findAllByIdIn(@NotNull Iterable<ID> ids, @NotNull Sort sort) {
        Assert.notNull(ids, "ID 不能为空");

        if (!ids.iterator().hasNext()) {
            return Collections.emptyList();
        }

        if (!entityInformation.hasCompositeId()) {
            ByIdsSpecification<DOMAIN> specification = new ByIdsSpecification<>(entityInformation);
            TypedQuery<DOMAIN> query = getQuery(specification, sort);
            return query.setParameter(specification.parameter, ids).getResultList();

        } else {
            List<DOMAIN> results = new ArrayList<>();
            ids.forEach(id -> findById(id).ifPresent(results::add));

            return results;
        }
    }

    @Override
    public long deleteByIdIn(@NotNull Iterable<ID> ids) {
        log.debug("Customized deleteByIdIn method was called.");

        List<DOMAIN> domains = findAllById(ids);
        deleteInBatch(domains);

        return domains.size();
    }

    private static class ByIdsSpecification<T> implements Specification<T> {

        private static final long serialVersionUID = -226657708704127985L;
        private final JpaEntityInformation<T, ?> entityInformation;

        @Nullable
        private ParameterExpression<Iterable> parameter;

        public ByIdsSpecification(JpaEntityInformation<T, ?> entityInformation) {
            this.entityInformation = entityInformation;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            Path<?> path = root.get(entityInformation.getIdAttribute());
            parameter = criteriaBuilder.parameter(Iterable.class);
            return path.in(parameter);
        }
    }
}
