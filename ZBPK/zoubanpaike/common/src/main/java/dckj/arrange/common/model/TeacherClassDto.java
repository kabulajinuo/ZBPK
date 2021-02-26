package dckj.arrange.common.model;

import lombok.Data;

/**
 * @Classname TeacherClassDto
 * @Description TODO
 * @Date 2019/8/20 17:40
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class TeacherClassDto {

    private String teacherId;

    private String teacherName;

    private Integer subjectId;

    private String subjectName;

    private String classId;

    private String className;

    private String roomId;

    private String roomName;

    private String siteId;

    private String siteName;

    private Integer mustNumber;

    private Integer choiceNumber;

    private Integer continuouNum;

}
