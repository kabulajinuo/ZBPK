package dckj.arrange.common.enums;

/**
 * @Classname SubjectEnum
 * @Description TODO
 * @Date 2019/8/30 15:37
 * @Created by JinPeng
 * @Version 1.0
 */
public enum  SubjectEnum {

    CHINESE("语文"),
    MATH("数学"),
    FOREIGN("外语"),
    PHYSICS("物理"),
    HISTORY("历史"),
    GEOGRAPHY("地理"),
    CHEMISTRY("化学"),
    POLITICS("政治"),
    BIOLOGY("生物"),
    PE("体育"),
    ART("美术"),
    MUSIC("音乐"),
    GENERAL("通用技术"),
    INFORMATION("信息技术"),
    ;

    private String value;


    private SubjectEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
