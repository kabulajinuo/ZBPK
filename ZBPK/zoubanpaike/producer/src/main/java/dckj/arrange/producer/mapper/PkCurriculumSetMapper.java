package dckj.arrange.producer.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.entity.PkCurriculumSet;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.response.PkCurriculumSetListRes;

import java.util.List;

/**
 * <p>
 * 课程设置表 Mapper 接口
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
public interface PkCurriculumSetMapper extends BaseMapper<PkCurriculumSet> {

    List<PkCurriculumSetListRes> getCurriculumSetList(BaseReq param);

}
