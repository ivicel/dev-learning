package thymeleafexamples.stsm.business.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SeedStarter {
    private Integer id;
    private Date datePlanted;
    private Type type = Type.PLASTIC;
    private Feature[] features;
    private List<Row> rows = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatePlanted() {
        return datePlanted;
    }

    public void setDatePlanted(Date datePlanted) {
        this.datePlanted = datePlanted;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Feature[] getFeatures() {
        return features;
    }

    public void setFeatures(Feature[] features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "SeedStarter{" +
                "id=" + id +
                ", datePlanted=" + datePlanted +
                ", type=" + type +
                ", features=" + Arrays.toString(features) +
                ", rows=" + rows +
                '}';
    }

    public List<Row> getRows() {
        return rows;
    }
}
