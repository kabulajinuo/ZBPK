package dckj.arrange.producer.service;

import dckj.arrange.common.entity.PkTask;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 排课任务表 服务类
 * </p>
 *
 * @author jiangyong
 * @since 2019-08-17
 */
public interface IPkTaskService extends IService<PkTask> {
    List<PkTask> selectPktask(Map<String,Object> map);

}
