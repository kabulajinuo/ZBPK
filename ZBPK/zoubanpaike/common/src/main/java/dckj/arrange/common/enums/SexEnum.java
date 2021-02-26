package dckj.arrange.common.enums;

/**
 * 性别枚举
 */
public enum SexEnum {

    MAN("0", "男"),
    WOMAN("1", "女"),
    ;

    private String value;

    private String message;

    private SexEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }


}
