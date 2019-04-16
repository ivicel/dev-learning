package info.ivicel.service;

import info.ivicel.domain.es.EsBlog;
import org.elasticsearch.search.SearchParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import info.ivicel.domain.Blog;

public interface IEsBlogService {
    Page<EsBlog> listEsBlog(Pageable pageable);

    List<EsBlog> findAll();

    Blog findById();

    Page<EsBlog> listHotestEsBlogs(String keyword, Pageable pageable);

    Page<EsBlog> listNewestEsBlogs(String keyword, Pageable pageable) throws SearchParseException;

    EsBlog save(EsBlog esBlog);
}
