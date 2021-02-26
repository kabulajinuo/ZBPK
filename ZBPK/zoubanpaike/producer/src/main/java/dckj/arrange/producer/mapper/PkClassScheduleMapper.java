package dckj.arrange.producer.mapper;

import dckj.arrange.common.entity.PkClassSchedule;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.model.request.SelectClassSubjectTea;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 总课程表 Mapper 接口
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-19
 */
public interface PkClassScheduleMapper extends BaseMapper<PkClassSchedule> {

    List<SelectClassSubjectTea> selectClassSubjectTea(Map<String,Object> map);

    List<SelectClassSubjectTea>  selectTeacherNum(Map<String,Object> map);

    List<PkClassSchedule>selectCurriculumNum(Map<String,Object> map);

    void inertBatch(List<PkClassSchedule> paramList);

    List<PkClassSchedule>selectClassSchedule(Map<String,Object>map);

}
