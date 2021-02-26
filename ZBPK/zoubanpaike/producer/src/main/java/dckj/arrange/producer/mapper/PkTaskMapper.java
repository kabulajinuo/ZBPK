package dckj.arrange.producer.mapper;

import dckj.arrange.common.entity.PkTask;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 排课任务表 Mapper 接口
 * </p>
 *
 * @author jiangyong
 * @since 2019-08-17
 */
public interface PkTaskMapper extends BaseMapper<PkTask> {

    List<PkTask>selectPktask(Map<String,Object> map);

}
