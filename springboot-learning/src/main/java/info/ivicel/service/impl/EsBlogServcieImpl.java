package info.ivicel.service.impl;

import info.ivicel.domain.es.EsBlog;
import info.ivicel.repository.es.EsBlogRepository;
import java.util.LinkedList;
import org.elasticsearch.search.SearchParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import info.ivicel.domain.Blog;
import info.ivicel.service.IEsBlogService;

@Service("esBlogService")
public class EsBlogServcieImpl implements IEsBlogService {
    private EsBlogRepository esBlogRepository;
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public EsBlogServcieImpl(EsBlogRepository esBlogRepository, ElasticsearchTemplate elasticsearchTemplate) {
        this.esBlogRepository = esBlogRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public Page<EsBlog> listEsBlog(Pageable pageable) {
        return esBlogRepository.findAll(pageable);
    }

    @Override
    public List<EsBlog> findAll() {
        List<EsBlog> list = new LinkedList<>();
        esBlogRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Blog findById() {
        return null;
    }

    @Override
    public Page<EsBlog> listHotestEsBlogs(String keyword, Pageable pageable) {
        return null;
    }

    @Override
    public Page<EsBlog> listNewestEsBlogs(String keyword, Pageable pageable) throws SearchParseException {
        return esBlogRepository
                .findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(
                        keyword, keyword, keyword, keyword, pageable);
    }

    @Override
    public EsBlog save(EsBlog esBlog) {
        return esBlogRepository.save(esBlog);
    }
}
