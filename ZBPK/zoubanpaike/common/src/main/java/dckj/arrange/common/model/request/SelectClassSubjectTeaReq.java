package dckj.arrange.common.model.request;

import lombok.Data;

/**
 * @Description: 排课预设入参
 * @Author: jiangyong
 * @CreateDate: 2019/8/19 20:45
 * @Version: 1.0
 */
@Data
public class SelectClassSubjectTeaReq {

    private String taskId;

    private String subjectName;
}
