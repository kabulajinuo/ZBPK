package dckj.arrange.common.enums;

public enum FileEnum {
    Excels("xls"),
    ;

    public String getSuffix() {
        return suffix;
    }

    FileEnum(String suffix) {
        this.suffix = suffix;
    }

    private String suffix;
}
