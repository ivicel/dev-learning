package info.ivicel.tumoblog.admin.service;

import info.ivicel.tumoblog.admin.entity.Tag;
import java.util.List;
import java.util.Set;

public interface ITagService extends IBaseService<Tag> {
    long insertOnIgnore(Set<Tag> tags);

    Set<Tag> findByNameIn(Set<Tag> tags);
}
