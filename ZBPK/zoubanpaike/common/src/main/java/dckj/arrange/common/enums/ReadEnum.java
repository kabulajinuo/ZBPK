package dckj.arrange.common.enums;

public enum ReadEnum {
    DAYREADING(1,"走读"),
    BOARDING(0,"寄宿")
    ;
    private Integer key;
    private String message;

    ReadEnum(Integer key, String message) {
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
