package dckj.arrange.common.model.request;

import dckj.arrange.common.entity.PkTeacherInfo;
import lombok.Data;

import java.util.List;

/**
 * @Classname TeachingTaskListReq
 * @Description TODO
 * @Date 2019/8/20 11:22
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class TeachingTaskListReq {

    private List<PkTeachingTaskReq> pttrList;

    private List<PkTeacherInfo> ptiList;

}
