package info.ivicel.repository.es;

import info.ivicel.domain.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;



public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {
    EsBlog findByBlogId(Long blogId);

    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(
            String title, String summary, String content, String tags, Pageable pageable);
}
