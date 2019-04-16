package cc.ryanc.halo.repository;

import cc.ryanc.halo.model.domain.Logs;
import cc.ryanc.halo.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends BaseRepository<Logs, Long> {

}
