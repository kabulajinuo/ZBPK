package dckj.arrange.common.model.request;

import lombok.Data;

/**
 * @Classname BaseReq
 * @Description TODO
 * @Date 2019/8/17 16:58
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class BaseReq {

    private String gradeId;

    private Integer subjectId;

    private String taskId;

    private String name;

    private String schoolCode;

    private Integer level;

    private String classId;

    private String roomId;

    private String teacherId;


}
