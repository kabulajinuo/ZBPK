package dckj.arrange.producer.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.entity.PkTeacherPosition;
import dckj.arrange.common.model.request.TeacherPositionVo;

import java.util.List;
import java.util.Map;

public interface PkTeacherPositionMapper extends BaseMapper<PkTeacherPosition> {
    List<PkTeacherPosition> selectListBySchoolCode(TeacherPositionVo teacherPositionVo);
    TeacherPositionVo selectPostIdsByName(Map<String,Object> map);
}