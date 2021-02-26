package dckj.arrange.producer.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.entity.PkLessonDetail;

import java.util.List;

/**
 * <p>
 * 课时详细表 Mapper 接口
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-18
 */
public interface PkLessonDetailMapper extends BaseMapper<PkLessonDetail> {
    int insertBatch(List<PkLessonDetail> lessonDetails);
    int updateByTaskId(PkLessonDetail pkLessonDetail);
    int deleteByTaskId(String taskId);
}
