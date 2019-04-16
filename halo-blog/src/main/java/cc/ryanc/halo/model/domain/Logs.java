package cc.ryanc.halo.model.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "halo_logs")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Logs implements Serializable {

    private static final long serialVersionUID = 6605328925964417533L;

    @Id
    @GeneratedValue
    private Long logId;

    private String logTitle;

    private String logContent;

    private String logIp;

    @CreatedDate
    private Date logCreated;
}
