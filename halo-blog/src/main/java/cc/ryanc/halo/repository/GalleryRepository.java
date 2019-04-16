package cc.ryanc.halo.repository;

import cc.ryanc.halo.model.domain.Gallery;
import cc.ryanc.halo.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository("galleryRepository")
public interface GalleryRepository extends BaseRepository<Gallery, Long> {

}
