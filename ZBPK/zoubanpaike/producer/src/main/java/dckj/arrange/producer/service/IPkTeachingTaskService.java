package dckj.arrange.producer.service;

import com.baomidou.mybatisplus.service.IService;
import dckj.arrange.common.entity.PkTeachingTask;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.PkTeachingTaskReq;
import dckj.arrange.common.model.request.TeachingTaskListReq;

import java.util.List;

/**
 * <p>
 * 教学任务表 服务类
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
public interface IPkTeachingTaskService extends IService<PkTeachingTask> {

    TeachingTaskListReq getTeachingTaskList(BaseReq param);

    void addTeachingTask(PkTeachingTaskReq param);

    void updateTeachingTask(List<PkTeachingTaskReq> param);

    void delTeachingTask(PkTeachingTask param);

}
