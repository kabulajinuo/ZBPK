package dckj.arrange.common.model.request;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * @Description: java类作用描述
 * @Author: jiangyong
 * @CreateDate: 2019/8/17 16:45
 * @Version: 1.0
 */
@Data
public class PkTaskCurriculumSetVo {

    @TableField("task_name")
    private String taskName;
    /**
     * 年级id
     */
    @TableField("grade_id")
    private String gradeId;
    /**
     * 年级
     */
    @TableField("grade_name")
    private String gradeName;
    /**
     * 排课班级总数
     */
    @TableField("class_number")
    private Integer classNumber;
    /**
     * 学年
     */
    @TableField("school_year")
    private String schoolYear;
    /**
     * 学期类型 0-上学期 1-下学期
     */
    @TableField("semester_type")
    private Integer semesterType;
    /**
     * 走班类型 0-不走班 1-小走班 2大走班 3全走班
     */
    @TableField("pk_type")
    private Integer pkType;
    /**
     * 选课模式 0-常规排课 1-“3+1+2"模式” 2- “"6选3"模式”
     */
    @TableField("xk_mode")
    private Integer xkMode;
    /**
     * 学校编号
     */
    @TableField("school_code")
    private String schoolCode;
    /**
     * 0-未发布 1-调整中 2-已完结 3-已发布
     */
    private Integer type;
    /**
     * 操作人id（登录人权限账号id）
     */
    private String userId;

    /**
     * 科目id
     */
    @TableField("subject_id")
    private Integer subjectId;
    /**
     * 课程类型（1. 必考，2.选考）
     */
    @TableField("curriculum_type")
    private Integer curriculumType;
    /**
     * 必考节次/每周
     */
    @TableField("must_number")
    private Integer mustNumber;

    /**
     * 选考节次/每周
     */
    @TableField("choice_number")
    private Integer choiceNumber;
}
