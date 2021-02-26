package dckj.arrange.common.enums;

public enum  WeekEnum {
    Monday(1,"星期一"),
    Tuesday(2,"星期二"),
    Wednesday(3,"星期三"),
    Thursday(4,"星期四"),
    Friday(5,"星期五"),
    Saturday(6,"星期六"),
    Sunday(7,"星期天")
    ;
    private Integer key;

    private String value;

    WeekEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Integer getKey() {

        return key;
    }
}
