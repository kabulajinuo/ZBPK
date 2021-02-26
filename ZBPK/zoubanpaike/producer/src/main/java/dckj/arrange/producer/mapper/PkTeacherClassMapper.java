package dckj.arrange.producer.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.entity.PkTeacherClass;
import dckj.arrange.common.model.TeacherClassDto;
import dckj.arrange.common.model.request.BaseReq;

import java.util.List;

/**
 * <p>
 * 教师任教表 Mapper 接口
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-19
 */
public interface PkTeacherClassMapper extends BaseMapper<PkTeacherClass> {

    List<TeacherClassDto> getTeacherClassList(BaseReq param);

}
