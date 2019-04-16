package cc.ryanc.halo.repository;

import cc.ryanc.halo.model.domain.User;
import cc.ryanc.halo.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
}
