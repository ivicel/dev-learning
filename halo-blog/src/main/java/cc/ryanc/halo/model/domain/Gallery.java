package cc.ryanc.halo.model.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "halo_gallery")
public class Gallery implements Serializable {

    private static final long serialVersionUID = -4997360336652469949L;

    @Id
    @GeneratedValue
    private Long id;

    private String galleryName;

    private String galleryDesc;

    private String galleryDate;

    private String galleryLocation;

    private String galleryThumbnailUrl;

    private String galleryUrl;
}
