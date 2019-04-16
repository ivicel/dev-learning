package cc.ryanc.halo.model.enums;

public enum TrueFalseEnum {
    TRUE("true"),
    FALSE("false");


    private String desc;

    TrueFalseEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
