package info.ivicel;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class PostRepositoryImpl extends SimpleJpaRepository<Post, Long> implements PostRepository {

    private JpaEntityInformation<Post, ?> entityInformation;
    private EntityManager entityManager;


    public PostRepositoryImpl(JpaEntityInformation<Post, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }


    @Override
    public List<Object> myFetchAll(Specification<Post> spec) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> query = cb.createQuery();
        Root<Post> root = query.from(Post.class);
        query.multiselect(root, root.get("author"));
        root.fetch("author");


        // return cb.equal(join.get("id"), root.get("author").get("id"));

        return entityManager.createQuery(query).getResultList();
    }
}
