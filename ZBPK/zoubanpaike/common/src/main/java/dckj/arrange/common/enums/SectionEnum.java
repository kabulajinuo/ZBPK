package dckj.arrange.common.enums;

/**
 * @Classname SectionEnum
 * @Description TODO
 * @Date 2019/8/20 21:15
 * @Created by JinPeng
 * @Version 1.0
 */
public enum SectionEnum {

    ONE(1, "第一节"),
    TWO(2, "第二节"),
    THREE(3, "第三节"),
    FOUR(4, "第四节"),
    FIVE(5, "第五节"),
    SIX(6, "第六节"),
    SEVEN(7, "第七节"),
    EIGHT(8, "第八节"),
    NINE(9, "第九节"),
    TEN(10, "第十节"),
    ;

    private Integer value;

    private String message;

    private SectionEnum(Integer value, String message) {
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
    public static SectionEnum parse(Integer value) {
        if (value != null) {
            SectionEnum[] array = values();
            for (SectionEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }


}
