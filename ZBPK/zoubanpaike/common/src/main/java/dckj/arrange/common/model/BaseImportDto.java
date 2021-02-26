package dckj.arrange.common.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Classname BaseImportDto
 * @Description TODO
 * @Date 2019/8/27 10:18
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class BaseImportDto implements Serializable {

    private static final long serialVersionUID = 1L;

    //年级	班级	选课组合	教学楼	教室	语文老师	数学老师	外语老师	物理老师	历史老师	地理老师	化学老师	政治老师	生物老师	体育老师	美术老师	音乐老师	通用技术	信息技术

    @NotBlank(message = "年级不能为空")
    @Excel(name = "年级", width=15)
    private String grade;

    @NotBlank(message = "班级不能为空")
    @Excel(name = "班级", width=15)
    private String className;

    @NotBlank(message = "选课组合不能为空")
    @Excel(name = "选课组合", width=30)
    private String group;

    @NotBlank(message = "教学楼不能为空")
    @Excel(name = "教学楼", width=15)
    private String siteName;

    @NotBlank(message = "教室不能为空")
    @Excel(name = "教室", width=15)
    private String roomName;

    @NotBlank(message = "语文老师不能为空")
    @Excel(name = "语文", width=15)
    private String chineseNum;

    @Excel(name = "语文老师", width=15)
    private String chineseTeacher;

    @NotBlank(message = "数学老师不能为空")
    @Excel(name = "数学", width=15)
    private String mathNum;

    @Excel(name = "数学老师", width=15)
    private String mathTeacher;

    @NotBlank(message = "外语老师不能为空")
    @Excel(name = "外语", width=15)
    private String foreignNum;

    @Excel(name = "外语老师", width=15)
    private String foreignTeacher;

    @NotBlank(message = "物理老师不能为空")
    @Excel(name = "物理", width=15)
    private String physicsNum;

    @Excel(name = "物理老师", width=15)
    private String physicsTeacher;

    @NotBlank(message = "历史老师不能为空")
    @Excel(name = "历史", width=15)
    private String historyNum;

    @Excel(name = "历史老师", width=15)
    private String historyTeacher;

    @NotBlank(message = "地理老师不能为空")
    @Excel(name = "地理", width=15)
    private String geographyNum;

    @Excel(name = "地理老师", width=15)
    private String geographyTeacher;

    @NotBlank(message = "化学老师不能为空")
    @Excel(name = "化学", width=15)
    private String chemistryNum;

    @Excel(name = "化学老师", width=15)
    private String chemistryTeacher;

    @NotBlank(message = "政治老师不能为空")
    @Excel(name = "政治", width=15)
    private String politicsNum;

    @Excel(name = "政治老师", width=15)
    private String politicsTeacher;

    @NotBlank(message = "生物老师不能为空")
    @Excel(name = "生物", width=15)
    private String biologyNum;

    @Excel(name = "生物老师", width=15)
    private String biologyTeacher;

    @NotBlank(message = "体育老师不能为空")
    @Excel(name = "体育", width=15)
    private String peNum;

    @Excel(name = "体育老师", width=15)
    private String peTeacher;

    @NotBlank(message = "美术老师不能为空")
    @Excel(name = "美术", width=15)
    private String artNum;

    @Excel(name = "美术老师", width=15)
    private String artTeacher;

    @NotBlank(message = "音乐老师不能为空")
    @Excel(name = "音乐", width=15)
    private String musicNum;

    @Excel(name = "音乐老师", width=15)
    private String musicTeacher;

    @NotBlank(message = "通用技术不能为空")
    @Excel(name = "通用技术", width=15)
    private String generalNum;

    @Excel(name = "通用老师", width=15)
    private String generalTeacher;

    @NotBlank(message = "信息技术不能为空")
    @Excel(name = "信息技术", width=15)
    private String informationNum;

    @Excel(name = "信息老师", width=15)
    private String informationTeacher;




}
