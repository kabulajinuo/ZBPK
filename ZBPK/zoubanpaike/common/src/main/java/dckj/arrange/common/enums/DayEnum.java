package dckj.arrange.common.enums;

public enum DayEnum {

    ZHOUYI(1, "星期一"),
    ZHOUER(2, "星期二"),
    ZHOUSAN(3, "星期三"),
    ZHOUSI(4, "星期四"),
    ZHOUWU(5, "星期五"),
    ZHOULIU(6, "星期六"),
    ZHOURI(7, "星期日"),
    ;

    private Integer value;

    private String message;

    private DayEnum(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 根据值获取对应的枚举
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static DayEnum parse(Integer value) {
        if (value != null) {
            DayEnum[] array = values();
            for (DayEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }


}
