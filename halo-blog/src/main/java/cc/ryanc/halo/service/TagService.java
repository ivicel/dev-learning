package cc.ryanc.halo.service;

import cc.ryanc.halo.model.domain.Tag;
import cc.ryanc.halo.service.base.CrudService;
import java.util.Set;


public interface TagService extends CrudService<Tag, Long> {

    Tag findByTagUrl(String tagUrl);

    Set<Tag> stringToTags(String tags);
}
