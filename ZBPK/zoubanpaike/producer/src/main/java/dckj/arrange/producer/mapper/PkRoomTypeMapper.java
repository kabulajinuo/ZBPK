package dckj.arrange.producer.mapper;

import dckj.arrange.common.entity.PkRoomType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.model.request.RoomTypeVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-13
 */
public interface PkRoomTypeMapper extends BaseMapper<PkRoomType> {
    List<PkRoomType> selectListBySchoolCode(RoomTypeVo roomTypeVo);
    PkRoomType selectTypeIdByName(Map map);
}
