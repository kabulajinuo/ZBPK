package dckj.arrange.common.model.request;

import lombok.Data;

/**
 * @Description: 课程预设科目 班级 教师 预设
 * @Author: jiangyong
 * @CreateDate: 2019/8/19 20:13
 * @Version: 1.0
 */
@Data
public class SelectClassSubjectTea {

    private String id;

    private String teacherId;

    private String teacherName;

    private String className;

    private String subjectName;

    private String gradeName;

    private Integer countClassSkNum;

    private Integer teacheaNum;

    private String taskId;

    private String subjectId;

    private String classId;

    private String gradeId;

    private Integer pCountClassSkNum;

    private Integer pTeacheaNum;


}
