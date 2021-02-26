package dckj.arrange.producer.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.entity.PkTeacherClassCopy;
import dckj.arrange.common.model.TeacherClassDto;
import dckj.arrange.common.model.request.BaseReq;

import java.util.List;

/**
 * <p>
 * 教师任教课程表 Mapper 接口
 * </p>
 *
 * @author Jiangyong
 * @since 2019-08-22
 */
public interface PkTeacherClassCopyMapper extends BaseMapper<PkTeacherClassCopy> {

    List<TeacherClassDto> getTeacherClassList(BaseReq param);

}
