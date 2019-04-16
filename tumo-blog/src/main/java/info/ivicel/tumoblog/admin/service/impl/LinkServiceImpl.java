package info.ivicel.tumoblog.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import info.ivicel.tumoblog.admin.entity.Article;
import info.ivicel.tumoblog.admin.entity.Link;
import info.ivicel.tumoblog.admin.mapper.LinkMapper;
import info.ivicel.tumoblog.admin.service.ILinkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("linkService")
public class LinkServiceImpl implements ILinkService {
    private LinkMapper linkMapper;

    @Autowired
    public LinkServiceImpl(LinkMapper linkMapper) {
        this.linkMapper = linkMapper;
    }

    @Override
    public List<Link> findAll() {
        return null;
    }

    @Override
    public List<Link> findAllPagable(int offset, int num) {
        return null;
    }

    @Override
    public Long findAllCount() {
        return linkMapper.count();
    }

    @Override
    public Page<Link> findAll(Link link, Integer pageCode, Integer pageSize) {
        return null;
    }

    @Override
    public Long save(Link article) {
        return null;
    }

    @Override
    public Link findById(Long id) {
        return null;
    }


}
