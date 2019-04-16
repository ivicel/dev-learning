package cc.ryanc.halo.repository;

import cc.ryanc.halo.model.domain.Tag;
import cc.ryanc.halo.repository.base.BaseRepository;
import java.util.Set;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends BaseRepository<Tag, Long> {

    Tag findByTagUrl(@Param("tagUrl") String tagUrl);

    Set<Tag> findAllByTagNameIn(Iterable<String> tagNames);
}
