package dckj.arrange.producer.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.entity.PkTeacherInfo;
import dckj.arrange.common.model.request.TeacherInfoVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-15
 */
public interface PkTeacherInfoMapper extends BaseMapper<PkTeacherInfo> {
    List<TeacherInfoVo> selectByCodeAndUserId(TeacherInfoVo teacherInfoVo);
    int insertBatch(List<TeacherInfoVo> teacherInfos);
    List<PkTeacherInfo> selectByCondition(TeacherInfoVo teacherInfoVo);
}
