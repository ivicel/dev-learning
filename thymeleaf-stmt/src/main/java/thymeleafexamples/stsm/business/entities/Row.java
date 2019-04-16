package thymeleafexamples.stsm.business.entities;

public class Row {
    private Variety variety;
    private Integer seedPerCell;

    public Variety getVariety() {
        return variety;
    }

    public void setVariety(Variety variety) {
        this.variety = variety;
    }

    public Integer getSeedPerCell() {
        return seedPerCell;
    }

    public void setSeedPerCell(Integer seedPerCell) {
        this.seedPerCell = seedPerCell;
    }

    @Override
    public String toString() {
        return "Row{" +
                "variety=" + variety +
                ", seedPerCell=" + seedPerCell +
                '}';
    }
}
