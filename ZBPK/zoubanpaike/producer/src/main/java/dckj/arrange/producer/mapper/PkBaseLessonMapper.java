package dckj.arrange.producer.mapper;

import dckj.arrange.common.entity.PkBaseLesson;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.model.request.BaseLessonReq;

/**
 * <p>
 * 课时基础表 Mapper 接口
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-18
 */
public interface PkBaseLessonMapper extends BaseMapper<PkBaseLesson> {
    BaseLessonReq selectByIdAndSchoolCode(BaseLessonReq baseLessonReq);
}
