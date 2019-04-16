package info.ivicel.tumoblog.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import info.ivicel.tumoblog.admin.entity.Article;
import info.ivicel.tumoblog.admin.entity.Tag;
import info.ivicel.tumoblog.admin.mapper.TagMapper;
import info.ivicel.tumoblog.admin.service.ITagService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("tagServcie")
public class TagServiceImpl implements ITagService {
    private TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public List<Tag> findAll() {
        return null;
    }

    @Override
    public List<Tag> findAllPagable(int offset, int num) {
        return null;
    }

    @Override
    public Long findAllCount() {
        return tagMapper.count();
    }

    @Override
    public Page<Tag> findAll(Tag tag, Integer pageCode, Integer pageSize) {
        return null;
    }

    @Override
    public Long save(Tag tag) {
        return null;
    }

    @Override
    public Tag findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public long insertOnIgnore(Set<Tag> tags) {
        if (tags == null || tags.size() == 0) {
            return 0;
        }
        return tagMapper.insertOnIgnore(tags);
    }

    @Override
    public Set<Tag> findByNameIn(Set<Tag> tags) {
        if (tags == null || tags.size() == 0) {
            return null;
        }
        return tagMapper.findByNameIn(tags);
    }
}
