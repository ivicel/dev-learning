package cc.ryanc.halo.repository;

import cc.ryanc.halo.model.domain.Link;
import cc.ryanc.halo.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends BaseRepository<Link, Long> {

}
