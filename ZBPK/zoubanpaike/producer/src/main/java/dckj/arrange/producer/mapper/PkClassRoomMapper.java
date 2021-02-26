package dckj.arrange.producer.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.entity.PkClassRoom;
import dckj.arrange.common.model.ClassDto;
import dckj.arrange.common.model.PkClassRoomDto;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.ClassRoomReq;

import java.util.List;

/**
 * <p>
 * 班级所属教室表 Mapper 接口
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-19
 */
public interface PkClassRoomMapper extends BaseMapper<PkClassRoom> {
    List<ClassRoomReq> selectByGradeId(ClassRoomReq classRoomReq);
    int insertBatch(List<ClassRoomReq> classRoomReqs);

    List<PkClassRoomDto> getClassRoomList(BaseReq param);

    List<ClassDto> getCdList(BaseReq param);
}
