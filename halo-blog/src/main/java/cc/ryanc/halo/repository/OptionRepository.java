package cc.ryanc.halo.repository;

import cc.ryanc.halo.model.domain.Option;
import cc.ryanc.halo.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository("optionsRepository")
public interface OptionRepository extends BaseRepository<Option, String> {

}
