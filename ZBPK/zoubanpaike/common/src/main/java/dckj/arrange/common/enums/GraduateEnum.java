package dckj.arrange.common.enums;

public enum GraduateEnum {
    YES(1,"已毕业"),
    NO(0,"在读")
    ;
    private Integer key;
    private String message;

    GraduateEnum(Integer key, String message) {
        this.key = key;
        this.message = message;
    }

    public Integer getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }
}
