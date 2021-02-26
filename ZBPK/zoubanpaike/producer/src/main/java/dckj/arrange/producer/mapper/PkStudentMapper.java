package dckj.arrange.producer.mapper;

import dckj.arrange.common.entity.PkStudent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.entity.PkTeacherInfo;
import dckj.arrange.common.model.request.StudentVo;

import java.util.List;

/**
 * <p>
 * 学生表 Mapper 接口
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
public interface PkStudentMapper extends BaseMapper<PkStudent> {
    int insertBtach(List<StudentVo> students);
    List<StudentVo> selectByCondition(StudentVo studentVo);
    List<PkStudent> selectByCodeAndStuNo(StudentVo studentVo);
    int updateBatchByUserId(List<String> students);
}
