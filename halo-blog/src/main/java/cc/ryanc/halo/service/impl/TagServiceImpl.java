package cc.ryanc.halo.service.impl;

import cc.ryanc.halo.model.domain.Tag;
import cc.ryanc.halo.repository.TagRepository;
import cc.ryanc.halo.service.TagService;
import cc.ryanc.halo.service.base.AbstractCrudService;
import cn.hutool.core.util.StrUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tagService")
public class TagServiceImpl extends AbstractCrudService<Tag, Long> implements TagService {
    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        super(tagRepository);
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag findByTagUrl(String tagUrl) {
        return tagRepository.findByTagUrl(tagUrl);
    }

    @Override
    public Set<Tag> stringToTags(String tags) {
        if (StrUtil.isBlank(tags)) {
            return Collections.emptySet();
        }

        Set<String> tagNames = new HashSet<>(Arrays.asList(tags.trim().split(",")));
        Set<Tag> tagSet = tagRepository.findAllByTagNameIn(tagNames);
        if (tagSet.size() != tagNames.size()) {
            Set<Tag> tmp = tagNames.stream().map(n -> {
                Tag t = new Tag();
                t.setTagName(n);
                t.setTagUrl(n);
                return t;
            }).collect(Collectors.toSet());
            tmp.removeAll(tagSet);
            tagSet.addAll(tagRepository.saveAll(tmp));
        }

        return tagSet;
    }
}
