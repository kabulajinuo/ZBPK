package dckj.arrange.common.model.request;

import dckj.arrange.common.entity.PkClass;
import dckj.arrange.common.entity.PkTeachingTask;
import lombok.Data;

import java.util.List;

/**
 * @Classname PkTeachingTaskReq
 * @Description TODO
 * @Date 2019/8/19 19:50
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class PkTeachingTaskReq extends PkTeachingTask {

    private List<PkClass> classIds;

}
