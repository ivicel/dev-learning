package cc.ryanc.halo.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "halo_options")
public class Option implements Serializable {

    private static final long serialVersionUID = 6497427582178727355L;

    @Id
    @Column(length = 127)
    private String optionName;

    @Lob
    private String optionValue;
}
