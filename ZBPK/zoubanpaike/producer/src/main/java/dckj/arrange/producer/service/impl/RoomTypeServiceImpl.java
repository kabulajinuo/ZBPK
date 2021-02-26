package dckj.arrange.producer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.PkRoomType;
import dckj.arrange.common.model.request.RoomTypeVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.producer.mapper.PkRoomTypeMapper;
import dckj.arrange.producer.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private PkRoomTypeMapper pkRoomTypeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toInsertRoomType(RoomTypeVo roomTypeVo) {
        roomTypeVo.setCreateTime(new Date());
        return pkRoomTypeMapper.insert(roomTypeVo);
    }

    @Override
    public PageInfo<PkRoomType> toGetRoomType(RoomTypeVo roomTypeVo) {
        PageHelper.startPage(roomTypeVo.getPageFilterVo().getPageNumber(), roomTypeVo.getPageFilterVo().getPageSize());
        List<PkRoomType> pkRoomTypes = pkRoomTypeMapper.selectListBySchoolCode(roomTypeVo);
        PageInfo<PkRoomType> pageInfo = new PageInfo<>(pkRoomTypes);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toDeleteRoomType(RoomTypeVo roomTypeVo) {
        return pkRoomTypeMapper.deleteById(roomTypeVo.getTypeId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int toUpdateRoomType(RoomTypeVo roomTypeVo) {
        return pkRoomTypeMapper.updateById(roomTypeVo);
    }

    @Override
    public Integer toGetTypeIds(String typeName, String schoolCode) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("schoolCode", schoolCode);
        paramMap.put("typeName", typeName);

        PkRoomType pkRoomType = pkRoomTypeMapper.selectTypeIdByName(paramMap);

        if (EmptyUtil.isEmpty(pkRoomType)) {
            PkRoomType pkRoomType1 = new PkRoomType();
            pkRoomType1.setCreateTime(new Date());
            pkRoomType1.setSchoolCode(schoolCode);
            pkRoomType1.setTypeName(typeName);
            pkRoomTypeMapper.insert(pkRoomType1);
        }

        PkRoomType pkRoomType2 = pkRoomTypeMapper.selectTypeIdByName(paramMap);
        return pkRoomType2.getTypeId();
    }

}
