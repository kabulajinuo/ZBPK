package dckj.arrange.producer.mapper;

import dckj.arrange.common.entity.PkRoomInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import dckj.arrange.common.model.request.RoomInfoVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-14
 */
public interface PkRoomInfoMapper extends BaseMapper<PkRoomInfo> {
    List<RoomInfoVo> selectBySchooleCode(RoomInfoVo roomInfoVo);
    List<PkRoomInfo> selectWithOutPage(RoomInfoVo roomInfoVo);
    int insertBatch(List<RoomInfoVo> roomInfoVoList);
}
