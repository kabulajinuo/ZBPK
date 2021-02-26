package dckj.arrange.producer.mapper;

import dckj.arrange.common.entity.PkGrade;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.model.request.GradeVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-15
 */
public interface PkGradeMapper extends BaseMapper<PkGrade> {
    List<GradeVo> selectListByCode(GradeVo gradeVo);
}
