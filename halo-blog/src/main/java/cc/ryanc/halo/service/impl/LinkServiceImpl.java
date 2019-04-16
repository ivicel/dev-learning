package cc.ryanc.halo.service.impl;

import cc.ryanc.halo.model.domain.Link;
import cc.ryanc.halo.repository.LinkRepository;
import cc.ryanc.halo.service.LinkService;
import cc.ryanc.halo.service.base.AbstractCrudService;
import org.springframework.stereotype.Repository;

@Repository("linkService")
public class LinkServiceImpl extends AbstractCrudService<Link, Long> implements LinkService {
    private LinkRepository linkRepository;

    protected LinkServiceImpl(LinkRepository repository) {
        super(repository);
        this.linkRepository = repository;
    }


}
